package biz.brioschi.lcmgenerator.diagram.builders;

import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

class PlantUMLBuilderJavaBoxTest {

    // TODO evaluate code optimization

    @Test
    public void addJavaClassBox() {

        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.addLiterateCodeMapBox(
                new LiterateCodeMapBox(
                        LiterateCodeMapBox.BoxType.JAVA_CLASS,
                        "ClassName"
                )
        );
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();

        assertThat(result, containsString("\nclass ClassName\n"));

    }

    @Test
    public void addJavaInterfaceBox() {

        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.addLiterateCodeMapBox(
                new LiterateCodeMapBox(
                        LiterateCodeMapBox.BoxType.JAVA_INTERFACE,
                        "InterfaceName"
                )
        );
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();

        assertThat(result, containsString("\ninterface InterfaceName\n"));

    }

    @Test
    public void addJavaEnumBox() {

        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        plantUMLBuilder.addLiterateCodeMapBox(
                new LiterateCodeMapBox(
                        LiterateCodeMapBox.BoxType.JAVA_ENUM,
                        "EnumName"
                )
        );
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();

        assertThat(result, containsString("\nenum EnumName\n"));

    }

    @Test
    public void testAllEnumTypeValues() {
        PlantUMLBuilder plantUMLBuilder = new PlantUMLBuilder();
        plantUMLBuilder.startDocument("", "");
        for (LiterateCodeMapBox.BoxType currentType : LiterateCodeMapBox.BoxType.values()) {
            plantUMLBuilder.addLiterateCodeMapBox(
                    new LiterateCodeMapBox(
                            currentType,
                            "GenericName"
                    )
            );
        }
        plantUMLBuilder.endDocument();
        String result = plantUMLBuilder.getDiagramDescription();
        assertThat(true, is(true));
    }

}