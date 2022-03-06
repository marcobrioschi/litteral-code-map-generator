package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.parser.JavaLexer;
import biz.brioschi.lcmgenerator.directives.Directive;
import biz.brioschi.lcmgenerator.directives.LiterateMapInvoke;
import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap.DirectivesRecognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DirectiveManager { // TODO interface segregation

    ///////////////////////////////////////////////////////////////////////////
    // Init

    private final ListenerStatus currentStatus;
    private int lastReadTokenIndex;

    public DirectiveManager(ListenerStatus currentStatus) {
        this.currentStatus = currentStatus;
        this.lastReadTokenIndex = -1;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Listener hooks

    public void visitTerminal(TerminalNode terminalNode) {
        manageDirectivesOnCurrentNode(terminalNode.getSymbol());
    }

    ///////////////////////////////////////////////////////////////////////////
    // Utils methods

    private void manageDirectivesOnCurrentNode(Token currentToken) {
        List<Token> comments = new ArrayList<>();
        comments.addAll(readLeftComments(currentToken));
        comments.addAll(readRightComments(currentToken));
        comments.stream()
                .map(Token::getText)
                .map(DirectivesRecognizer::extractDirectives)
                .flatMap(Collection::stream)
                .forEach(this::applyDirective);
    }

    private List<Token> readLeftComments(Token currentToken) {
        List<Token> leftResult = this.currentStatus.bufferedTokenStream.getHiddenTokensToLeft(currentToken.getTokenIndex());
        List<Token> result = new ArrayList<>();
        if (leftResult != null) {
            for (Token token : leftResult) {
                if (isAComment(token) && isNotAlreadyRead(token)) {
                    result.add(token);
                    lastReadTokenIndex = token.getTokenIndex();
                }
            }
        }
        return result;
    }

    private boolean isNotAlreadyRead(Token token) {
        return token.getTokenIndex() > lastReadTokenIndex;
    }

    private List<Token> readRightComments(Token currentToken) {
        List<Token> rightResult = this.currentStatus.bufferedTokenStream.getHiddenTokensToRight(currentToken.getTokenIndex());
        List<Token> result = new ArrayList<>();
        if (rightResult != null) {
            for (Token token : rightResult) {
                if (isAComment(token) && tokensAreOnTheSameLine(currentToken, token)) {
                    result.add(token);
                    lastReadTokenIndex = token.getTokenIndex();
                }
            }
        }
        return result;
    }

    private boolean tokensAreOnTheSameLine(Token firstToken, Token secondToken) {
        return secondToken.getLine() == firstToken.getLine();
    }

    private boolean isAComment(Token token) {
        return (token.getType() == JavaLexer.LINE_COMMENT) || (token.getType() == JavaLexer.COMMENT);
    }

    private void applyDirective(Directive baseDirective) {
        // TODO refactoring and enrich logic
        if (baseDirective instanceof LiterateMapInvoke) {
            if (!this.currentStatus.typeScopeStack.empty()) {  // TODO emit warning!!!!
                LiterateMapInvoke directive = (LiterateMapInvoke) baseDirective;
                this.currentStatus.typeScopeStack.peek().getConnections().add(
                        BoxConnection.generateInvoke(
                                directive.getTargetBox(),
                                directive.getProgressiveNumber(),
                                directive.getDescription()
                        )
                );
            }
        }
    }

}
