package biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap;

import biz.brioschi.lcmgenerator.directives.Directive;
import biz.brioschi.lcmgenerator.directives.LiterateMapBlock;
import biz.brioschi.lcmgenerator.directives.LiterateMapInvoke;
import biz.brioschi.lcmgenerator.literatemap.Box;
import biz.brioschi.lcmgenerator.sourceanalyzer.java.JavaAnalyzer;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

public class JavaDirectiveLiterateMapBlockTest {

    @ParameterizedTest(name = "{index} - \"{0}\"")
    @MethodSource
    public void LiterateMapBlock(String sourceText, LiterateMapBlock targetDirective) {
        List<Directive> directives = DirectivesRecognizer.extractDirectives(sourceText);
        assertThat(directives, hasSize(1));
        assertThat(directives.get(0), instanceOf(LiterateMapBlock.class));
        LiterateMapBlock firstDirective = (LiterateMapBlock) directives.get(0);
        assertThat(firstDirective, is(targetDirective));
    }

    private static Stream<Arguments> LiterateMapBlock() {
        return Stream.of(
                Arguments.of(
                        "/* With a text before @LiterateMapBlock() */",
                        new LiterateMapBlock()
                ),
                Arguments.of(
                        "/* @LiterateMapBlock() and with a text after*/",
                        new LiterateMapBlock()
                ),
                Arguments.of(
                        "// With a text before @LiterateMapBlock()",
                        new LiterateMapBlock()
                ),
                Arguments.of(
                        "// @LiterateMapBlock() with a text after!!!",
                        new LiterateMapBlock()
                )
        );
    }

}
