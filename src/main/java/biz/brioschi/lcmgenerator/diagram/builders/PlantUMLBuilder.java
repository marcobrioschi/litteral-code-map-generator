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
        switch(box.getType()) { // TODO add a test that check all the cases
            case JAVA_CLASS:
                this.diagramDescription.append("class ").append(box.getName()).append("\n");
                break;
            case JAVA_INTERFACE:
                this.diagramDescription.append("interface ").append(box.getName()).append("\n");
                break;
            case JAVA_ENUM:
                break;
        }
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
