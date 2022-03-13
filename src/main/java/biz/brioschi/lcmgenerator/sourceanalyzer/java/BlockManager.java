package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import static biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser.*;

public class BlockManager {

    ///////////////////////////////////////////////////////////////////////////
    // Init

    private final ListenerStatus currentStatus;

    public BlockManager(ListenerStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Listener hooks

    public void enterMethodDeclaration(MethodDeclarationContext ctx) {
        String methodReturnType = ctx.typeTypeOrVoid().getText();
        String methodName = ctx.identifier().getText();
        String parameters = getParametersText(ctx);
        String currentBlockName = methodReturnType + " " + methodName + parameters;
        currentStatus.currentBlockName.push(currentBlockName);
    }

    public void exitMethodDeclaration(MethodDeclarationContext ctx) {
        currentStatus.currentBlockName.pop();
    }

    public void enterBlock(BlockContext ctx) {
        String blockContent = getBlockContentText(ctx);
        currentStatus.currentBlockContent.push(blockContent);
    }

    public void exitBlock(BlockContext ctx) {
        currentStatus.currentBlockContent.pop();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Utils methods

    private String getParametersText(MethodDeclarationContext ctx) {
        String result = ctx.formalParameters().getText();
        return result;
    }

    private String getBlockContentText(BlockContext ctx) {
        String result = currentStatus.bufferedTokenStream.getText(ctx.start, ctx.stop);
        return result;
    }

}
