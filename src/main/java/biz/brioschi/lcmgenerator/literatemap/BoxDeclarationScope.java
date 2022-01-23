package biz.brioschi.lcmgenerator.literatemap;

import lombok.Value;

import java.util.List;

@Value
public class BoxDeclarationScope {
    String typeName;
    List<BoxConnection> connections;
}
