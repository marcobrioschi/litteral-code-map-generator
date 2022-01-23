package biz.brioschi.lcmgenerator.diagram;

import lombok.Value;

import java.util.List;

@Value
public class TypeDeclarationScope {
    String typeName;
    List<BoxConnection> connections;
}
