package biz.brioschi.lcmgenerator.builders;

import org.junit.jupiter.api.Test;

import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType;
import static biz.brioschi.lcmgenerator.literatemap.BoxConnection.ConnectionType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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

    @Test
    public void testElementGenerationOrder() {
        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_INTERFACE, "Interface1");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "Class3");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "Class2");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "Class1");
        plantUMLBuilder.addLiterateCodeMapConnection("Class2", "Interface1", ConnectionType.EXTENDS, null, null);
        plantUMLBuilder.addLiterateCodeMapConnection("Class1", "Interface1", ConnectionType.EXTENDS, null, null);
        plantUMLBuilder.addLiterateCodeMapConnection(
                "Class2",
                "Class3",
                ConnectionType.INVOKE,
                2,
                "Description2"
        );
        plantUMLBuilder.addLiterateCodeMapConnection(
                "Class1",
                "Class3",
                ConnectionType.INVOKE,
                1,
                "Description1"
        );
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getLiterateCodeMaoDescription();

        assertThat(result, startsWith("@startuml\nClass1 --> Class3 : 1. Description1\nClass2 --> Class3 : 2. Description2"));

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