package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.lexer.JavaLexer;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser;
import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.List;

public class JavaAnalyzer {

    private final CharStream charInputStream;

    public JavaAnalyzer(CharStream charInputStream) {
        this.charInputStream = charInputStream;
    }

    public List<LiterateCodeMapBox> extractInfo() {
        JavaLexer lexer = new JavaLexer(this.charInputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(commonTokenStream);
        ParseTree tree = parser.compilationUnit();
        JavaLiterateCodeMapListener listener = new JavaLiterateCodeMapListener(commonTokenStream);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        return listener.getLiterateCodeMapBoxes();
    }

}
