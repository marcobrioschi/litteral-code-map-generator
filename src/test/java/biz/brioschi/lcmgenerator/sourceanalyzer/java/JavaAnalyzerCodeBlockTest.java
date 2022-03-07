package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.literatemap.Box;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JavaAnalyzerCodeBlockTest {

    @Test
    public void codeBlock() throws IOException {
        String inputUnit = "src/test/resources/biz/brioschi/lcmgenerator/sourceanalyzer/java/MethodCodeBlock.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<Box> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(3));
        assertThat(units, hasItem(
                Box.builder().type(Box.BoxType.JAVA_CLASS).name("TestClass").connections(new ArrayList<>()).build()
        ));
        assertThat(units, hasItem(
                Box.builder().type(Box.BoxType.JAVA_INTERFACE).name("TestInterface").connections(new ArrayList<>()).build()
        ));
        assertThat(units, hasItem(
                Box.builder().type(Box.BoxType.JAVA_ENUM).name("TestEnum").connections(new ArrayList<>()).build()
        ));
        assertThat(true, is(false));    // TODO: add code blocks
    }

}