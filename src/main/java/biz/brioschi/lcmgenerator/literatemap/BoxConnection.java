package biz.brioschi.lcmgenerator.literatemap;

import lombok.NonNull;
import lombok.Value;

@Value
public class BoxConnection {

    @NonNull
    ConnectionType type;

    @NonNull
    String targetBoxName;

    Integer progressiveNumber;

    String description;

    // TODO private constructor and add factory methods
    public BoxConnection(ConnectionType type, String targetBoxName) {
        this.type = type;
        this.targetBoxName = targetBoxName;
        this.progressiveNumber = null;
        this.description = "";
    }

    public BoxConnection(ConnectionType type, String targetBoxName, String description) {   // TODO remove
        this.type = type;
        this.targetBoxName = targetBoxName;
        this.progressiveNumber = null;
        this.description = description;
    }

    public BoxConnection(ConnectionType type, String targetBoxName, Integer progressiveNumber, String description) {
        this.type = type;
        this.targetBoxName = targetBoxName;
        this.progressiveNumber = progressiveNumber;
        this.description = description;
    }

    public enum ConnectionType {
        EXTENDS,
        INVOKE
    }

}
