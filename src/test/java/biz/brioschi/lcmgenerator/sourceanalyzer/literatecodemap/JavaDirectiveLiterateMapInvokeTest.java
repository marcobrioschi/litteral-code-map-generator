package biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap;

import biz.brioschi.lcmgenerator.directives.Directive;
import biz.brioschi.lcmgenerator.directives.LiterateMapInvoke;
import biz.brioschi.lcmgenerator.literatemap.Box;
import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import biz.brioschi.lcmgenerator.sourceanalyzer.java.JavaAnalyzer;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType.*;
import static biz.brioschi.lcmgenerator.util.LiterateCodeMapBoxHelper.generateLiterateCodeMapBox;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JavaDirectiveLiterateMapInvokeTest {

    @ParameterizedTest(name = "{index} - \"{0}\"")
    @MethodSource
    public void LiterateMapInvoke2(String sourceText, LiterateMapInvoke targetDirective) {
        List<Directive> directives = DirectivesRecognizer.extractDirectives(sourceText);
        assertThat(directives, hasSize(1));
        assertThat(directives.get(0), instanceOf(LiterateMapInvoke.class));
        LiterateMapInvoke firstDirective = (LiterateMapInvoke) directives.get(0);
        assertThat(firstDirective, is(targetDirective));
    }

    private static Stream<Arguments> LiterateMapInvoke2() {
        return Stream.of(
                Arguments.of(
                        "/* This is the first invocation: @LiterateMapInvoke(1, \"ClassA\", \"MethodA()\") */",
                        new LiterateMapInvoke(1, "ClassA", "MethodA()")
                ),
                Arguments.of(
                        "// @LiterateMapInvoke(2, \"ClassB\", \"MethodB()\") the value is taken from ClassB",
                        new LiterateMapInvoke(2, "ClassB", "MethodB()")
                ),
                Arguments.of(
                        " // This method delegate @LiterateMapInvoke(3, 'ClassC', 'MethodC()')",
                        new LiterateMapInvoke(3, "ClassC", "MethodC()")
                ),
                Arguments.of(
                        "/* But not this method @LiterateMapInvoke(4, 'ClassD', 'MethodD()') */",
                        new LiterateMapInvoke(4, "ClassD", "MethodD()")
                )
        );
    }

}
