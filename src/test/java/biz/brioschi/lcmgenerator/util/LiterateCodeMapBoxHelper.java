package biz.brioschi.lcmgenerator.util;

import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox;
import biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox.BoxType;

import java.util.ArrayList;
import java.util.List;

import static biz.brioschi.lcmgenerator.literatemap.BoxConnection.ConnectionType.EXTENDS;
import static biz.brioschi.lcmgenerator.literatemap.BoxConnection.ConnectionType.INVOKE;

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
            String... extraInfo
    ) {
        List<BoxConnection> expectedConnections = new ArrayList<>();
        for (String singleExtraInfo : extraInfo) {
            if (singleExtraInfo.contains(":")) {
                String[] connectionComponents = singleExtraInfo.split(":");
                expectedConnections.add(new BoxConnection(INVOKE, connectionComponents[0], connectionComponents[1]));
            } else {
                expectedConnections.add(new BoxConnection(EXTENDS, singleExtraInfo));
            }
        }
        return LiterateCodeMapBox.builder()
                .type(expectedType)
                .name(expectedName)
                .connections(expectedConnections)
                .build();
    }

}
