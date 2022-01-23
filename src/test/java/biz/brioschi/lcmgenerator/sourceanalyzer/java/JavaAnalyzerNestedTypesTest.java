package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.diagram.BoxConnection;
import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;
import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox.BoxType;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static biz.brioschi.lcmgenerator.diagram.BoxConnection.ConnectionType.EXTENDS;
import static biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox.BoxType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JavaAnalyzerNestedTypesTest {

    @Test
    public void testNestedTypeNoExtensions() throws IOException {
        String inputUnit = "src/test/resources/boxconnections/NestedTypeNoExtensions.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<LiterateCodeMapBox> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(7));
        checkExpectedBox(units, JAVA_CLASS, "Level_1");
        checkExpectedBox(units, JAVA_CLASS, "Level_1_1");
        checkExpectedBox(units, JAVA_CLASS, "Level_1_1_1");
        checkExpectedBox(units, JAVA_INTERFACE, "Level_1_1_2");
        checkExpectedBox(units, JAVA_ENUM, "Level_1_1_3");
        checkExpectedBox(units, JAVA_INTERFACE, "Level_1_2");
        checkExpectedBox(units, JAVA_ENUM, "Level_1_3");
    }

    @Test
    public void testNestedTypeWithExtensions() throws IOException {
        String inputUnit = "src/test/resources/boxconnections/NestedTypeWithExtensions.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<LiterateCodeMapBox> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(7));
        checkExpectedBox(units, JAVA_CLASS, "Level_1", "C1");
        checkExpectedBox(units, JAVA_CLASS, "Level_1_1", "I1", "I2");
        checkExpectedBox(units, JAVA_CLASS, "Level_1_1_1", "C2", "I3", "I4");
        checkExpectedBox(units, JAVA_INTERFACE, "Level_1_1_2", "I5");
        checkExpectedBox(units, JAVA_ENUM, "Level_1_1_3", "I8", "I9");
        checkExpectedBox(units, JAVA_INTERFACE, "Level_1_2", "I6", "I7");
        checkExpectedBox(units, JAVA_ENUM, "Level_1_3", "I10");
    }

    private LiterateCodeMapBox buildExpectedBox(BoxType expectedType, String expectedName, String[] expectedConnectionNames) {
        List<BoxConnection> expectedConnections = new ArrayList<>();
        for (String expectedConnectionName : expectedConnectionNames) {
            expectedConnections.add(new BoxConnection(EXTENDS, expectedConnectionName));
        }
        LiterateCodeMapBox expectedBox = LiterateCodeMapBox.builder()
                .type(expectedType)
                .name(expectedName)
                .connections(expectedConnections)
                .build();
        return expectedBox;
    }

    private void checkExpectedBox(
            List<LiterateCodeMapBox> boxes,
            BoxType expectedType,
            String expectedName,
            String... expectedConnectionNames
    ) {
        LiterateCodeMapBox expectedBox = buildExpectedBox(expectedType, expectedName, expectedConnectionNames);
        assertThat(boxes, hasItems(expectedBox));
    }

}
