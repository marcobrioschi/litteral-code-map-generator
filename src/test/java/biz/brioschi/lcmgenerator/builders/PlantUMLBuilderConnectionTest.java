package biz.brioschi.lcmgenerator.builders;

import biz.brioschi.lcmgenerator.builders.PlantUMLBuilder;
import org.junit.jupiter.api.Test;

import static biz.brioschi.lcmgenerator.literatemap.BoxConnection.ConnectionType;
import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

class PlantUMLBuilderConnectionTest {

    @Test
    public void generateExtension() {

        String result = generatePlantUMLDocumentUsingConnectionBoxInfo(
                "ClassNameSource",
                "ClassNameTarget",
                ConnectionType.EXTENDS,
                null,
                null
        );

        assertThat(result, containsString("\nClassNameTarget <|-- ClassNameSource\n"));
    }

    @Test
    public void generateCallWithNoNumberAndNoDescription() {

        String result = generatePlantUMLDocumentUsingConnectionBoxInfo(
                "ClassNameSource",
                "ClassNameTarget",
                ConnectionType.INVOKE,
                null,
                null
        );

        assertThat(result, containsString("\nClassNameSource --> ClassNameTarget\n"));

    }

    @Test
    public void generateCallWithNumberAndNoDescription() {

        String result = generatePlantUMLDocumentUsingConnectionBoxInfo(
                "ClassNameSource",
                "ClassNameTarget",
                ConnectionType.INVOKE,
                347,
                null
        );

        assertThat(result, containsString("\nClassNameSource --> ClassNameTarget : 347.\n"));

    }

    @Test
    public void generateCallWithNoNumberAndDescription() {

        String result = generatePlantUMLDocumentUsingConnectionBoxInfo(
                "ClassNameSource",
                "ClassNameTarget",
                ConnectionType.INVOKE,
                null,
                "connectionDescription"
        );

        assertThat(result, containsString("\nClassNameSource --> ClassNameTarget : connectionDescription\n"));

    }

    @Test
    public void generateCallWithNumberAndDescription() {

        String result = generatePlantUMLDocumentUsingConnectionBoxInfo(
                "ClassNameSource",
                "ClassNameTarget",
                ConnectionType.INVOKE,
                123,
                "connectionDescription"
        );

        assertThat(result, containsString("\nClassNameSource --> ClassNameTarget : 123. connectionDescription\n"));

    }

    private String generatePlantUMLDocumentUsingConnectionBoxInfo(
            String sourceBoxName,
            String targetBoxName,
            ConnectionType connectionType,
            Integer progressiveNumber,
            String description
    ) {
        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "ClassNameSource");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "ClassNameTarget");
        plantUMLBuilder.addLiterateCodeMapConnection(sourceBoxName, targetBoxName, connectionType, progressiveNumber, description);
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getLiterateCodeMaoDescription();
        return result;
    }

    @Test
    public void testAllEnumTypeValues() {
        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "A");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "B");
        for (ConnectionType currentType : ConnectionType.values()) {
            plantUMLBuilder.addLiterateCodeMapConnection("A", "B", currentType, 1001,null);
        }
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getLiterateCodeMaoDescription();
        assertThat(true, is(true));
    }

}