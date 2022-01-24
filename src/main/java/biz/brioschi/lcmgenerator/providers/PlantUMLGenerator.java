package biz.brioschi.lcmgenerator.providers;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.IOException;
import java.io.OutputStream;

public class PlantUMLGenerator {

    public void generateSVGImage(String literateCodeMapDefinition, OutputStream os) throws IOException {
        SourceStringReader reader = new SourceStringReader(literateCodeMapDefinition);
        reader.generateImage(os, new FileFormatOption(FileFormat.SVG)); // TODO update the deprecated method
    }

}
