package biz.brioschi.lcmgenerator.literatemap;

import lombok.Value;

import java.util.List;

import static biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox.*;

@Value
public class BoxDeclarationScope {
    String typeName;
    BoxType boxType;
    List<BoxConnection> connections;
}
