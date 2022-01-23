package biz.brioschi.lcmgenerator.diagram;

import lombok.Value;

@Value
public class BoxConnection {

    ConnectionType type;
    String targetBoxName;

    public enum ConnectionType {
        EXTENDS,
        // TODO add INVOKE
    }

}
