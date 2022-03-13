package biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap;

import biz.brioschi.lcmgenerator.literatemap.Box;
import biz.brioschi.lcmgenerator.sourceanalyzer.java.JavaAnalyzer;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType.JAVA_CLASS;
import static biz.brioschi.lcmgenerator.util.LiterateCodeMapBoxHelper.generateLiterateCodeMapBox;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class JavaDirectiveLiterateMapBlockTest {

    // TODO: wrong test, I need only to recognize the directive in a text, not to face with the Java syntax

    @Test
    @Disabled
    public void LiterateMapBlock() throws IOException {
        String inputUnit = "src/test/resources/biz/brioschi/lcmgenerator/sourceanalyzer/literatecodemap/LiterateMapBlock.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<Box> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(1));
        assertThat(
                units,
                hasItems(
                        generateLiterateCodeMapBox(
                                JAVA_CLASS,
                                "TestBlock",
                                new String[]{
                                        "void TestClass()#this.avar = avalue;",
                                        "void doSomething(int number, String string)#number = string;"
                                }
                        )
                )
        );
    }

}
