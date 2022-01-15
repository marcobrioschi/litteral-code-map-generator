package biz.brioschi.lcmgenerator;

import biz.brioschi.lcmgenerator.antlr.parser.JavaParser;
import biz.brioschi.lcmgenerator.antlr.parser.JavaParserBaseListener;

public class LiterateCodeMapListener extends JavaParserBaseListener {

    @Override
    public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
        System.out.println(">>> " + ctx.qualifiedName().getText());
    }

}
