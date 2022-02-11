package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.parser.JavaLexer;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParserBaseListener;
import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import biz.brioschi.lcmgenerator.literatemap.Box;
import biz.brioschi.lcmgenerator.directives.Directive;
import biz.brioschi.lcmgenerator.directives.LiterateMapInvoke;
import biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap.DirectivesRecognizer;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import static biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser.*;
import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType;
import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType.*;

public class JavaLiterateCodeMapListener extends JavaParserBaseListener {
    private BufferedTokenStream bufferedTokenStream;
    private List<Box> boxes;
    private Stack<BoxDeclarationScope> typeScopeStack;

    public JavaLiterateCodeMapListener(BufferedTokenStream bufferedTokenStream) {
        this.bufferedTokenStream = bufferedTokenStream;
        this.boxes = new ArrayList<>();
        this.typeScopeStack = new Stack<>();
    }

    public List<Box> getLiterateCodeMapBoxes() {
        return boxes;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Scope management

    @Override
    public void enterTypeDeclaration(TypeDeclarationContext ctx) {
        pushRightTypeContext(ctx);
    }

    @Override
    public void exitTypeDeclaration(TypeDeclarationContext ctx) {
        popContextOfCurrentTypeAndStoreTheBox();
    }

    @Override
    public void enterClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
        if (ctx.memberDeclaration() != null) {
            pushRightTypeContext(ctx.getRuleContext(MemberDeclarationContext.class, 0));
        }
    }

    @Override
    public void exitClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
        if (ctx.memberDeclaration() != null) {
            if (
                    (ctx.memberDeclaration().classDeclaration() != null) ||
                    (ctx.memberDeclaration().interfaceDeclaration() != null) ||
                    (ctx.memberDeclaration().enumDeclaration() != null)
            ) {
                popContextOfCurrentTypeAndStoreTheBox();
            }
        }
    }

    @Override
    public void enterInterfaceBodyDeclaration(InterfaceBodyDeclarationContext ctx) {
        if (ctx.interfaceMemberDeclaration() != null) {
            pushRightTypeContext(ctx.getRuleContext(InterfaceMemberDeclarationContext.class, 0));
        }
    }

    @Override
    public void exitInterfaceBodyDeclaration(InterfaceBodyDeclarationContext ctx) {
        if (ctx.interfaceMemberDeclaration() != null) {
            if (
                    (ctx.interfaceMemberDeclaration().classDeclaration() != null) ||
                    (ctx.interfaceMemberDeclaration().interfaceDeclaration() != null) ||
                    (ctx.interfaceMemberDeclaration().enumDeclaration() != null)
            ) {
                popContextOfCurrentTypeAndStoreTheBox();
            }
        }
    }

    @Override
    public void enterLocalTypeDeclaration(LocalTypeDeclarationContext ctx) {
        pushRightTypeContext(ctx);
    }

    @Override
    public void exitLocalTypeDeclaration(LocalTypeDeclarationContext ctx) {
        popContextOfCurrentTypeAndStoreTheBox();
    }

    private void pushRightTypeContext(ParserRuleContext ctx) {
        checkContextAndGenerateScope(ctx, ClassDeclarationContext.class, JAVA_CLASS);
        checkContextAndGenerateScope(ctx, InterfaceDeclarationContext.class, JAVA_INTERFACE);
        checkContextAndGenerateScope(ctx, EnumDeclarationContext.class, JAVA_ENUM);
    }

    private void checkContextAndGenerateScope(ParserRuleContext ctx, Class<? extends ParserRuleContext> classDeclarationContextClass, BoxType boxType) {
        ParserRuleContext currentCheckedClassContext = ctx.getRuleContext(classDeclarationContextClass, 0);
        if (currentCheckedClassContext != null) {
            IdentifierContext identifier = currentCheckedClassContext.getRuleContext(IdentifierContext.class, 0);
            String typeName = identifier.getText();
            buildContextAndPushIt(typeName, boxType);
        }
    }

    private void buildContextAndPushIt(String typeName, BoxType boxType) {
        BoxDeclarationScope scope = new BoxDeclarationScope(typeName, boxType, new ArrayList<>());
        typeScopeStack.push(scope);
    }

    private void popContextOfCurrentTypeAndStoreTheBox() {
        BoxDeclarationScope currentScope = typeScopeStack.pop();
        generateANewBoxElement(currentScope.getBoxType(), currentScope.getTypeName(), currentScope.getConnections());
    }

    private void generateANewBoxElement(BoxType boxType, String boxName, List<BoxConnection> connections) {
        boxes.add(
                Box.builder()
                        .type(boxType)
                        .name(boxName)
                        .connections(connections)
                        .build()
        );
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
        typeScopeStack.peek().getConnections().addAll(connections);
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
        List<Token> leftResult = bufferedTokenStream.getHiddenTokensToLeft(currentToken.getTokenIndex());
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
        List<Token> rightResult = bufferedTokenStream.getHiddenTokensToRight(currentToken.getTokenIndex());
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
            if (!typeScopeStack.empty()) {  // TODO emit warning!!!!
                LiterateMapInvoke directive = (LiterateMapInvoke) baseDirective;
                typeScopeStack.peek().getConnections().add(
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
