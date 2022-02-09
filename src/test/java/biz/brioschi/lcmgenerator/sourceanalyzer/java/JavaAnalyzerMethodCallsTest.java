package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox.BoxType.*;
import static biz.brioschi.lcmgenerator.util.LiterateCodeMapBoxHelper.generateLiterateCodeMapBox;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class JavaAnalyzerMethodCallsTest {

    @Test
    public void NestedTypes() throws IOException {
        String inputUnit = "src/test/resources/biz/brioschi/lcmgenerator/sourceanalyzer/java/NestedTypeInvokeConnections.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<LiterateCodeMapBox> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(7));
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
        assertThat(
                units,
                hasItems(
                        generateLiterateCodeMapBox(
                                JAVA_CLASS,
                                "Level_1_1_1",
                                new String[]{
                                        "TestDestinationClass:doSomething_I()",
                                        "TestDestinationClass:doSomething_J()",
                                        "TestDestinationClass:doSomething_K()",
                                        "TestDestinationClass:doSomething_L()",
                                }
                        )
                )
        );
        assertThat(
                units,
                hasItems(
                        generateLiterateCodeMapBox(
                                JAVA_INTERFACE,
                                "Level_1_1_2",
                                new String[]{
                                        "TestDestinationClass:doSomething_M()",
                                        "TestDestinationClass:doSomething_N()",
                                        "TestDestinationClass:doSomething_O()",
                                        "TestDestinationClass:doSomething_P()",
                                }
                        )
                )
        );
        assertThat(
                units,
                hasItems(
                        generateLiterateCodeMapBox(
                                JAVA_ENUM,
                                "Level_1_1_3",
                                new String[]{
                                        "TestDestinationClass:doSomething_Q()",
                                        "TestDestinationClass:doSomething_R()",
                                        "TestDestinationClass:doSomething_S()",
                                        "TestDestinationClass:doSomething_T()",
                                }
                        )
                )
        );
        assertThat(
                units,
                hasItems(
                        generateLiterateCodeMapBox(
                                JAVA_INTERFACE,
                                "Level_1_2",
                                new String[]{
                                        "TestDestinationClass:doSomething_U()",
                                        "TestDestinationClass:doSomething_W()",
                                        "TestDestinationClass:doSomething_X()",
                                        "TestDestinationClass:doSomething_Y()",
                                }
                        )
                )
        );
        assertThat(
                units,
                hasItems(
                        generateLiterateCodeMapBox(
                                JAVA_ENUM,
                                "Level_1_3",
                                new String[]{
                                        "TestDestinationClass:doSomething_Z()",
                                        "TestDestinationClass:doSomething_1()",
                                        "TestDestinationClass:doSomething_2()",
                                        "TestDestinationClass:doSomething_3()",
                                }
                        )
                )
        );
    }

}
