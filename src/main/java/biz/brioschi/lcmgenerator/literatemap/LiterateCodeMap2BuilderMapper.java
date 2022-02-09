package biz.brioschi.lcmgenerator.literatemap;

import biz.brioschi.lcmgenerator.literatemap.builders.LiterateCodeBuilder;

import java.util.List;

public class LiterateCodeMap2BuilderMapper {

    private final LiterateCodeBuilder literateCodeBuilder;

    public LiterateCodeMap2BuilderMapper(LiterateCodeBuilder literateCodeBuilder) {
        this.literateCodeBuilder = literateCodeBuilder;
    }

    public void mapBoxes(String title, String description, List<LiterateCodeMapBox> boxes) {
        this.literateCodeBuilder.startDocument(title, description);
        for (LiterateCodeMapBox box : boxes) {
            this.literateCodeBuilder.addLiterateCodeMapBox(box.getType(), box.getName());
            for (BoxConnection connection : box.getConnections()) {
                this.literateCodeBuilder.addLiterateCodeMapConnection(
                        box.getName(),
                        connection.getTargetBoxName(),
                        connection.getType(),
                        connection.getDescription());
            }
        }
        this.literateCodeBuilder.endDocument();
    }

}
