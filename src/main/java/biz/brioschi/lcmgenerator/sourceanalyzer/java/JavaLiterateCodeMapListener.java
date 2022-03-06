package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.parser.JavaLexer;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParserBaseListener;
import biz.brioschi.lcmgenerator.directives.Directive;
import biz.brioschi.lcmgenerator.directives.LiterateMapInvoke;
import biz.brioschi.lcmgenerator.literatemap.Box;
import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap.DirectivesRecognizer;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser.*;

public class JavaLiterateCodeMapListener extends JavaParserBaseListener {

    private ListenerStatus currentStatus;
    private ScopeManager scopeManager;

    public JavaLiterateCodeMapListener(BufferedTokenStream bufferedTokenStream) {
        this.currentStatus = new ListenerStatus(bufferedTokenStream);
        this.scopeManager = new ScopeManager(currentStatus);
    }

    public List<Box> getLiterateCodeMapBoxes() {
        return this.currentStatus.boxes;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Scope management

    @Override
    public void enterTypeDeclaration(TypeDeclarationContext ctx) {
        scopeManager.enterTypeDeclaration(ctx);
    }

    @Override
    public void exitTypeDeclaration(TypeDeclarationContext ctx) {
        scopeManager.exitTypeDeclaration(ctx);
    }

    @Override
    public void enterClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
        scopeManager.enterClassBodyDeclaration(ctx);
    }

    @Override
    public void exitClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
        scopeManager.exitClassBodyDeclaration(ctx);
    }

    @Override
    public void enterInterfaceBodyDeclaration(InterfaceBodyDeclarationContext ctx) {
        scopeManager.enterInterfaceBodyDeclaration(ctx);
    }

    @Override
    public void exitInterfaceBodyDeclaration(InterfaceBodyDeclarationContext ctx) {
        scopeManager.exitInterfaceBodyDeclaration(ctx);
    }

    @Override
    public void enterLocalTypeDeclaration(LocalTypeDeclarationContext ctx) {
        scopeManager.enterLocalTypeDeclaration(ctx);
    }

    @Override
    public void exitLocalTypeDeclaration(LocalTypeDeclarationContext ctx) {
        scopeManager.exitLocalTypeDeclaration(ctx);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Extensions

    @Override
    public void enterClassDeclaration(ClassDeclarationContext ctx) {
        addCurrentTypeExtensions(ctx);
    }

    @Override
    public void enterInterfaceDeclaration(InterfaceDeclarationContext ctx) {
        addCurrentTypeExtensions(ctx);
    }

    @Override
    public void enterEnumDeclaration(EnumDeclarationContext ctx) {
        addCurrentTypeExtensions(ctx);
    }

    private void addCurrentTypeExtensions(ParserRuleContext ctx) {
        List<BoxConnection> connections = getCurrentBoxExtensions(ctx);
        this.currentStatus.typeScopeStack.peek().getConnections().addAll(connections);
    }

    private List<BoxConnection> getCurrentBoxExtensions(ParserRuleContext ctx) {
        List<BoxConnection> connections = new ArrayList<>();
        TerminalNode extendsToken = ctx.getToken(JavaParser.EXTENDS, 0);
        TerminalNode implementsToken = ctx.getToken(IMPLEMENTS, 0);
        int lastReadedChild = 2;
        while ((ctx.getChild(lastReadedChild) == extendsToken) || (ctx.getChild(lastReadedChild) == implementsToken)) {
            ParseTree extendsChild = ctx.getChild(lastReadedChild + 1);
            connections.addAll(parseListOfCurrentBoxExtensions(extendsChild));
            lastReadedChild += 2;
        };
        return connections;
    }

    private List<BoxConnection> parseListOfCurrentBoxExtensions(ParseTree interfaceList) {
        List<BoxConnection> connections = new ArrayList<>();
        for (int i = 0; i < interfaceList.getChildCount(); i += 2) {
            String currentExtensionName = interfaceList.getChild(i).getText();
            connections.add(
                    BoxConnection.generateExtends(
                            currentExtensionName
                    )
            );
        }
        return connections;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Code blocks

    @Override
    public void enterBlock(BlockContext ctx) {
        String sourceText = this.currentStatus.bufferedTokenStream.getText(Interval.of(ctx.start.getTokenIndex() + 1, ctx.stop.getTokenIndex() - 1));
        //String sourceText = bufferedTokenStream.getText(ctx.start + 1, ctx.stop);
        System.out.println(sourceText);
    }

    @Override
    public void enterBlockStatement(BlockStatementContext ctx) {
        String sourceText = this.currentStatus.bufferedTokenStream.getText(ctx.start, ctx.stop);
        System.out.println(sourceText);
    }

    @Override
    public void enterStatement(StatementContext ctx) {
        String sourceText = this.currentStatus.bufferedTokenStream.getText(ctx.start, ctx.stop);
        System.out.println(sourceText);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Directives

    private int lastReadTokenIndex = -1;

    @Override
    public void visitTerminal(TerminalNode terminalNode) {
        manageDirectivesOnCurrentNode(terminalNode.getSymbol());
    }

    private void manageDirectivesOnCurrentNode(Token currentToken) {
        List<Token> comments = new ArrayList<>();
        comments.addAll(readLeftComments(currentToken));
        comments.addAll(readRightComments(currentToken));
        comments.stream()
                .map(Token::getText)
                .map(DirectivesRecognizer::extractDirectives)
                .flatMap(Collection::stream)
                .forEach(this::applyDirective);
    }

    private List<Token> readLeftComments(Token currentToken) {
        List<Token> leftResult = this.currentStatus.bufferedTokenStream.getHiddenTokensToLeft(currentToken.getTokenIndex());
        List<Token> result = new ArrayList<>();
        if (leftResult != null) {
            for (Token token : leftResult) {
                if (isAComment(token) && isNotAlreadyRead(token)) {
                    result.add(token);
                    lastReadTokenIndex = token.getTokenIndex();
                }
            }
        }
        return result;
    }

    private boolean isNotAlreadyRead(Token token) {
        return token.getTokenIndex() > lastReadTokenIndex;
    }

    private List<Token> readRightComments(Token currentToken) {
        List<Token> rightResult = this.currentStatus.bufferedTokenStream.getHiddenTokensToRight(currentToken.getTokenIndex());
        List<Token> result = new ArrayList<>();
        if (rightResult != null) {
            for (Token token : rightResult) {
                if (isAComment(token) && tokensAreOnTheSameLine(currentToken, token)) {
                    result.add(token);
                    lastReadTokenIndex = token.getTokenIndex();
                }
            }
        }
        return result;
    }

    private boolean tokensAreOnTheSameLine(Token firstToken, Token secondToken) {
        return secondToken.getLine() == firstToken.getLine();
    }

    private boolean isAComment(Token token) {
        return (token.getType() == JavaLexer.LINE_COMMENT) || (token.getType() == JavaLexer.COMMENT);
    }

    private void applyDirective(Directive baseDirective) {
        // TODO refactoring and enrich logic
        if (baseDirective instanceof LiterateMapInvoke) {
            if (!this.currentStatus.typeScopeStack.empty()) {  // TODO emit warning!!!!
                LiterateMapInvoke directive = (LiterateMapInvoke) baseDirective;
                this.currentStatus.typeScopeStack.peek().getConnections().add(
                        BoxConnection.generateInvoke(
                                directive.getTargetBox(),
                                directive.getProgressiveNumber(),
                                directive.getDescription()
                        )
                );
            }
        }
    }

}
