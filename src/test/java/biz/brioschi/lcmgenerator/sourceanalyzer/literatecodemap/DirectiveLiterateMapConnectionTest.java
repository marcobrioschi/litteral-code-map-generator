package biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap;

import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import biz.brioschi.lcmgenerator.literatemap.directives.Directive;
import biz.brioschi.lcmgenerator.literatemap.directives.LiterateMapConnection;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class DirectiveLiterateMapConnectionTest {

    @Test
    @Disabled
    public void parseLiterateMapInvoke() {
        String inputString = "// This is a comment LiterateMapConnection(10, 'TargetNode', 'Description') end of the comment";
        List<Directive> directives = DirectivesRecognizer.extractDirectives(inputString);

        assertThat(directives, hasSize(1));
        assertThat(directives, instanceOf(LiterateMapConnection.class));
        LiterateMapConnection directive = ((LiterateMapConnection)directives.get(0));
        BoxConnection boxConnection = directive.generateBoxConnection();
        assertThat(boxConnection.getType(), is(BoxConnection.ConnectionType.INVOKE));
        // TODO add the number position
        assertThat(boxConnection.getTargetBoxName(), is("TargetNode"));
        assertThat(boxConnection.getDescription(), is("Description"));
    }

}
