package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.parser.JavaLexer;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser.ClassDeclarationContext;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser.IdentifierContext;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser.InterfaceDeclarationContext;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParserBaseListener;
import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import biz.brioschi.lcmgenerator.literatemap.BoxDeclarationScope;
import biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox;
import biz.brioschi.lcmgenerator.literatemap.directives.Directive;
import biz.brioschi.lcmgenerator.literatemap.directives.LiterateMapConnection;
import biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap.DirectivesRecognizer;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser.*;
import static biz.brioschi.lcmgenerator.literatemap.BoxConnection.ConnectionType.EXTENDS;
import static biz.brioschi.lcmgenerator.literatemap.BoxConnection.ConnectionType.INVOKE;
import static biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox.BoxType;
import static biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox.BoxType.*;

public class JavaLiterateCodeMapListener extends JavaParserBaseListener {
    private BufferedTokenStream bufferedTokenStream;
    private List<LiterateCodeMapBox> literateCodeMapBoxes;
    private Stack<BoxDeclarationScope> typeScopeStack;

    public JavaLiterateCodeMapListener(BufferedTokenStream bufferedTokenStream) {
        this.bufferedTokenStream = bufferedTokenStream;
        this.literateCodeMapBoxes = new ArrayList<>();
        this.typeScopeStack = new Stack<>();
    }

    public List<LiterateCodeMapBox> getLiterateCodeMapBoxes() {
        return literateCodeMapBoxes;
    }

    @Override
    public void enterClassDeclaration(ClassDeclarationContext ctx) {
        pushContextOfCurrentType(ctx.identifier());
        addCurrentTypeExtensions(ctx);
        // TODO manageDirectivesOnCurrentNode(ctx.start, ctx.stop);
    }

    @Override
    public void exitClassDeclaration(ClassDeclarationContext ctx) {
        popContextOfCurrentTypeAndStoreTheBox(JAVA_CLASS);
    }

    @Override
    public void enterInterfaceDeclaration(InterfaceDeclarationContext ctx) {
        pushContextOfCurrentType(ctx.identifier());
        addCurrentTypeExtensions(ctx);
    }

    @Override
    public void exitInterfaceDeclaration(InterfaceDeclarationContext ctx) {
        popContextOfCurrentTypeAndStoreTheBox(JAVA_INTERFACE);
    }

    @Override
    public void enterEnumDeclaration(EnumDeclarationContext ctx) {
        pushContextOfCurrentType(ctx.identifier());
        addCurrentTypeExtensions(ctx);
    }

    @Override
    public void exitEnumDeclaration(EnumDeclarationContext ctx) {
        popContextOfCurrentTypeAndStoreTheBox(JAVA_ENUM);
    }

    @Override
    public void enterTypeDeclaration(TypeDeclarationContext ctx) {
        pushRightTypeContext(ctx);
//        // TODO refactoring
//        manageDirectivesOnCurrentNode(ctx.start, ctx.stop);
    }

    @Override
    public void enterMemberDeclaration(MemberDeclarationContext ctx) {
        pushRightTypeContext(ctx);
    }

    @Override
    public void enterInterfaceMemberDeclaration(InterfaceMemberDeclarationContext ctx) {
        pushRightTypeContext(ctx);
    }

    @Override
    public void enterAnnotationTypeElementRest(AnnotationTypeElementRestContext ctx) {
        pushRightTypeContext(ctx);
    }

    @Override
    public void enterLocalTypeDeclaration(LocalTypeDeclarationContext ctx) {
        pushRightTypeContext(ctx);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Extensions

    private void pushRightTypeContext(ParserRuleContext ctx) {
        if (ctx.getRuleContext(ClassDeclarationContext.class, 0) != null) {
            String typeName = ctx.getRuleContext(ClassDeclarationContext.class, 0).identifier().getText();
            List<BoxConnection> connections = new ArrayList<>();
            typeScopeStack.push(new BoxDeclarationScope(typeName, JAVA_CLASS, connections));
        } else if (ctx.getRuleContext(InterfaceDeclarationContext.class, 0) != null) {
            String typeName = ctx.getRuleContext(InterfaceDeclarationContext.class, 0).identifier().getText();
            List<BoxConnection> connections = new ArrayList<>();
            typeScopeStack.push(new BoxDeclarationScope(typeName, JAVA_INTERFACE, connections));
        } else if (ctx.getRuleContext(EnumDeclarationContext.class, 0) != null) {
            String typeName = ctx.getRuleContext(EnumDeclarationContext.class, 0).identifier().getText();
            List<BoxConnection> connections = new ArrayList<>();
            typeScopeStack.push(new BoxDeclarationScope(typeName, JAVA_ENUM, connections));
        }
    }

    private void pushContextOfCurrentType(IdentifierContext identifier) {
        String typeName = identifier.getText();
        //typeScopeStack.push(new BoxDeclarationScope(typeName, null, new ArrayList<>()));
    }

    private void addCurrentTypeExtensions(ParserRuleContext ctx) {
        List<BoxConnection> connections = getCurrentBoxExtensions(ctx);
        typeScopeStack.peek().getConnections().addAll(connections);
    }

    private void popContextOfCurrentTypeAndStoreTheBox(BoxType boxType) {
        BoxDeclarationScope currentScope = typeScopeStack.pop();
        generateANewBoxElement(boxType, currentScope.getTypeName(), currentScope.getConnections());
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
    // Directives

    /*
    typeDeclaration
    : classOrInterfaceModifier*
      (classDeclaration | enumDeclaration | interfaceDeclaration )
    | ';'
    ;
     */
    // TODO search for other entry points following rules
    private void manageDirectivesOnCurrentNode(Token startToken, Token stopToken) {
        // TODO refactoring and complete with righ comments e filter on already readed comments
        List<Token> result = bufferedTokenStream.getHiddenTokensToLeft(startToken.getTokenIndex());
        if (result != null) {
            String leftComments = result.stream()
                    .filter(token -> token.getType() == JavaLexer.LINE_COMMENT)
                    .map(token -> token.getText())
                    .collect(Collectors.joining(" "));
            List<Directive> directives = DirectivesRecognizer.extractDirectives(leftComments);
            if (directives != null) {
                for (Directive baseDirective: directives) {
                    if (baseDirective instanceof LiterateMapConnection) {
                        LiterateMapConnection directive = (LiterateMapConnection)baseDirective;
                        typeScopeStack.peek().getConnections().add(
                                new BoxConnection(
                                        INVOKE,
                                        directive.getTargetBox(),
                                        directive.getDescription()
                                )
                        );
                    }
                }
            }
        }
    }

}
