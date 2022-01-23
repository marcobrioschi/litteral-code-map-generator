package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParserBaseListener;
import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox;
import biz.brioschi.lcmgenerator.literatemap.BoxDeclarationScope;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static biz.brioschi.lcmgenerator.literatemap.BoxConnection.ConnectionType.EXTENDS;
import static biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox.BoxType;
import static biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox.BoxType.*;

public class JavaLiterateCodeMapListener extends JavaParserBaseListener {

    private BufferedTokenStream bufferedTokenStream;
    private List<LiterateCodeMapBox> literateCodeMapBoxes;
    private Stack<BoxDeclarationScope> typeScopeStack;
    private int lastReadCommentIndex;

    public JavaLiterateCodeMapListener(BufferedTokenStream bufferedTokenStream) {
        this.bufferedTokenStream = bufferedTokenStream;
        this.literateCodeMapBoxes = new ArrayList<>();
        this.typeScopeStack = new Stack<>();
        this.lastReadCommentIndex = -1;
    }

    public List<LiterateCodeMapBox> getLiterateCodeMapBoxes() {
        return literateCodeMapBoxes;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Extensions

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        String className = ctx.identifier().getText();
        List<BoxConnection> connections = getCurrentBoxExtensions(ctx);
        typeScopeStack.push(new BoxDeclarationScope(className, connections));
    }

    @Override
    public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        BoxDeclarationScope currentScope = typeScopeStack.pop();
        generateANewBoxElement(JAVA_CLASS, currentScope.getTypeName(), currentScope.getConnections());
    }

    @Override
    public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        String className = ctx.identifier().getText();
        List<BoxConnection> connections = getCurrentBoxExtensions(ctx);
        typeScopeStack.push(new BoxDeclarationScope(className, connections));
    }

    @Override
    public void exitInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        BoxDeclarationScope currentScope = typeScopeStack.pop();
        generateANewBoxElement(JAVA_INTERFACE, currentScope.getTypeName(), currentScope.getConnections());
    }

    @Override
    public void enterEnumDeclaration(JavaParser.EnumDeclarationContext ctx) {
        String className = ctx.identifier().getText();
        List<BoxConnection> connections = getCurrentBoxExtensions(ctx);
        typeScopeStack.push(new BoxDeclarationScope(className, connections));
    }

    @Override
    public void exitEnumDeclaration(JavaParser.EnumDeclarationContext ctx) {
        BoxDeclarationScope currentScope = typeScopeStack.pop();
        generateANewBoxElement(JAVA_ENUM, currentScope.getTypeName(), currentScope.getConnections());
    }

    private List<BoxConnection> getCurrentBoxExtensions(ParserRuleContext ctx) {
        List<BoxConnection> connections = new ArrayList<>();
        TerminalNode extendsToken = ctx.getToken(JavaParser.EXTENDS, 0);
        TerminalNode implementsToken = ctx.getToken(JavaParser.IMPLEMENTS, 0);
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
                    new BoxConnection(
                            EXTENDS,
                            currentExtensionName
                    )
            );
        }
        return connections;
    }

    private void generateANewBoxElement(BoxType boxType, String boxName, List<BoxConnection> connections) {
        literateCodeMapBoxes.add(
                LiterateCodeMapBox.builder()
                        .type(boxType)
                        .name(boxName)
                        .connections(connections)
                        .build()
        );
    }

    ///////////////////////////////////////////////////////////////////////////
    // Literate Map Generator directives

    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        //parseDirectives(ctx);
    }
    // TODO typeDeclaration, classBodyDeclaration, interfaceBodyDeclaration, block, blockStatement and remove statement

    private void parseDirectives(JavaParser.StatementContext ctx) {
        List<Token> possibleDirectives = new ArrayList<>();
        possibleDirectives.addAll(findAllCommentsBeforeTheCurrentElementAndNotAlreadyUsed(ctx.start));
        System.out.println(ctx.stop);
        possibleDirectives.addAll(findAllCommentsAfterTheCurrentElementAndInTheSameLine(ctx.stop));
        lastReadCommentIndex = possibleDirectives.stream()
                .mapToInt(token -> token.getTokenIndex())
                .max().orElse(lastReadCommentIndex);
        for (Token singleToken : possibleDirectives) {
            System.out.println(singleToken.getCharPositionInLine() + " - " + singleToken.getText());
        }
    }

    private List<Token> findAllCommentsBeforeTheCurrentElementAndNotAlreadyUsed(Token currentElementFirstToken) {
        return bufferedTokenStream.getHiddenTokensToLeft(currentElementFirstToken.getTokenIndex()).stream()
                .filter(token -> isAComment(token))
                .filter(token -> !isAlreadyUsed(token))
                .collect(Collectors.toList());
    }

    private List<Token> findAllCommentsAfterTheCurrentElementAndInTheSameLine(Token currentElementLastToken) {
        return bufferedTokenStream.getHiddenTokensToRight(currentElementLastToken.getTokenIndex()).stream()
                .filter(token -> isAComment(token))
                .filter(token -> isOnTheSameLine(token, currentElementLastToken.getLine()))
                .collect(Collectors.toList());
    }

    private boolean isAlreadyUsed(Token token) {
        return token.getTokenIndex() <= lastReadCommentIndex;
    }

    private boolean isOnTheSameLine(Token token, int referenceLine) {
        return token.getLine() == referenceLine;
    }

    private boolean isAComment(Token token) {
        return (token.getType() == JavaParser.COMMENT) || (token.getType() == JavaParser.LINE_COMMENT);
    }

}
