package biz.brioschi.lcmgenerator.literatemap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType.JAVA_CLASS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

class BoxesFilterTest {

    @Test
    public void nullFilterDoesNotRemoveBox() {
        BoxesFilter boxesFilter = new BoxesFilter(null);
        assertThat(boxesFilter.filter(Arrays.asList(BOX1)), is(Arrays.asList(BOX1)));
        assertThat(boxesFilter.filter(Arrays.asList(BOX1, BOX2)), is(Arrays.asList(BOX1, BOX2)));
    }

    @Test
    public void filterNotValidBoxes() {
        BoxesFilter boxesFilter = new BoxesFilter(Arrays.asList("box_numero_2", "box_numero_3", "box_numero_4"));
        List<Box> result = boxesFilter.filter(Arrays.asList(BOX1, BOX2));
        assertThat(result, is(Arrays.asList(BOX2)));
    }

    @Test
    public void filterNotValidBoxConnections() {
        BoxesFilter boxesFilter = new BoxesFilter(Arrays.asList("box_numero_2", "box_numero_4"));
        List<Box> result = boxesFilter.filter(Arrays.asList(BOX1, BOX2));
        assertThat(result, hasSize(1));
        assertThat(result.get(0).getName(), is("box_numero_2"));
        assertThat(result.get(0).getConnections(), hasSize(1));
        assertThat(result.get(0).getConnections().get(0).getTargetBoxName(), is("box_numero_4"));
    }

    private static final Box BOX1 = Box.builder()
            .name("box_numero_1")
            .type(JAVA_CLASS)
            .connections(new ArrayList<>())
            .build();
    private static final Box BOX2 = Box.builder()
            .name("box_numero_2")
            .type(JAVA_CLASS)
            .connections(
                    Arrays.asList(
                            BoxConnection.generateExtends("box_numero_3"),
                            BoxConnection.generateExtends("box_numero_4")
                    )
            ).build();

}