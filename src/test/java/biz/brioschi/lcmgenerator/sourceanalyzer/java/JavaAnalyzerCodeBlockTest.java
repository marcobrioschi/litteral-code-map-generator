package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.literatemap.Box;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JavaAnalyzerCodeBlockTest {

    @Test
    @Disabled
    public void codeBlock() throws IOException {
        String inputUnit = "src/test/resources/biz/brioschi/lcmgenerator/sourceanalyzer/java/MethodCodeBlock.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<Box> units = javaAnalyzer.extractInfo();
        assertThat(true, is(false));
    }

}