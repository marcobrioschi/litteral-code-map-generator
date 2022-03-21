package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser;
import org.antlr.v4.runtime.Token;

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
                String currentBlockName = methodReturnType + " " + methodName + "(" + parameters + ")";
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
        // TODO Remove the StringBuffer and use vars
        StringBuffer parametersDescription = new StringBuffer();
        if (ctx.formalParameters().receiverParameter() != null) {
            parametersDescription.append(getReceiverParameter(ctx.formalParameters().receiverParameter()));
        }
        if (ctx.formalParameters().formalParameterList() != null) {
            parametersDescription.append(getFormalParameterList(ctx.formalParameters().formalParameterList()));
        };
        return parametersDescription.toString();
    }

    private String getReceiverParameter(ReceiverParameterContext receiverParameter) { // TODO test this method/usecase
        return decodeType(receiverParameter.typeType()) + " THIS";
    }

    private String decodeType(TypeTypeContext type) {
        if (type.primitiveType() != null) {
            return type.primitiveType().getText();
        }
        if (type.classOrInterfaceType() != null) {
            return type.classOrInterfaceType().getText();
        }
        return null;    // TODO exception
    }

    private String getFormalParameterList(FormalParameterListContext formalParameterList) { // TODO test a list of params
        StringBuffer stringBuffer = new StringBuffer();
        List<FormalParameterContext> parameters = formalParameterList.formalParameter();
        for (FormalParameterContext parameter : parameters) {
            stringBuffer.append(decodeType(parameter.typeType()));
            stringBuffer.append(" ");
            stringBuffer.append(parameter.variableDeclaratorId().getText());
        }
        if (formalParameterList.lastFormalParameter() != null) {
            stringBuffer.append(decodeType(formalParameterList.lastFormalParameter().typeType()));
            stringBuffer.append(" ... ");
            stringBuffer.append(formalParameterList.lastFormalParameter().variableDeclaratorId().getText());
        }
        return stringBuffer.toString();
    }

    private String getBlockContentText(BlockContext ctx) {
        Token startJustAfterParenthesis = currentStatus.bufferedTokenStream.get(ctx.start.getTokenIndex() + 1);
        Token endJustBeforeParenthesis = currentStatus.bufferedTokenStream.get(ctx.stop.getTokenIndex() - 1);
        String rawText = currentStatus.bufferedTokenStream.getText(startJustAfterParenthesis, endJustBeforeParenthesis);
        String cleanedText = fixIndentation(rawText);
        return cleanedText;
    }

    private String fixIndentation(String source) {
        String[] lines = source.split("\n");
        int firstLine = findFirstLine(lines);
        int lastLine = findLastLine(lines);
        int maxIndentation = findMaxIndentationSpaces(lines, firstLine, lastLine);
        StringBuffer result = new StringBuffer();
        for (int i = firstLine; i <= lastLine; ++i) {
            result.append(lines[i].substring(maxIndentation));
            if (i < lastLine) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    private int findFirstLine(String[] lines) {
        int firstLine = 0;
        while (isAnEmptyLine(lines[firstLine]))
            firstLine++;
        return firstLine;
    }

    private int findLastLine(String[] lines) {
        int lastLine = lines.length - 1;
        while (isAnEmptyLine(lines[lastLine]))
            lastLine--;
        return lastLine;
    }

    private int findMaxIndentationSpaces(String[] lines, int firstLine, int lastLine) {
        int maxIndentation = Integer.MAX_VALUE;
        for (int i = firstLine; i <= lastLine; ++i) {
            String currentLine = lines[i];
            maxIndentation = Math.min(
                    maxIndentation,
                    currentLine.indexOf(currentLine.trim())
            );
        }
        return maxIndentation;
    }

    private boolean isAnEmptyLine(String line) {
        return line.matches("\\s*");
    }

}
