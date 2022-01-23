package biz.brioschi.lcmgenerator.literatemap;

import lombok.NonNull;
import lombok.Value;

@Value
public class BoxConnection {

    @NonNull
    ConnectionType type;

    @NonNull
    String targetBoxName;

    String description;

    public BoxConnection(ConnectionType type, String targetBoxName) {
        this.type = type;
        this.targetBoxName = targetBoxName;
        this.description = "";
    }

    public BoxConnection(ConnectionType type, String targetBoxName, String description) {
        this.type = type;
        this.targetBoxName = targetBoxName;
        this.description = description;
    }

    public enum ConnectionType {
        EXTENDS,
        INVOKE
    }

}
