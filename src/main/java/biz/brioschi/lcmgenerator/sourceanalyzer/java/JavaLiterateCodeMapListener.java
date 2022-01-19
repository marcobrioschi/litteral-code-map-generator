package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParserBaseListener;
import biz.brioschi.lcmgenerator.diagram.BoxConnection;
import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

import static biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox.BoxType;

public class JavaLiterateCodeMapListener extends JavaParserBaseListener {

    private List<LiterateCodeMapBox> literateCodeMapBoxes;

    public JavaLiterateCodeMapListener() {
        literateCodeMapBoxes = new ArrayList<>();
    }

    public List<LiterateCodeMapBox> getLiterateCodeMapBoxes() {
        return literateCodeMapBoxes;
    }

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        List<BoxConnection> connections = new ArrayList<>();
        String className = ctx.identifier().getText();
        int lastReadedChild = 1;
        ParseTree currentExtensionType = ctx.getChild(++lastReadedChild);
        if (currentExtensionType == ctx.EXTENDS()) {
            String targetBoxName = ctx.getChild(++lastReadedChild).getText();
            connections.add(new BoxConnection(BoxConnection.ConnectionType.EXTENDS, targetBoxName));
            currentExtensionType = ctx.getChild(++lastReadedChild);
        }
        if (currentExtensionType == ctx.IMPLEMENTS()) {
            ParseTree interfaceList = ctx.getChild(++lastReadedChild);
            connections.addAll(parseListOfExtensions(interfaceList));
        }
        generateANewBoxElement(BoxType.JAVA_CLASS, className, connections);
    }

    @Override
    public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        List<BoxConnection> connections = new ArrayList<>();
        String className = ctx.identifier().getText();
        ParseTree currentExtensionType = ctx.getChild(2);
        if (currentExtensionType == ctx.EXTENDS()) {
            ParseTree interfaceList = ctx.getChild(3);
            connections.addAll(parseListOfExtensions(interfaceList));
        }
        generateANewBoxElement(BoxType.JAVA_INTERFACE, className, connections);
    }

    @Override
    public void enterEnumDeclaration(JavaParser.EnumDeclarationContext ctx) {
        List<BoxConnection> connections = new ArrayList<>();
        String className = ctx.identifier().getText();
        ParseTree currentExtensionType = ctx.getChild(2);
        if (currentExtensionType == ctx.IMPLEMENTS()) {
            ParseTree interfaceList = ctx.getChild(3);
            connections.addAll(parseListOfExtensions(interfaceList));
        }
        generateANewBoxElement(BoxType.JAVA_ENUM, className, connections);
    }

    private List<BoxConnection> parseListOfExtensions(ParseTree interfaceList) {
        List<BoxConnection> connections = new ArrayList<>();
        for (int i = 0; i < interfaceList.getChildCount(); ++i) {
            String currentToken = interfaceList.getChild(i).getText();
            if (!currentToken.equals(",")) {
                connections.add(new BoxConnection(BoxConnection.ConnectionType.EXTENDS, currentToken));
            }
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

}
