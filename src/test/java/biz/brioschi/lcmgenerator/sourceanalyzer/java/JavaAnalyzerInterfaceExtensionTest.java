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

public class JavaAnalyzerInterfaceExtensionTest {

    // TODO rifattorizzare l'helper

    @ParameterizedTest(name = "{index} - \"{0}\" the extensions are \"{1}\"")
    @MethodSource
    public void parseInterfaceExtensions(String inputUnit, BoxConnection[] connections) {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromString(inputUnit));
        List<Box> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(1));
        Box firstUnit = units.get(0);
        assertThat(firstUnit.getType(), is(Box.BoxType.JAVA_INTERFACE));
        assertThat(firstUnit.getName(), is("InterfaceX"));
        assertThat(firstUnit.getConnections(), contains(connections));
    }

    private static Stream<Arguments> parseInterfaceExtensions() {
        return Stream.of(
                Arguments.of(
                        "interface InterfaceX extends InterfaceA { }",
                        new BoxConnection[] {
                                BoxConnection.generateExtends("InterfaceA")
                        }
                ),
                Arguments.of(
                        "interface InterfaceX extends InterfaceA, InterfaceB { }",
                        new BoxConnection[] {
                                BoxConnection.generateExtends("InterfaceA"),
                                BoxConnection.generateExtends("InterfaceB")
                        }
                ),
                Arguments.of(
                        "interface InterfaceX extends InterfaceA, InterfaceB, InterfaceC { }",
                        new BoxConnection[] {
                                BoxConnection.generateExtends("InterfaceA"),
                                BoxConnection.generateExtends("InterfaceB"),
                                BoxConnection.generateExtends("InterfaceC")
                        }
                )
        );
    }
}
