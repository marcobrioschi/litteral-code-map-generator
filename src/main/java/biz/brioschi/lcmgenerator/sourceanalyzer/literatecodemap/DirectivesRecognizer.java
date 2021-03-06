package biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap;

import biz.brioschi.lcmgenerator.antlr.literatecodemap.LiterateCodeMapLexer;
import biz.brioschi.lcmgenerator.antlr.literatecodemap.LiterateCodeMapParser;
import biz.brioschi.lcmgenerator.directives.Directive;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.List;

public class DirectivesRecognizer {

    public static List<Directive> extractDirectives(String inputString) {
        CharStream charInputStream = CharStreams.fromString(inputString);
        LiterateCodeMapLexer lexer = new LiterateCodeMapLexer(charInputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        LiterateCodeMapParser parser = new LiterateCodeMapParser(commonTokenStream);
        ParseTree tree = parser.commentsentence();
        DirectivesListener listener = new DirectivesListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        return listener.getDirectives();
    }

}
