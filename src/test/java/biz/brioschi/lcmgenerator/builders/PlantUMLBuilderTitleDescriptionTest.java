package biz.brioschi.lcmgenerator.builders;

import biz.brioschi.lcmgenerator.builders.PlantUMLBuilder;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PlantUMLBuilderTitleDescriptionTest {

    @Test
    public void composeBaseCodeMap() {

        String result = generatePlantUMLDocumentUsingThisTitleAndDescription("", "");

        assertThat(result, startsWith("@startuml\n"));
        assertThat(result, endsWith("\n@enduml"));

    }

    @Test
    public void showTheTitleInTheCodeMap() {

        String result = generatePlantUMLDocumentUsingThisTitleAndDescription("This is a title", "");

        assertThat(result, containsString("\ntitle \"This is a title\"\n"));

    }

    @Test
    public void showTheDescriptionInTheCodeMap() {

        String result = generatePlantUMLDocumentUsingThisTitleAndDescription("", "This is a description");

        assertThat(result, containsString("\ncaption \"This is a description\"\n"));

    }

    @Test
    public void dontShowEmptyTitleOrEmptyDescriptionInTheCodeMap() {

        String result = generatePlantUMLDocumentUsingThisTitleAndDescription("", "");

        assertThat(result, not(containsString("title")));
        assertThat(result, not(containsString("caption")));

    }

    private String generatePlantUMLDocumentUsingThisTitleAndDescription(String title, String description) {
        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument(title, description);
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getLiterateCodeMaoDescription();
        return result;
    }

}