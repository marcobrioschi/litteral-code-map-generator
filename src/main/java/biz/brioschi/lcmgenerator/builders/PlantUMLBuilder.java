package biz.brioschi.lcmgenerator.builders;

import biz.brioschi.lcmgenerator.literatemap.BoxConnection.ConnectionType;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType;

public class PlantUMLBuilder implements LiterateCodeBuilder {

    private final StringBuffer plantUMLLiterateCodeMapDefinition;
    private final List<String> diagramEntries;
    private final Map<Integer, String> invokeConnections;

    public PlantUMLBuilder() {
        this.plantUMLLiterateCodeMapDefinition = new StringBuffer();
        this.diagramEntries = new ArrayList<>();
        this.invokeConnections = new Hashtable<>();
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
        StringBuffer boxDescription = new StringBuffer();
        switch(boxType) {
            case JAVA_CLASS:
                boxDescription.append("class ").append(boxName).append("\n");
                break;
            case JAVA_INTERFACE:
                boxDescription.append("interface ").append(boxName).append("\n");
                break;
            case JAVA_ENUM:
                boxDescription.append("enum ").append(boxName).append("\n");
                break;
            default:
                throw new RuntimeException("Missing case for enum value: " + boxType);
        }
        this.diagramEntries.add(boxDescription.toString());
    }

    @Override
    public void addLiterateCodeMapConnection(String sourceBox, String destinationBox, ConnectionType connectionType, Integer progressiveNumber, String description) {
        StringBuffer connectionDescription = new StringBuffer();
        switch(connectionType) {
            case EXTENDS:
                connectionDescription.append(destinationBox).append(" <|-- ").append(sourceBox).append("\n");
                this.diagramEntries.add(connectionDescription.toString());
                break;
            case INVOKE:
                connectionDescription.append(sourceBox).append(" --> ").append(destinationBox);
                if ( (progressiveNumber != null) || (description !=null && !description.equals("")) ) {
                    connectionDescription.append(" :");
                    if (progressiveNumber != null) {
                        connectionDescription.append(" ").append(progressiveNumber.toString()).append(".");
                    }
                    if (description !=null && !description.equals("")) {
                        connectionDescription.append(" ").append(description);
                    }
                }
                connectionDescription.append("\n");
                if (progressiveNumber != null) {
                    this.invokeConnections.put(progressiveNumber, connectionDescription.toString());
                } else {
                    this.diagramEntries.add(connectionDescription.toString());
                }
                break;
            default:
                throw new RuntimeException("Missing case for enum value: " + connectionType);
        }
    }

    @Override
    public void endDocument() {
        for (Integer i : this.invokeConnections.keySet().stream().sorted().collect(Collectors.toList())) {
            this.plantUMLLiterateCodeMapDefinition.append(this.invokeConnections.get(i));
        }
        for (String s : this.diagramEntries) {
            this.plantUMLLiterateCodeMapDefinition.append(s);
        }
        this.plantUMLLiterateCodeMapDefinition.append("@enduml");
    }

    @Override
    public String getLiterateCodeMaoDescription() {
        return this.plantUMLLiterateCodeMapDefinition.toString();
    }

}
