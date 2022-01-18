package biz.brioschi.lcmgenerator.diagram;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class LiterateCodeMapBox {

    @NonNull
    BoxType type;

    @NonNull
    String name;

    List<BoxConnection> connections;

    public enum BoxType  {
        JAVA_CLASS,
        JAVA_INTERFACE,
        JAVA_ENUM
    }

}
