package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.model.LiterateCodeMapBox;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static biz.brioschi.lcmgenerator.model.LiterateCodeMapBox.BoxType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class JavaAnalyzerTest {

    @ParameterizedTest(name = "{index} - \"{0}\" generate a box of type {1} with name \"{2}\"")
    @MethodSource
    public void parseJavaClassBox(String inputUnit, BoxType type, String boxName) {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromString(inputUnit));
        List<LiterateCodeMapBox> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(1));
        LiterateCodeMapBox firstUnit = units.get(0);
        assertThat(firstUnit.getType(), is(type));
        assertThat(firstUnit.getName(), is(boxName));
    }

    private static Stream<Arguments> parseJavaClassBox() {
        return Stream.of(
                Arguments.of("public class TestClass { }", BoxType.JAVA_CLASS, "TestClass"),
                Arguments.of("public interface TestInterface { }", BoxType.JAVA_INTERFACE, "TestInterface"),
                Arguments.of("public enum TestEnum { A, B; }", BoxType.JAVA_ENUM, "TestEnum")
        );
    }

}
