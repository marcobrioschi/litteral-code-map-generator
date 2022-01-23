package biz.brioschi.lcmgenerator.diagram.builders;

import biz.brioschi.lcmgenerator.diagram.BoxConnection.ConnectionType;
import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;

public interface DiagramBuilder {

    void startDocument(String title, String description);

    void addLiterateCodeMapBox(LiterateCodeMapBox.BoxType boxType, String boxName);

    void addLiterateCodeMapConnection(String sourceBox, String destinationBox, ConnectionType connectionType, String description);

    void endDocument();

    String getDiagramDescription();

}
