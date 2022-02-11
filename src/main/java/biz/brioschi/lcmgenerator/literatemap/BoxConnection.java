package biz.brioschi.lcmgenerator.literatemap;

import lombok.NonNull;
import lombok.Value;

@Value
public class BoxConnection {

    ConnectionType type;

    @NonNull
    String targetBoxName;

    Integer progressiveNumber;

    String description;

    public static BoxConnection generateExtends(String targetBoxName) {
        return new BoxConnection(ConnectionType.EXTENDS, targetBoxName, null, null);
    }

    public static BoxConnection generateInvoke(String targetBoxName, Integer progressiveNumber, String description) {
        return new BoxConnection(ConnectionType.INVOKE, targetBoxName, progressiveNumber, description);
    }

    private BoxConnection(ConnectionType connectionType, String targetBoxName, Integer progressiveNumber, String description) {
        this.type = connectionType;
        this.targetBoxName = targetBoxName;
        this.progressiveNumber = progressiveNumber;
        this.description = description;
    }

    public enum ConnectionType {
        EXTENDS,
        INVOKE
    }

}
