package biz.brioschi.lcmgenerator.builders;

import biz.brioschi.lcmgenerator.literatemap.BoxConnection.ConnectionType;

import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType;

public class PlantUMLBuilder implements LiterateCodeBuilder {

    private final StringBuffer plantUMLLiterateCodeMapDefinition;

    public PlantUMLBuilder() {
        this.plantUMLLiterateCodeMapDefinition = new StringBuffer();
    }

    @Override
    public void startDocument(String title, String description) {
        this.plantUMLLiterateCodeMapDefinition.append("@startuml\n");
        if ((title != null) && !title.equals("")) {
            this.plantUMLLiterateCodeMapDefinition.append("title \"").append(title).append("\"\n");
        }
        if ((description != null) && !description.equals("")) {
            this.plantUMLLiterateCodeMapDefinition.append("caption \"").append(description).append("\"\n");
        }
    }

    @Override
    public void addLiterateCodeMapBox(BoxType boxType, String boxName) {
        switch(boxType) {
            case JAVA_CLASS:
                this.plantUMLLiterateCodeMapDefinition.append("class ").append(boxName).append("\n");
                break;
            case JAVA_INTERFACE:
                this.plantUMLLiterateCodeMapDefinition.append("interface ").append(boxName).append("\n");
                break;
            case JAVA_ENUM:
                this.plantUMLLiterateCodeMapDefinition.append("enum ").append(boxName).append("\n");
                break;
            default:
                throw new RuntimeException("Missing case for enum value: " + boxType);
        }
    }

    @Override
    public void addLiterateCodeMapConnection(String sourceBox, String destinationBox, ConnectionType connectionType, Integer progressiveNumber, String description) {
        switch(connectionType) {
            case EXTENDS:
                this.plantUMLLiterateCodeMapDefinition.append(destinationBox).append(" <|-- ").append(sourceBox).append("\n");
                break;
            case INVOKE:
                this.plantUMLLiterateCodeMapDefinition.append(sourceBox).append(" --> ").append(destinationBox);
                if ( (progressiveNumber != null) || (description !=null && !description.equals("")) ) {
                    this.plantUMLLiterateCodeMapDefinition.append(" :");
                    if (progressiveNumber != null) {
                        this.plantUMLLiterateCodeMapDefinition.append(" ").append(progressiveNumber.toString()).append(".");
                    }
                    if (description !=null && !description.equals("")) {
                        this.plantUMLLiterateCodeMapDefinition.append(" ").append(description);
                    }
                }
                this.plantUMLLiterateCodeMapDefinition.append("\n");
                break;
            default:
                throw new RuntimeException("Missing case for enum value: " + connectionType);
        }
    }

    @Override
    public void endDocument() {
        this.plantUMLLiterateCodeMapDefinition.append("@enduml");
    }

    @Override
    public String getLiterateCodeMaoDescription() {
        return this.plantUMLLiterateCodeMapDefinition.toString();
    }

}
