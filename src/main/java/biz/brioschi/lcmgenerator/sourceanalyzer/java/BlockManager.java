package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;

import java.util.List;

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

    // TODO generic method, enumBody, interfaceBody

    public void enterClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
        if (ctx.memberDeclaration() != null) {
            if (ctx.memberDeclaration().methodDeclaration() != null) {
                MethodDeclarationContext methodDeclaration = ctx.memberDeclaration().methodDeclaration();
                String methodReturnType = methodDeclaration.typeTypeOrVoid().getText();
                String methodName = methodDeclaration.identifier().getText();
                String parameters = getParametersText(methodDeclaration);
                String currentBlockName = methodReturnType + " " + methodName + parameters;
                currentStatus.currentBlockName.push(currentBlockName);
                String blockContent = getBlockContentText(methodDeclaration.methodBody().block());
                currentStatus.currentBlockContent.push(blockContent);
            }
        }
    }

    public void exitClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
        if (ctx.memberDeclaration() != null) {
            if (ctx.memberDeclaration().methodDeclaration() != null) {
                currentStatus.currentBlockName.pop();
                currentStatus.currentBlockContent.pop();
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Utils methods

    private String getParametersText(MethodDeclarationContext ctx) {
        String result = ctx.formalParameters().getText();
        return result;
    }

    private String getBlockContentText(BlockContext ctx) {
        Token startJustAfterParenthesis = currentStatus.bufferedTokenStream.get(ctx.start.getTokenIndex() + 1);
        Token endJustBeforeParenthesis = currentStatus.bufferedTokenStream.get(ctx.stop.getTokenIndex() - 1);
        String rawText = currentStatus.bufferedTokenStream.getText(startJustAfterParenthesis, endJustBeforeParenthesis);
        String cleanedText = fixIndentation(rawText);
        System.out.println(cleanedText);
        return cleanedText;
    }

    private String fixIndentation(String source) {
        // TODO: remove empty lines at beginning and at the end
        // TODO: reduce the indentation in the same way in all the lines
        String[] strings = source.split("\n");
        return source;
    }

}
