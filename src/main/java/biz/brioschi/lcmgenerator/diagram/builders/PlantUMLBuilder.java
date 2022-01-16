package biz.brioschi.lcmgenerator.diagram.builders;

import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;

public class PlantUMLBuilder implements DiagramBuilder {

    private final StringBuffer diagramDescription;

    public PlantUMLBuilder() {
        this.diagramDescription = new StringBuffer();
    }

    @Override
    public void startDocument(String title, String description) {
        this.diagramDescription.append("@startuml\n");
        if ((title != null) && !title.equals("")) {
            this.diagramDescription.append("title \"").append(title).append("\"\n");
        }
        if ((description != null) && !description.equals("")) {
            this.diagramDescription.append("caption \"").append(description).append("\"\n");
        }
    }

    @Override
    public void addLiterateCodeMapBox(LiterateCodeMapBox box) {

    }

    @Override
    public void endDocument() {
        this.diagramDescription.append("@enduml");
    }

    @Override
    public String getDiagramDescription() {
        return this.diagramDescription.toString();
    }

}
