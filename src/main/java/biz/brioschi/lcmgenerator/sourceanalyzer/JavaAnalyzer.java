package biz.brioschi.lcmgenerator.sourceanalyzer;

import biz.brioschi.lcmgenerator.antlr.lexer.JavaLexer;
import biz.brioschi.lcmgenerator.antlr.parser.JavaParser;
import biz.brioschi.lcmgenerator.antlr.parser.JavaParserListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class JavaAnalyzer {

    private final File sourceUnit;

    public JavaAnalyzer(File sourceUnit) {
        this.sourceUnit = sourceUnit;
    }

    public void extractInfo() {
        try {
            JavaLexer lexer = new JavaLexer(CharStreams.fromStream(new FileInputStream(sourceUnit)));
            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
            JavaParser parser = new JavaParser(commonTokenStream);
            ParseTree tree = parser.compilationUnit();
            JavaParserListener listener = new LiterateCodeMapListener();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listener, tree);
        } catch (IOException e) {
            System.err.println("Invalid file: " + e.getMessage());
        }
    }

}
