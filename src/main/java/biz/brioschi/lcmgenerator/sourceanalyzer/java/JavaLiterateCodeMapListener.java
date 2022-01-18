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
        while (ctx.getChildCount() - 1 > lastReadedChild) {
            ParseTree currentExtensionType = ctx.getChild(++lastReadedChild);
            if (currentExtensionType == ctx.EXTENDS()) {
                String boxName = ctx.getChild(++lastReadedChild).getText();
                connections.add(new BoxConnection(BoxConnection.ConnectionType.EXTENDS, boxName));
            }
            if (currentExtensionType == ctx.IMPLEMENTS()) {
                ParseTree interfaceList = ctx.getChild(++lastReadedChild);
                for (int i = 0; i < interfaceList.getChildCount(); ++i) {
                    String currentToken = interfaceList.getChild(i).getText();
                    if (!currentToken.equals(",")) {
                        connections.add(new BoxConnection(BoxConnection.ConnectionType.EXTENDS, currentToken));
                    }
                }
            }
        }
        generateANewBoxElement(
                BoxType.JAVA_CLASS,
                className,
                connections);
    }

    @Override
    public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        generateANewBoxElement(BoxType.JAVA_INTERFACE, ctx.identifier().getText(), new ArrayList<>());
    }

    @Override
    public void enterEnumDeclaration(JavaParser.EnumDeclarationContext ctx) {
        generateANewBoxElement(BoxType.JAVA_ENUM, ctx.identifier().getText(), new ArrayList<>());
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
