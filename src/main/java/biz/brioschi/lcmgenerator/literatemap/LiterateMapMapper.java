package biz.brioschi.lcmgenerator.literatemap;

import biz.brioschi.lcmgenerator.literatemap.builders.DiagramBuilder;

import java.util.List;

public class LiterateMapMapper {

    private final DiagramBuilder diagramBuilder;

    public LiterateMapMapper(DiagramBuilder diagramBuilder) {
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
                        connection.getType(),
                        connection.getDescription());
            }
        }
        this.diagramBuilder.endDocument();
    }

}
