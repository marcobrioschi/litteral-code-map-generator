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

    // TODO private constructor and add factory methods
    private BoxConnection(String targetBoxName) {
        this.type = ConnectionType.EXTENDS;
        this.targetBoxName = targetBoxName;
        this.progressiveNumber = null;
        this.description = null;
    }

    private BoxConnection(String targetBoxName, Integer progressiveNumber, String description) {
        this.type = ConnectionType.INVOKE;
        this.targetBoxName = targetBoxName;
        this.progressiveNumber = progressiveNumber;
        this.description = description;
    }

    public enum ConnectionType {
        EXTENDS,
        INVOKE
    }

    public static BoxConnection generateExtends(String targetBoxName) {
        return new BoxConnection(targetBoxName);
    }

    public static BoxConnection generateInvoke(String targetBoxName, Integer progressiveNumber, String description) {
        return new BoxConnection(targetBoxName, progressiveNumber, description);
    }

}
