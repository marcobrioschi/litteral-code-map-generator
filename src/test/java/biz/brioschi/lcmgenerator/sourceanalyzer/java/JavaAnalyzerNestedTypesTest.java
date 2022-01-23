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
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class JavaAnalyzerNestedTypesTest {

    @Test
    public void testNestedTypeNoExtensions() throws IOException {
        String inputUnit = "src/test/resources/boxconnections/NestedTypeNoExtensions.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<LiterateCodeMapBox> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(7));
        ArrayList<BoxConnection> emptyConnectionList = new ArrayList<>();
        checkType(units.get(0), JAVA_CLASS, "Level_1", emptyConnectionList);
        checkType(units.get(1), JAVA_CLASS, "Level_1_1", emptyConnectionList);
        checkType(units.get(2), JAVA_CLASS, "Level_1_1_1", emptyConnectionList);
        checkType(units.get(3), JAVA_INTERFACE, "Level_1_1_2", emptyConnectionList);
        checkType(units.get(4), JAVA_ENUM, "Level_1_1_3", emptyConnectionList);
        checkType(units.get(5), JAVA_INTERFACE, "Level_1_2", emptyConnectionList);
        checkType(units.get(6), JAVA_ENUM, "Level_1_3", emptyConnectionList);
    }

    @Test
    public void testNestedTypeWithExtensions() throws IOException {
        String inputUnit = "src/test/resources/boxconnections/NestedTypeWithExtensions.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<LiterateCodeMapBox> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(7));
        checkType(units.get(0), JAVA_CLASS, "Level_1", generateBoxConnectionTestList("C1"));
        checkType(units.get(1), JAVA_CLASS, "Level_1_1", generateBoxConnectionTestList("I1", "I2"));
        checkType(units.get(2), JAVA_CLASS, "Level_1_1_1", generateBoxConnectionTestList("C2", "I3", "I4"));
        checkType(units.get(3), JAVA_INTERFACE, "Level_1_1_2", generateBoxConnectionTestList("I5"));
        checkType(units.get(4), JAVA_ENUM, "Level_1_1_3", generateBoxConnectionTestList("I8", "I9"));
        checkType(units.get(5), JAVA_INTERFACE, "Level_1_2", generateBoxConnectionTestList("I6", "I7"));
        checkType(units.get(6), JAVA_ENUM, "Level_1_3", generateBoxConnectionTestList("I10"));
    }

    private List<BoxConnection> generateBoxConnectionTestList(String... connectionNames) {
        List<BoxConnection> connections = new ArrayList<>();
        for (String connectionName : connectionNames) {
            connections.add(new BoxConnection(EXTENDS, connectionName));
        }
        return connections;
    }

    private void checkType(LiterateCodeMapBox box, BoxType boxType, String boxName, List<BoxConnection> boxConnections) {
        assertThat(box.getType(), is(boxType));
        assertThat(box.getName(), is(boxName));
        assertThat(box.getConnections(), is(boxConnections));
    }

}
