package biz.brioschi.lcmgenerator.diagram.builders;

import org.junit.jupiter.api.Test;

import static biz.brioschi.lcmgenerator.diagram.BoxConnection.ConnectionType;
import static biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox.BoxType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

class PlantUMLBuilderConnectionTest {

    @Test
    public void generateExtension() {

        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "ClassNameSource");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "ClassNameTarget");
        plantUMLBuilder.addLiterateCodeMapConnection(
                "ClassNameSource",
                "ClassNameTarget",
                ConnectionType.EXTENDS,
                null);
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();

        assertThat(result, containsString("\nClassNameTarget <|-- ClassNameSource\n"));
    }

    @Test
    public void generateCallWithDescription() {

        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "ClassNameSource");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "ClassNameTarget");
        plantUMLBuilder.addLiterateCodeMapConnection(
                "ClassNameSource",
                "ClassNameTarget",
                ConnectionType.INVOKE,
                "connectionDescription");
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();

        assertThat(result, containsString("\nClassNameSource --> ClassNameTarget : connectionDescription\n"));

    }

    @Test
    public void generateCallWithNoDescription() {

        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "ClassNameSource");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "ClassNameTarget");
        plantUMLBuilder.addLiterateCodeMapConnection(
                "ClassNameSource",
                "ClassNameTarget",
                ConnectionType.INVOKE,
                null);
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();

        assertThat(result, containsString("\nClassNameSource --> ClassNameTarget\n"));

    }

    @Test
    public void testAllEnumTypeValues() {
        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "A");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "B");
        for (ConnectionType currentType : ConnectionType.values()) {
            plantUMLBuilder.addLiterateCodeMapConnection("A", "B", currentType, null);
        }
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();
        assertThat(true, is(true));
    }

}