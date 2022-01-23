package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParserBaseListener;
import biz.brioschi.lcmgenerator.diagram.BoxConnection;
import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;
import biz.brioschi.lcmgenerator.diagram.TypeDeclarationScope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox.BoxType;

public class JavaLiterateCodeMapListener extends JavaParserBaseListener {

    private List<LiterateCodeMapBox> literateCodeMapBoxes;
    private Stack<TypeDeclarationScope> typeScopeStack;

    public JavaLiterateCodeMapListener() {
        this.literateCodeMapBoxes = new ArrayList<>();
        this.typeScopeStack = new Stack<>();
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
        typeScopeStack.push(new TypeDeclarationScope(className, connections));
    }

    @Override
    public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        TypeDeclarationScope currentScope = typeScopeStack.pop();
        generateANewBoxElement(BoxType.JAVA_CLASS, currentScope.getTypeName(), currentScope.getConnections());
    }

    @Override
    public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        String className = ctx.identifier().getText();
        List<BoxConnection> connections = getCurrentBoxExtensions(ctx);
        typeScopeStack.push(new TypeDeclarationScope(className, connections));
    }

    @Override
    public void exitInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        TypeDeclarationScope currentScope = typeScopeStack.pop();
        generateANewBoxElement(BoxType.JAVA_INTERFACE, currentScope.getTypeName(), currentScope.getConnections());
    }

    @Override
    public void enterEnumDeclaration(JavaParser.EnumDeclarationContext ctx) {
        String className = ctx.identifier().getText();
        List<BoxConnection> connections = getCurrentBoxExtensions(ctx);
        typeScopeStack.push(new TypeDeclarationScope(className, connections));
    }

    @Override
    public void exitEnumDeclaration(JavaParser.EnumDeclarationContext ctx) {
        TypeDeclarationScope currentScope = typeScopeStack.pop();
        generateANewBoxElement(BoxType.JAVA_ENUM, currentScope.getTypeName(), currentScope.getConnections());
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
                            BoxConnection.ConnectionType.EXTENDS,
                            currentExtensionName
                    )
            );
        }
        return connections;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Method invocations

//    @Override
//    public void enterExpression(JavaParser.ExpressionContext ctx) {
//        if (ctx.bop != null) {
//            String targetBoxName = ctx.expression(0).getText();
//            String invocationDescription = ctx.methodCall().getText();
//            System.out.println(targetBoxName);
//            System.out.println(invocationDescription);
//        }
//    }

    private void generateANewBoxElement(BoxType boxType, String boxName, List<BoxConnection> connections) {
        literateCodeMapBoxes.add(
                LiterateCodeMapBox.builder()
                        .type(boxType)
                        .name(boxName)
                        .connections(connections)
                        .build()
        );
    }

}
