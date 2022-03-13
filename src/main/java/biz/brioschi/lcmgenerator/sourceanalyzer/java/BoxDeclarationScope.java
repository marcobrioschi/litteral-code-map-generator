package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.literatemap.BoxBlock;
import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import lombok.Value;

import java.util.List;

import static biz.brioschi.lcmgenerator.literatemap.Box.*;

@Value
class BoxDeclarationScope {
    String typeName;
    BoxType boxType;
    List<BoxConnection> connections;
    List<BoxBlock> blocks;
}
