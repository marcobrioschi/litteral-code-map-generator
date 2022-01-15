package biz.brioschi.lcmgenerator;

import biz.brioschi.lcmgenerator.antlr.parser.JavaLexer;
import biz.brioschi.lcmgenerator.antlr.parser.JavaParser;
import biz.brioschi.lcmgenerator.antlr.parser.JavaParserListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
    public static void main(String[] args) {
        String expression = "import java.util.List;";
        JavaLexer lexer = new JavaLexer(CharStreams.fromString(expression));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(commonTokenStream);
        ParseTree tree = parser.compilationUnit();
        JavaParserListener listener = new LiterateCodeMapListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener,tree);
    }
}
