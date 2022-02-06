package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox.BoxType.JAVA_CLASS;
import static biz.brioschi.lcmgenerator.util.LiterateCodeMapBoxHelper.generateLiterateCodeMapBox;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JavaAnalyzerMethodCallsTest {

    // TODO nested classes
    // TODO handle only static invocation filtering other expressions

    @Test
    @Disabled
    public void NestedTypes() throws IOException {
        String inputUnit = "src/test/resources/biz/brioschi/lcmgenerator/sourceanalyzer/java/NestedTypeInvokeConnections.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<LiterateCodeMapBox> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(2));  // TODO
        assertThat(
                units,
                hasItems(
                        generateLiterateCodeMapBox(
                                JAVA_CLASS,
                                "Level_1",
                                new String[]{
                                        "TestDestinationClass:doSomething_A()",
                                        "TestDestinationClass:doSomething_B()",
                                        "TestDestinationClass:doSomething_C()",
                                        "TestDestinationClass:doSomething_D()",
                                }
                        )
                )
        );
        assertThat(
                units,
                hasItems(
                        generateLiterateCodeMapBox(
                                JAVA_CLASS,
                                "Level_1_1",
                                new String[]{
                                        "TestDestinationClass:doSomething_E()",
                                        "TestDestinationClass:doSomething_F()",
                                        "TestDestinationClass:doSomething_G()",
                                        "TestDestinationClass:doSomething_H()",
                                }
                        )
                )
        );
    }

}
