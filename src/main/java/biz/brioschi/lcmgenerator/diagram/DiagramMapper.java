package biz.brioschi.lcmgenerator.diagram;

import biz.brioschi.lcmgenerator.diagram.builders.DiagramBuilder;

import java.util.List;

public class DiagramMapper {

    private final DiagramBuilder diagramBuilder;

    public DiagramMapper(DiagramBuilder diagramBuilder) {
        this.diagramBuilder = diagramBuilder;
    }

    public void mapBoxes(String title, String description, List<LiterateCodeMapBox> boxes) {
        this.diagramBuilder.startDocument(title, description);
        for (LiterateCodeMapBox box : boxes) {
            this.diagramBuilder.addLiterateCodeMapBox(box.getType(), box.getName());
            for (BoxConnection connection : box.getConnections()) {
                this.diagramBuilder.addLiterateCodeMapConnection(
                        box.getName(),
                        connection.getTargetBoxName(),
                        connection.getType()
                );
            }
        }
        this.diagramBuilder.endDocument();
    }

}
