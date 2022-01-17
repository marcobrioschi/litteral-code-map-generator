package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParserBaseListener;
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
        List<String> extend_s = new ArrayList<>();
        String className = ctx.identifier().getText();
        int lastReadedChild = 1;
        while (ctx.getChildCount() - 1 > lastReadedChild) {
            ParseTree currentExtensionType = ctx.getChild(++lastReadedChild);
            if (currentExtensionType == ctx.EXTENDS()) {
                extend_s.add(ctx.getChild(++lastReadedChild).getText());
            }
            if (currentExtensionType == ctx.IMPLEMENTS()) {
                ParseTree interfaceList = ctx.getChild(++lastReadedChild);
                for (int i = 0; i < interfaceList.getChildCount(); ++i) {
                    String currentToken = interfaceList.getChild(i).getText();
                    if (!currentToken.equals(",")) {
                        extend_s.add(currentToken);
                    }
                }
            }
        }
        generateANewBoxElement(
                BoxType.JAVA_CLASS,
                className,
                extend_s
        );
    }

    @Override
    public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        generateANewBoxElement(BoxType.JAVA_INTERFACE, ctx.identifier().getText(), null);
    }

    @Override
    public void enterEnumDeclaration(JavaParser.EnumDeclarationContext ctx) {
        generateANewBoxElement(BoxType.JAVA_ENUM, ctx.identifier().getText(), null);
    }

    private void generateANewBoxElement(BoxType boxType, String boxName, List<String> extend_s) {
        literateCodeMapBoxes.add(
                LiterateCodeMapBox.builder()
                        .type(boxType)
                        .name(boxName)
                        .extend_s(extend_s)
                        .build()
        );
    }

}
