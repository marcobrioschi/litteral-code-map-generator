package biz.brioschi.lcmgenerator.literatemap.builders;

import biz.brioschi.lcmgenerator.literatemap.BoxConnection.ConnectionType;

import static biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox.BoxType;

public class PlantUMLBuilder implements LiterateCodeBuilder {

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
    public void addLiterateCodeMapBox(BoxType boxType, String boxName) {
        switch(boxType) {
            case JAVA_CLASS:
                this.diagramDescription.append("class ").append(boxName).append("\n");
                break;
            case JAVA_INTERFACE:
                this.diagramDescription.append("interface ").append(boxName).append("\n");
                break;
            case JAVA_ENUM:
                this.diagramDescription.append("enum ").append(boxName).append("\n");
                break;
            default:
                throw new RuntimeException("Missing case for enum value: " + boxType);
        }
    }

    @Override
    public void addLiterateCodeMapConnection(String sourceBox, String destinationBox, ConnectionType connectionType, String description) {
        switch(connectionType) {
            case EXTENDS:
                this.diagramDescription.append(destinationBox).append(" <|-- ").append(sourceBox).append("\n");
                break;
            case INVOKE:
                this.diagramDescription.append(sourceBox).append(" --> ").append(destinationBox);
                if (description !=null && !description.equals("")) {
                    this.diagramDescription.append(" : ").append(description);
                }
                this.diagramDescription.append("\n");
                break;
            default:
                throw new RuntimeException("Missing case for enum value: " + connectionType);
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
