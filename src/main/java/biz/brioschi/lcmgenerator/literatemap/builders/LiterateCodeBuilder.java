package biz.brioschi.lcmgenerator.literatemap.builders;

import biz.brioschi.lcmgenerator.literatemap.BoxConnection.ConnectionType;
import biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox;

public interface LiterateCodeBuilder {

    void startDocument(String title, String description);

    void addLiterateCodeMapBox(LiterateCodeMapBox.BoxType boxType, String boxName);

    void addLiterateCodeMapConnection(String sourceBox, String destinationBox, ConnectionType connectionType, Integer progressiveNumber, String description);

    void endDocument();

    String getLiterateCodeMaoDescription();

}
