package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.literatemap.Box;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType.*;
import static biz.brioschi.lcmgenerator.util.LiterateCodeMapBoxHelper.generateLiterateCodeMapBox;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class JavaAnalyzerNestedTypesTest {

    // TODO togliere JavaAnalyzer da tutte le classi

    @Test
    public void testNestedTypeNoExtensions() throws IOException {
        String inputUnit = "src/test/resources/biz/brioschi/lcmgenerator/sourceanalyzer/java/NestedTypeNoExtensions.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<Box> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(7));
        assertThat(units, hasItems(generateLiterateCodeMapBox(JAVA_CLASS, "Level_1", new String[]{})));
        assertThat(units, hasItems(generateLiterateCodeMapBox(JAVA_CLASS, "Level_1_1", new String[]{})));
        assertThat(units, hasItems(generateLiterateCodeMapBox(JAVA_CLASS, "Level_1_1_1", new String[]{})));
        assertThat(units, hasItems(generateLiterateCodeMapBox(JAVA_INTERFACE, "Level_1_1_2", new String[]{})));
        assertThat(units, hasItems(generateLiterateCodeMapBox(JAVA_ENUM, "Level_1_1_3", new String[]{})));
        assertThat(units, hasItems(generateLiterateCodeMapBox(JAVA_INTERFACE, "Level_1_2", new String[]{})));
        assertThat(units, hasItems(generateLiterateCodeMapBox(JAVA_ENUM, "Level_1_3", new String[]{})));
    }

    @Test
    public void testNestedTypeWithExtensions() throws IOException {
        String inputUnit = "src/test/resources/biz/brioschi/lcmgenerator/sourceanalyzer/java/NestedTypeWithExtensions.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<Box> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(7));
        assertThat(units, hasItems(generateLiterateCodeMapBox(JAVA_CLASS, "Level_1", "C1")));
        assertThat(units, hasItems(generateLiterateCodeMapBox(JAVA_CLASS, "Level_1_1", "I1", "I2")));
        assertThat(units, hasItems(generateLiterateCodeMapBox(JAVA_CLASS, "Level_1_1_1", "C2", "I3", "I4")));
        assertThat(units, hasItems(generateLiterateCodeMapBox(JAVA_INTERFACE, "Level_1_1_2", "I5")));
        assertThat(units, hasItems(generateLiterateCodeMapBox(JAVA_ENUM, "Level_1_1_3", "I8", "I9")));
        assertThat(units, hasItems(generateLiterateCodeMapBox(JAVA_INTERFACE, "Level_1_2", "I6", "I7")));
        assertThat(units, hasItems(generateLiterateCodeMapBox(JAVA_ENUM, "Level_1_3", "I10")));
    }

}
