package biz.brioschi.lcmgenerator.diagram;

import lombok.Value;

@Value
public class BoxConnection {

    ConnectionType type;
    String targetBoxName;
    String description = "";    // TODO: fix the management

    public enum ConnectionType {
        EXTENDS,
        INVOKE
    }

}
