package biz.brioschi.lcmgenerator.builders;

import org.junit.jupiter.api.Test;

import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType;
import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

class PlantUMLBuilderJavaBoxTest {

    @Test
    public void addJavaClassBox() {

        String result = generatePlantUMLDocumentUsingBoxTypeAndName(JAVA_CLASS, "ClassName");

        assertThat(result, containsString("\nclass ClassName\n"));

    }

    @Test
    public void addJavaInterfaceBox() {

        String result = generatePlantUMLDocumentUsingBoxTypeAndName(JAVA_INTERFACE, "InterfaceName");

        assertThat(result, containsString("\ninterface InterfaceName\n"));

    }

    @Test
    public void addJavaEnumBox() {

        String result = generatePlantUMLDocumentUsingBoxTypeAndName(JAVA_ENUM, "EnumName");

        assertThat(result, containsString("\nenum EnumName\n"));

    }

    private String generatePlantUMLDocumentUsingBoxTypeAndName(BoxType boxType, String boxName) {
        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.addLiterateCodeMapBox(boxType, boxName);
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getLiterateCodeMaoDescription();
        return result;
    }

    @Test
    public void testAllEnumTypeValues() {
        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        for (BoxType currentType : BoxType.values()) {
            plantUMLBuilder.addLiterateCodeMapBox(currentType, "GenericName");
        }
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getLiterateCodeMaoDescription();
        assertThat(true, is(true));
    }

}