package biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap;

import biz.brioschi.lcmgenerator.literatemap.directives.Directive;

import java.util.List;

public class DirectivesRecognizer {

    public static List<Directive> extractDirectives(String inputString) {
        // TODO implement a real directive recognizing
        return null;
//        CharStream charInputStream = CharStreams.fromString(inputString);
//        LiterateCodeMapLexer lexer = new LiterateCodeMapLexer(charInputStream);
//        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
//        LiterateCodeMapParser parser = new LiterateCodeMapParser(commonTokenStream);
//        ParseTree tree = parser.commentsentence();
//        DirectivesListener listener = new DirectivesListener();
//        ParseTreeWalker walker = new ParseTreeWalker();
//        walker.walk(listener, tree);
//        return listener.getDirectives();
    }

}
