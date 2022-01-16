package biz.brioschi.lcmgenerator.diagram.builders;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PlantUMLBuilderTest {

    @Test
    public void composeBaseDiagram() {

        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();

        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();

        assertThat(result, startsWith("@startuml\n"));
        assertThat(result, endsWith("\n@enduml"));

    }

    @Test
    public void showTheTitleInTheDiagram() {

        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();

        plantUMLBuilder.startDocument("This is a title", "");
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();

        assertThat(result, containsString("\ntitle \"This is a title\"\n"));

    }

    @Test
    public void showTheDescriptionInTheDiagram() {

        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();

        plantUMLBuilder.startDocument("", "This is a description");
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();

        assertThat(result, containsString("\ncaption \"This is a description\"\n"));

    }

    @Test
    public void dontShowEmptyTitleOrEmptyDescriptionInTheDiagram() {

        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();

        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();

        assertThat(result, not(containsString("title")));
        assertThat(result, not(containsString("caption")));

    }

}