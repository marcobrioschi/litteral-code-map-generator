package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JavaAnalyzerMethodCallsTest {

    // TODO nested classes
    // TODO handle only static invocation filtering other expressions

    @Test
    @Disabled
    public void methodCalls() throws IOException {
        String inputUnit = "src/test/resources/boxconnections/MethodCall.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<LiterateCodeMapBox> units = javaAnalyzer.extractInfo();
        assertThat(true, is(false));
//        assertThat(units, hasSize(1));
//        LiterateCodeMapBox firstUnit = units.get(0);
//        assertThat(firstUnit.getType(), is(LiterateCodeMapBox.BoxType.JAVA_CLASS));
//        assertThat(firstUnit.getName(), is("StaticMethodCallClass"));
//        List<BoxConnection> firstUnitConnections = firstUnit.getConnections();
//        assertThat(firstUnitConnections, hasSize(2));
//        assertThat(firstUnitConnections.get(0).getType(), is(BoxConnection.ConnectionType.INVOKE));
//        assertThat(firstUnitConnections.get(0).getTargetBoxName(), is("GenericClassOne"));
//        assertThat(firstUnitConnections.get(0).getDescription(), is("firstStaticMethod(...)"));
//        assertThat(firstUnitConnections.get(1).getType(), is(BoxConnection.ConnectionType.INVOKE));
//        assertThat(firstUnitConnections.get(1).getTargetBoxName(), is("GenericClassTwo"));
//        assertThat(firstUnitConnections.get(1).getDescription(), is("secondStaticMethod(...)"));
    }

}
