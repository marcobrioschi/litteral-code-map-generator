package biz.brioschi.lcmgenerator.util;

import biz.brioschi.lcmgenerator.literatemap.Box;
import biz.brioschi.lcmgenerator.literatemap.Box.BoxType;
import biz.brioschi.lcmgenerator.literatemap.BoxBlock;
import biz.brioschi.lcmgenerator.literatemap.BoxConnection;

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
                .blocks(new ArrayList<>())
                .build();
    }

    public static Box generateLiterateCodeMapBox(
            BoxType expectedType,
            String expectedName,
            String... extraInfo
    ) {
        List<BoxConnection> expectedConnections = new ArrayList<>();
        List<BoxBlock> expectedBlocks = new ArrayList<>();
        for (String singleExtraInfo : extraInfo) {
            if (singleExtraInfo.contains("#")) {
                String[] connectionComponents = singleExtraInfo.split("#");
                expectedBlocks.add(
                        new BoxBlock(connectionComponents[0], connectionComponents[1])
                );
            } else if (singleExtraInfo.contains(":")) {
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
                .blocks(expectedBlocks)
                .build();
    }

}
