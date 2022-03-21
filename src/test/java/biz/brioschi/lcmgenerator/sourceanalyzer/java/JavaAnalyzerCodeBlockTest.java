package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.literatemap.Box;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType.JAVA_CLASS;
import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType.JAVA_ENUM;
import static biz.brioschi.lcmgenerator.util.LiterateCodeMapBoxHelper.generateLiterateCodeMapBox;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JavaAnalyzerCodeBlockTest {

    @Test
    public void codeBlock() throws IOException {
        String inputUnit = "src/test/resources/biz/brioschi/lcmgenerator/sourceanalyzer/java/MethodCodeBlock.java";
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(CharStreams.fromFileName(inputUnit));
        List<Box> units = javaAnalyzer.extractInfo();
        assertThat(units, hasSize(2));
        assertThat(units, hasItem(
                generateLiterateCodeMapBox(
                        JAVA_CLASS,
                        "TestClass",
                        "void aCostructor(Integer integer)#this.string = integer.toString();",
                        "int aMethod(Integer integer)#return integer + 1;"
                )
        ));
        assertThat(units, hasItem(
                generateLiterateCodeMapBox(
                        JAVA_ENUM,
                        "TestEnum",
                        "String getEnumStringValue()#return null;",
                        "doSomething(Boolean value, String ... strings)#return;")
        ));
    }

}