package biz.brioschi.lcmgenerator.model;

import lombok.Value;

@Value
public class LiterateCodeMapBox {

    BoxType type;
    String name;

    public enum BoxType  {
        JAVA_CLASS,
        JAVA_INTERFACE,
        JAVA_ENUM
    }
}
