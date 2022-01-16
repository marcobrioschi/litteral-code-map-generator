package biz.brioschi.lcmgenerator.diagram.builders;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PlantUMLBuilderTitleDescriptionTest {

    @Test
    public void composeBaseDiagram() {

        String result = generatePlantUMLDocumentUsingThisTitleAndDescription("", "");

        assertThat(result, startsWith("@startuml\n"));
        assertThat(result, endsWith("\n@enduml"));

    }

    @Test
    public void showTheTitleInTheDiagram() {

        String result = generatePlantUMLDocumentUsingThisTitleAndDescription("This is a title", "");

        assertThat(result, containsString("\ntitle \"This is a title\"\n"));

    }

    @Test
    public void showTheDescriptionInTheDiagram() {

        String result = generatePlantUMLDocumentUsingThisTitleAndDescription("", "This is a description");

        assertThat(result, containsString("\ncaption \"This is a description\"\n"));

    }

    @Test
    public void dontShowEmptyTitleOrEmptyDescriptionInTheDiagram() {

        String result = generatePlantUMLDocumentUsingThisTitleAndDescription("", "");

        assertThat(result, not(containsString("title")));
        assertThat(result, not(containsString("caption")));

    }

    private String generatePlantUMLDocumentUsingThisTitleAndDescription(String title, String description) {
        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument(title, description);
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();
        return result;
    }

}