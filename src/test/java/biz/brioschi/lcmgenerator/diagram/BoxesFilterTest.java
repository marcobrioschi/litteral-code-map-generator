package biz.brioschi.lcmgenerator.diagram;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static biz.brioschi.lcmgenerator.diagram.BoxConnection.ConnectionType.EXTENDS;
import static biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox.BoxType.JAVA_CLASS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BoxesFilterTest {

    @Test
    public void nullFilterDoesNotRemoveBox() {
        BoxesFilter boxesFilter = new BoxesFilter(null);
        assertThat(boxesFilter.filter(Arrays.asList(BOX1)), is(Arrays.asList(BOX1)));
        assertThat(boxesFilter.filter(Arrays.asList(BOX1, BOX2)), is(Arrays.asList(BOX1, BOX2)));
    }

    @Test
    @Disabled
    public void filterNotValidBoxes() {
        assertThat(true, is(false));
    }

    @Test
    @Disabled
    public void filterNotValidBoxConnections() {
        assertThat(true, is(false));
    }

    private static final LiterateCodeMapBox BOX1 = LiterateCodeMapBox.builder()
            .name("box_numero_1")
            .type(JAVA_CLASS)
            .connections(new ArrayList<>())
            .build();
    private static final LiterateCodeMapBox BOX2 = LiterateCodeMapBox.builder()
            .name("box_numero_2")
            .type(JAVA_CLASS)
            .connections(
                    Arrays.asList(
                            new BoxConnection(EXTENDS, "box_numero_3"),
                            new BoxConnection(EXTENDS, "box_numero_4")
                    )
            ).build();

}