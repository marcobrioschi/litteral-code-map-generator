package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox.BoxType.JAVA_CLASS;
import static biz.brioschi.lcmgenerator.util.LiterateCodeMapBoxHelper.generateLiterateCodeMapBox;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class JavaAnalyzerClassExtensionTest {

    @ParameterizedTest(name = "{index} - \"{0}\" generate {1}")
    @MethodSource
    public void parseClassExtensions(String inputUnit, LiterateCodeMapBox expectedLiterateCodeMapBox) {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromString(inputUnit));
        List<LiterateCodeMapBox> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(1));
        LiterateCodeMapBox firstUnit = units.get(0);
        assertThat(firstUnit, is(expectedLiterateCodeMapBox));
    }

    private static Stream<Arguments> parseClassExtensions() {
        return Stream.of(
                Arguments.of(
                        "class Test4 extends Test1 { }",
                        generateLiterateCodeMapBox(JAVA_CLASS, "Test4", "Test1")
                ),
                Arguments.of(
                        "class Test4 implements Test3 { }",
                        generateLiterateCodeMapBox(JAVA_CLASS, "Test4", "Test3")
                ),
                Arguments.of(
                        "class Test4 implements Test2, Test1 { }",
                        generateLiterateCodeMapBox(JAVA_CLASS, "Test4","Test2", "Test1")
                ),
                Arguments.of(
                        "class Test4 extends Test3 implements Test2, Test1 { }",
                        generateLiterateCodeMapBox(JAVA_CLASS, "Test4", "Test3", "Test2", "Test1")
                )
        );
    }
}
