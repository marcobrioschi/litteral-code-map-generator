package biz.brioschi.lcmgenerator.util;

import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import biz.brioschi.lcmgenerator.literatemap.Box;
import biz.brioschi.lcmgenerator.literatemap.Box.BoxType;

import java.util.ArrayList;
import java.util.List;

public class LiterateCodeMapBoxHelper {

    public static Box generateLiterateCodeMapBox(
            BoxType expectedType,
            String expectedName
    ) {
        return Box.builder()
                .type(expectedType)
                .name(expectedName)
                .connections(new ArrayList<>())
                .build();
    }

    public static Box generateLiterateCodeMapBox(
            BoxType expectedType,
            String expectedName,
            String... extraInfo
    ) {
        List<BoxConnection> expectedConnections = new ArrayList<>();
        for (String singleExtraInfo : extraInfo) {
            if (singleExtraInfo.contains(":")) {
                String[] connectionComponents = singleExtraInfo.split(":");
                expectedConnections.add(BoxConnection.generateInvoke(
                        connectionComponents[1],
                        Integer.parseInt(connectionComponents[0]),
                        connectionComponents[2]
                ));
            } else {
                expectedConnections.add(BoxConnection.generateExtends(singleExtraInfo));
            }
        }
        return Box.builder()
                .type(expectedType)
                .name(expectedName)
                .connections(expectedConnections)
                .build();
    }

}
