package biz.brioschi.lcmgenerator.diagram;

import lombok.Value;

import java.util.List;

@Value
public class TypeDeclarationScope {
    String name;
    List<BoxConnection> connectionList;
}
