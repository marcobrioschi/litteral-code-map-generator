package biz.brioschi.lcmgenerator.diagram.builders;

import biz.brioschi.lcmgenerator.diagram.BoxConnection;
import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;

public interface DiagramBuilder {

    void startDocument(String title, String description);

    void addLiterateCodeMapBox(LiterateCodeMapBox.BoxType boxType, String boxName);

    void addLiterateCodeMapConnection(String sourceBox, String destinationBox, BoxConnection.ConnectionType connectionType);

    void endDocument();

    String getDiagramDescription();

}
