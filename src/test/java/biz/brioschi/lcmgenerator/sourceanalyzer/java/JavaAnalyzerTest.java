package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.diagram.BoxConnection;
import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox.BoxType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JavaAnalyzerTest {

    @ParameterizedTest(name = "{index} - \"{0}\" generate a box of type {1} with name \"{2}\"")
    @MethodSource
    public void parseJavaBox(String inputUnit, BoxType type, String boxName) {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromString(inputUnit));
        List<LiterateCodeMapBox> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(1));
        LiterateCodeMapBox firstUnit = units.get(0);
        assertThat(firstUnit.getType(), is(type));
        assertThat(firstUnit.getName(), is(boxName));
        assertThat(firstUnit.getConnections(), hasSize(0));
    }

    private static Stream<Arguments> parseJavaBox() {
        return Stream.of(
                Arguments.of("public class TestClass { }", BoxType.JAVA_CLASS, "TestClass"),
                Arguments.of("public interface TestInterface { }", BoxType.JAVA_INTERFACE, "TestInterface"),
                Arguments.of("public enum TestEnum { A, B; }", BoxType.JAVA_ENUM, "TestEnum")
        );
    }

    @ParameterizedTest(name = "{index} - \"{0}\" the extensions are \"{1}\"")
    @MethodSource
    public void parseClassExtensions(String inputUnit, BoxConnection[] connections) {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromString(inputUnit));
        List<LiterateCodeMapBox> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(1));
        LiterateCodeMapBox firstUnit = units.get(0);
        assertThat(firstUnit.getType(), is(BoxType.JAVA_CLASS));
        assertThat(firstUnit.getName(), is("Test4"));
        assertThat(firstUnit.getConnections(), contains(connections));
    }

    private static Stream<Arguments> parseClassExtensions() {
        return Stream.of(
                Arguments.of(
                        "class Test4 extends Test1 { }",
                        new BoxConnection[] {
                                new BoxConnection(BoxConnection.ConnectionType.EXTENDS, "Test1")
                        }
                ),
                Arguments.of(
                        "class Test4 implements Test3 { }",
                        new BoxConnection[] {
                                new BoxConnection(BoxConnection.ConnectionType.EXTENDS, "Test3")
                        }
                ),
                Arguments.of(
                        "class Test4 implements Test2, Test1 { }",
                        new BoxConnection[] {
                                new BoxConnection(BoxConnection.ConnectionType.EXTENDS, "Test2"),
                                new BoxConnection(BoxConnection.ConnectionType.EXTENDS, "Test1")
                        }
                ),
                Arguments.of(
                        "class Test4 extends Test3 implements Test2, Test1 { }",
                        new BoxConnection[] {
                                new BoxConnection(BoxConnection.ConnectionType.EXTENDS, "Test3"),
                                new BoxConnection(BoxConnection.ConnectionType.EXTENDS, "Test2"),
                                new BoxConnection(BoxConnection.ConnectionType.EXTENDS, "Test1")
                        }
                )
        );
    }

}
