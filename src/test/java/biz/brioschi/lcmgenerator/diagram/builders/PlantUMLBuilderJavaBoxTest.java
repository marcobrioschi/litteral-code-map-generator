package biz.brioschi.lcmgenerator.diagram.builders;

import org.junit.jupiter.api.Test;

import static biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox.BoxType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

class PlantUMLBuilderJavaBoxTest {

    // TODO evaluate code optimization

    @Test
    public void addJavaClassBox() {

        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_CLASS, "ClassName");
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();

        assertThat(result, containsString("\nclass ClassName\n"));

    }

    @Test
    public void addJavaInterfaceBox() {

        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_INTERFACE, "InterfaceName");
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();

        assertThat(result, containsString("\ninterface InterfaceName\n"));

    }

    @Test
    public void addJavaEnumBox() {

        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.addLiterateCodeMapBox(BoxType.JAVA_ENUM, "EnumName");
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();

        assertThat(result, containsString("\nenum EnumName\n"));

    }

    @Test
    public void testAllEnumTypeValues() {
        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        for (BoxType currentType : BoxType.values()) {
            plantUMLBuilder.addLiterateCodeMapBox(currentType, "GenericName");
        }
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();
        assertThat(true, is(true));
    }

}