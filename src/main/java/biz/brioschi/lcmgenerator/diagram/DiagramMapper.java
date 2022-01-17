package biz.brioschi.lcmgenerator.diagram;

import biz.brioschi.lcmgenerator.diagram.builders.DiagramBuilder;

import java.util.List;

public class DiagramMapper {

    private final DiagramBuilder diagramBuilder;

    public DiagramMapper(DiagramBuilder diagramBuilder) {
        this.diagramBuilder = diagramBuilder;
    }

    public void mapBoxes(List<LiterateCodeMapBox> boxes) {
        this.diagramBuilder.startDocument("", "");
        for (LiterateCodeMapBox box : boxes) {
            this.diagramBuilder.addLiterateCodeMapBox(box.getType(), box.getName());
        }
        this.diagramBuilder.endDocument();
    }

}
