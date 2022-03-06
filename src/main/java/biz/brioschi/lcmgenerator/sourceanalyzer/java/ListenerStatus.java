package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.literatemap.Box;
import org.antlr.v4.runtime.BufferedTokenStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class ListenerStatus {

    // TODO make them private and segretate interfaces?
    public BufferedTokenStream bufferedTokenStream;
    public Stack<BoxDeclarationScope> typeScopeStack;
    public List<Box> boxes;

    public ListenerStatus(BufferedTokenStream bufferedTokenStream) {
        this.bufferedTokenStream = bufferedTokenStream;
        this.typeScopeStack = new Stack<>();
        this.boxes = new ArrayList<>();
    }

}
