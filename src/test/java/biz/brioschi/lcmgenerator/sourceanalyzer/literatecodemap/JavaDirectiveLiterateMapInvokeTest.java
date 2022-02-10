package biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap;

import biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox;
import biz.brioschi.lcmgenerator.sourceanalyzer.java.JavaAnalyzer;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox.BoxType.*;
import static biz.brioschi.lcmgenerator.util.LiterateCodeMapBoxHelper.generateLiterateCodeMapBox;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class JavaDirectiveLiterateMapInvokeTest {

    @Test
    public void LiterateMapInvoke() throws IOException {
        String inputUnit = "src/test/resources/biz/brioschi/lcmgenerator/sourceanalyzer/literatecodemap/LiterateMapInvoke.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<LiterateCodeMapBox> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(1));
        assertThat(
                units,
                hasItems(
                        generateLiterateCodeMapBox(
                                JAVA_CLASS,
                                "TestClass",
                                new String[]{
                                        "1:ClassA:MethodA()",
                                        "2:ClassB:MethodB()",
                                        "3:ClassC:MethodC()",
                                        "4:ClassD:MethodD()",
                                }
                        )
                )
        );
    }

}
