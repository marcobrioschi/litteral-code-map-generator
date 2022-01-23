package biz.brioschi.lcmgenerator.util;

import biz.brioschi.lcmgenerator.diagram.BoxConnection;
import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;
import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox.BoxType;

import java.util.ArrayList;
import java.util.List;

import static biz.brioschi.lcmgenerator.diagram.BoxConnection.ConnectionType.EXTENDS;

public class LiterateCodeMapBoxHelper {

    public static LiterateCodeMapBox generateLiterateCodeMapBox(
            BoxType expectedType,
            String expectedName
    ) {
        return LiterateCodeMapBox.builder()
                .type(expectedType)
                .name(expectedName)
                .connections(new ArrayList<>())
                .build();
    }

    public static LiterateCodeMapBox generateLiterateCodeMapBox(
            BoxType expectedType,
            String expectedName,
            String... expectedExtends
    ) {
        List<BoxConnection> expectedConnections = new ArrayList<>();
        for (String expectedConnectionName : expectedExtends) {
            expectedConnections.add(new BoxConnection(EXTENDS, expectedConnectionName));
        }
        return LiterateCodeMapBox.builder()
                .type(expectedType)
                .name(expectedName)
                .connections(expectedConnections)
                .build();
    }

}
