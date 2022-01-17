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
        List<String> extend_s = new ArrayList<>();
        // TODO check if terminals 'extends' and 'implements' are presents
        if (ctx.typeType() != null) {
            extend_s.add(ctx.typeType().getText());
        }
        if (ctx.typeList() != null)
            if (ctx.typeList().size() != 0) {
                List<JavaParser.TypeTypeContext> interfaceList = ctx.typeList().get(0).typeType();
                for (JavaParser.TypeTypeContext interfaceName : interfaceList) {
                    extend_s.add(interfaceName.getText());
                }
            }
        generateANewBoxElement(
                BoxType.JAVA_CLASS,
                ctx.identifier().getText(),
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
