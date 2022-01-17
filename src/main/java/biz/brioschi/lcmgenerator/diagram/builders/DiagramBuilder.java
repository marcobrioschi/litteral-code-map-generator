package biz.brioschi.lcmgenerator.diagram.builders;

import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;

public interface DiagramBuilder {

    void startDocument(String title, String description);

    void addLiterateCodeMapBox(LiterateCodeMapBox.BoxType boxType, String boxName);

    void endDocument();

    String getDiagramDescription();

}
