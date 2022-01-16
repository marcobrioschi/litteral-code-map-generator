package biz.brioschi.lcmgenerator.diagram.builders;

import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

class PlantUMLBuilderJavaBoxTest {

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
    @Disabled
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

}