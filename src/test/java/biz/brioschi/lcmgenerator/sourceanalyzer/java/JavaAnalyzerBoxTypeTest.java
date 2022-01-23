package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox.BoxType.*;
import static biz.brioschi.lcmgenerator.util.LiterateCodeMapBoxHelper.generateLiterateCodeMapBox;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class JavaAnalyzerBoxTypeTest {

    @ParameterizedTest(name = "{index} - \"{0}\" generate the box {1}")
    @MethodSource
    public void parseJavaBox(String inputUnit, LiterateCodeMapBox expectedLiterateCodeMapBox) {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromString(inputUnit));
        List<LiterateCodeMapBox> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(1));
        assertThat(units.get(0), is(expectedLiterateCodeMapBox));
    }

    private static Stream<Arguments> parseJavaBox() {
        return Stream.of(
                Arguments.of("public class TestClass { }", generateLiterateCodeMapBox(JAVA_CLASS, "TestClass")),
                Arguments.of("public interface TestInterface { }", generateLiterateCodeMapBox(JAVA_INTERFACE, "TestInterface")),
                Arguments.of("public enum TestEnum { A, B; }", generateLiterateCodeMapBox(JAVA_ENUM, "TestEnum"))
        );
    }

}
