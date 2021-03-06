package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import biz.brioschi.lcmgenerator.literatemap.Box;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JavaAnalyzerEnumExtensionTest {

    // TODO rifattorizzare l'helper

    @ParameterizedTest(name = "{index} - \"{0}\" the extensions are \"{1}\"")
    @MethodSource
    public void parseEnumExtensions(String inputUnit, BoxConnection[] connections) {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromString(inputUnit));
        List<Box> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(1));
        Box firstUnit = units.get(0);
        assertThat(firstUnit.getType(), is(Box.BoxType.JAVA_ENUM));
        assertThat(firstUnit.getName(), is("EnumX"));
        assertThat(firstUnit.getConnections(), contains(connections));
    }

    private static Stream<Arguments> parseEnumExtensions() {
        return Stream.of(
                Arguments.of(
                        "enum EnumX implements Interface1 { }",
                        new BoxConnection[] {
                                BoxConnection.generateExtends("Interface1")
                        }
                ),
                Arguments.of(
                        "enum EnumX implements Interface1, Interface2 { }",
                        new BoxConnection[] {
                                BoxConnection.generateExtends("Interface1"),
                                BoxConnection.generateExtends("Interface2")
                        }
                ),
                Arguments.of(
                        "enum EnumX implements Interface1, Interface2, Interface3 { }",
                        new BoxConnection[] {
                                BoxConnection.generateExtends("Interface1"),
                                BoxConnection.generateExtends("Interface2"),
                                BoxConnection.generateExtends("Interface3")
                        }
                )
        );
    }
}
