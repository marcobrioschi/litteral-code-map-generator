package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParserBaseListener;
import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;

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
        generateANewBoxElement(BoxType.JAVA_CLASS, ctx.identifier().getText());
    }

    @Override
    public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        generateANewBoxElement(BoxType.JAVA_INTERFACE, ctx.identifier().getText());
    }

    @Override
    public void enterEnumDeclaration(JavaParser.EnumDeclarationContext ctx) {
        generateANewBoxElement(BoxType.JAVA_ENUM, ctx.identifier().getText());
    }

    private void generateANewBoxElement(BoxType boxType, String boxName) {
        literateCodeMapBoxes.add(
                new LiterateCodeMapBox(boxType, boxName)
        );
    }

}
