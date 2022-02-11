package biz.brioschi.lcmgenerator.builders;

import biz.brioschi.lcmgenerator.literatemap.BoxConnection.ConnectionType;
import biz.brioschi.lcmgenerator.literatemap.Box;

public interface LiterateCodeBuilder {

    void startDocument(String title, String description);

    void addLiterateCodeMapBox(Box.BoxType boxType, String boxName);

    void addLiterateCodeMapConnection(String sourceBox, String destinationBox, ConnectionType connectionType, Integer progressiveNumber, String description);

    void endDocument();

    String getLiterateCodeMaoDescription();         // TODO use a more generic outputStream?

}
