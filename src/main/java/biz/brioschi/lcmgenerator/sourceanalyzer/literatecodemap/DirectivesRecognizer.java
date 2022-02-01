package biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap;

import biz.brioschi.lcmgenerator.literatemap.directives.Directive;
import biz.brioschi.lcmgenerator.literatemap.directives.LiterateMapConnection;

import java.util.ArrayList;
import java.util.List;

public class DirectivesRecognizer {

    public static List<Directive> extractDirectives(String inputString) {
        // TODO implement a real directive recognizing
        List<Directive> directives = new ArrayList<>();
        if (inputString.contains("@LiterateMapConnection('TestDestinationClass', 'doSomething_A()')")) {
            directives.add(new LiterateMapConnection("TestDestinationClass",  "doSomething_A()"));
        }
        if (inputString.contains("@LiterateMapConnection('TestDestinationClass', 'doSomething_B()')")) {
            directives.add(new LiterateMapConnection("TestDestinationClass",  "doSomething_B()"));
        }
        return directives;
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
