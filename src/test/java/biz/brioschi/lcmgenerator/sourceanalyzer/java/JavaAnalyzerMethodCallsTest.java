package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.literatemap.Box;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType.*;
import static biz.brioschi.lcmgenerator.util.LiterateCodeMapBoxHelper.generateLiterateCodeMapBox;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class JavaAnalyzerMethodCallsTest {

    @Test
    public void NestedTypes() throws IOException {
        String inputUnit = "src/test/resources/biz/brioschi/lcmgenerator/sourceanalyzer/java/NestedTypeInvokeConnections.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<Box> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(7));
        assertThat(
                units,
                hasItems(
                        generateLiterateCodeMapBox(
                                JAVA_CLASS,
                                "Level_1",
                                new String[]{
                                        "0:TestDestinationClass:doSomething_A()",
                                        "0:TestDestinationClass:doSomething_B()",
                                        "0:TestDestinationClass:doSomething_C()",
                                        "0:TestDestinationClass:doSomething_D()",
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
                                        "0:TestDestinationClass:doSomething_E()",
                                        "0:TestDestinationClass:doSomething_F()",
                                        "0:TestDestinationClass:doSomething_G()",
                                        "0:TestDestinationClass:doSomething_H()",
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
                                        "0:TestDestinationClass:doSomething_I()",
                                        "0:TestDestinationClass:doSomething_J()",
                                        "0:TestDestinationClass:doSomething_K()",
                                        "0:TestDestinationClass:doSomething_L()",
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
                                        "0:TestDestinationClass:doSomething_M()",
                                        "0:TestDestinationClass:doSomething_N()",
                                        "0:TestDestinationClass:doSomething_O()",
                                        "0:TestDestinationClass:doSomething_P()",
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
                                        "0:TestDestinationClass:doSomething_Q()",
                                        "0:TestDestinationClass:doSomething_R()",
                                        "0:TestDestinationClass:doSomething_S()",
                                        "0:TestDestinationClass:doSomething_T()",
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
                                        "0:TestDestinationClass:doSomething_U()",
                                        "0:TestDestinationClass:doSomething_W()",
                                        "0:TestDestinationClass:doSomething_X()",
                                        "0:TestDestinationClass:doSomething_Y()",
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
                                        "0:TestDestinationClass:doSomething_Z()",
                                        "0:TestDestinationClass:doSomething_1()",
                                        "0:TestDestinationClass:doSomething_2()",
                                        "0:TestDestinationClass:doSomething_3()",
                                }
                        )
                )
        );
    }

}
