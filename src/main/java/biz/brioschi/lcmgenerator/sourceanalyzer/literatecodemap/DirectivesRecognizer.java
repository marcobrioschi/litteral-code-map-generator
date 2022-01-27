package biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap;

import biz.brioschi.lcmgenerator.literatemap.directives.Directive;
import biz.brioschi.lcmgenerator.literatemap.directives.LiterateMapConnection;

import java.util.Arrays;
import java.util.List;

public class DirectivesRecognizer {

    public static List<Directive> extractDirectives(String inputString) {
        // TODO implement a real directive recognizing
        if (inputString.contains("// @LiterateMapConnection('TestDestinationClass', 'doSomething()')")) {
            return Arrays.asList(new LiterateMapConnection("TestDestinationClass",  "doSomething()"));
        } else {
            return null;
        }
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
