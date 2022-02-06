package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.parser.JavaLexer;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser;
import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParserBaseListener;
import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import biz.brioschi.lcmgenerator.literatemap.BoxDeclarationScope;
import biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox;
import biz.brioschi.lcmgenerator.literatemap.directives.Directive;
import biz.brioschi.lcmgenerator.literatemap.directives.LiterateMapConnection;
import biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap.DirectivesRecognizer;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser.*;
import static biz.brioschi.lcmgenerator.literatemap.BoxConnection.ConnectionType.EXTENDS;
import static biz.brioschi.lcmgenerator.literatemap.BoxConnection.ConnectionType.INVOKE;
import static biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox.BoxType;
import static biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox.BoxType.*;

public class JavaLiterateCodeMapListener extends JavaParserBaseListener {
    private BufferedTokenStream bufferedTokenStream;
    private List<LiterateCodeMapBox> literateCodeMapBoxes;
    private Stack<BoxDeclarationScope> typeScopeStack;

    public JavaLiterateCodeMapListener(BufferedTokenStream bufferedTokenStream) {
        this.bufferedTokenStream = bufferedTokenStream;
        this.literateCodeMapBoxes = new ArrayList<>();
        this.typeScopeStack = new Stack<>();
    }

    public List<LiterateCodeMapBox> getLiterateCodeMapBoxes() {
        return literateCodeMapBoxes;
    }

    @Override
    public void enterTypeDeclaration(TypeDeclarationContext ctx) {
        pushRightTypeContext(ctx);
    }

    @Override
    public void exitTypeDeclaration(TypeDeclarationContext ctx) {
        popContextOfCurrentTypeAndStoreTheBox();
    }


    // TODO: switch to classBodyDeclaration
//    @Override
//    public void enterMemberDeclaration(MemberDeclarationContext ctx) {
//        pushRightTypeContext(ctx);
//    }
//
//    @Override
//    public void exitMemberDeclaration(MemberDeclarationContext ctx) {
//        popContextOfCurrentTypeAndStoreTheBox();
//    }

    @Override
    public void enterClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
        if (ctx.memberDeclaration() != null) {
            if (ctx.memberDeclaration().classDeclaration() != null) {
                buildContextAndPushIt(ctx.memberDeclaration().classDeclaration().identifier().getText(), JAVA_CLASS);
            }
        }
        if (ctx.memberDeclaration() != null) {
            if (ctx.memberDeclaration().interfaceDeclaration() != null) {
                buildContextAndPushIt(ctx.memberDeclaration().interfaceDeclaration().identifier().getText(), JAVA_INTERFACE);
            }
        }
        if (ctx.memberDeclaration() != null) {
            if (ctx.memberDeclaration().enumDeclaration() != null) {
                buildContextAndPushIt(ctx.memberDeclaration().enumDeclaration().identifier().getText(), JAVA_ENUM);
            }
        }
    }

    @Override
    public void exitClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
        if (ctx.memberDeclaration() != null) {
            if (ctx.memberDeclaration().classDeclaration() != null) {
                popContextOfCurrentTypeAndStoreTheBox();
            }
        }
        if (ctx.memberDeclaration() != null) {
            if (ctx.memberDeclaration().interfaceDeclaration() != null) {
                popContextOfCurrentTypeAndStoreTheBox();
            }
        }
        if (ctx.memberDeclaration() != null) {
            if (ctx.memberDeclaration().enumDeclaration() != null) {
                popContextOfCurrentTypeAndStoreTheBox();
            }
        }
    }

    // TODO switch to interfaceBodyDeclaration
//    @Override
//    public void enterInterfaceMemberDeclaration(InterfaceMemberDeclarationContext ctx) {
//        pushRightTypeContext(ctx);
//    }
//
//    @Override
//    public void exitInterfaceMemberDeclaration(InterfaceMemberDeclarationContext ctx) {
//        popContextOfCurrentTypeAndStoreTheBox();
//    }

    @Override
    public void enterInterfaceBodyDeclaration(InterfaceBodyDeclarationContext ctx) {
        if (ctx.interfaceMemberDeclaration() != null) {
            if (ctx.interfaceMemberDeclaration().classDeclaration() != null) {
                buildContextAndPushIt(ctx.interfaceMemberDeclaration().classDeclaration().identifier().getText(), JAVA_CLASS);
            }
        }
        if (ctx.interfaceMemberDeclaration() != null) {
            if (ctx.interfaceMemberDeclaration().interfaceDeclaration() != null) {
                buildContextAndPushIt(ctx.interfaceMemberDeclaration().interfaceDeclaration().identifier().getText(), JAVA_INTERFACE);
            }
        }
        if (ctx.interfaceMemberDeclaration() != null) {
            if (ctx.interfaceMemberDeclaration().enumDeclaration() != null) {
                buildContextAndPushIt(ctx.interfaceMemberDeclaration().enumDeclaration().identifier().getText(), JAVA_ENUM);
            }
        }
    }

    @Override
    public void exitInterfaceBodyDeclaration(InterfaceBodyDeclarationContext ctx) {
        if (ctx.interfaceMemberDeclaration() != null) {
            if (ctx.interfaceMemberDeclaration().classDeclaration() != null) {
                popContextOfCurrentTypeAndStoreTheBox();
            }
        }
        if (ctx.interfaceMemberDeclaration() != null) {
            if (ctx.interfaceMemberDeclaration().interfaceDeclaration() != null) {
                popContextOfCurrentTypeAndStoreTheBox();
            }
        }
        if (ctx.interfaceMemberDeclaration() != null) {
            if (ctx.interfaceMemberDeclaration().enumDeclaration() != null) {
                popContextOfCurrentTypeAndStoreTheBox();
            }
        }
    }

    // TODO switch to annotationTypeElementDeclaration
    @Override
    public void enterAnnotationTypeElementRest(AnnotationTypeElementRestContext ctx) {
        pushRightTypeContext(ctx);
    }

    @Override
    public void exitAnnotationTypeElementRest(AnnotationTypeElementRestContext ctx) {
        popContextOfCurrentTypeAndStoreTheBox();
    }

    @Override
    public void enterLocalTypeDeclaration(LocalTypeDeclarationContext ctx) {
        pushRightTypeContext(ctx);
    }

    @Override
    public void exitLocalTypeDeclaration(LocalTypeDeclarationContext ctx) {
        popContextOfCurrentTypeAndStoreTheBox();
    }

    @Override
    public void enterClassDeclaration(ClassDeclarationContext ctx) {
        addCurrentTypeExtensions(ctx);
    }

    @Override
    public void enterInterfaceDeclaration(InterfaceDeclarationContext ctx) {
        addCurrentTypeExtensions(ctx);
    }

    @Override
    public void enterEnumDeclaration(EnumDeclarationContext ctx) {
        addCurrentTypeExtensions(ctx);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Extensions

    private void pushRightTypeContext(ParserRuleContext ctx) {
        if (ctx.getRuleContext((Class<? extends ParserRuleContext>) ClassDeclarationContext.class, 0) != null) {
            String typeName2 = ctx.getRuleContext((Class<? extends ParserRuleContext>) ClassDeclarationContext.class, 0).getRuleContext(IdentifierContext.class, 0).getText();
            buildContextAndPushIt(typeName2, JAVA_CLASS);
        }
        if (ctx.getRuleContext((Class<? extends ParserRuleContext>) InterfaceDeclarationContext.class, 0) != null) {
            String typeName1 = ctx.getRuleContext((Class<? extends ParserRuleContext>) InterfaceDeclarationContext.class, 0).getRuleContext(IdentifierContext.class, 0).getText();
            buildContextAndPushIt(typeName1, JAVA_INTERFACE);
        }
        if (ctx.getRuleContext((Class<? extends ParserRuleContext>) EnumDeclarationContext.class, 0) != null) {
            String typeName = ctx.getRuleContext((Class<? extends ParserRuleContext>) EnumDeclarationContext.class, 0).getRuleContext(IdentifierContext.class, 0).getText();
            buildContextAndPushIt(typeName, JAVA_ENUM);
        }
    }

    private void buildContextAndPushIt(String typeName, BoxType boxType) {
        BoxDeclarationScope scope = new BoxDeclarationScope(typeName, boxType, new ArrayList<>());
        typeScopeStack.push(scope);
    }

    private void popContextOfCurrentTypeAndStoreTheBox() {
        BoxDeclarationScope currentScope = typeScopeStack.pop();
        generateANewBoxElement(currentScope.getBoxType(), currentScope.getTypeName(), currentScope.getConnections());
    }

    private void generateANewBoxElement(BoxType boxType, String boxName, List<BoxConnection> connections) {
        literateCodeMapBoxes.add(
                LiterateCodeMapBox.builder()
                        .type(boxType)
                        .name(boxName)
                        .connections(connections)
                        .build()
        );
    }

    private void addCurrentTypeExtensions(ParserRuleContext ctx) {
        List<BoxConnection> connections = getCurrentBoxExtensions(ctx);
        typeScopeStack.peek().getConnections().addAll(connections);
    }

    private List<BoxConnection> getCurrentBoxExtensions(ParserRuleContext ctx) {
        List<BoxConnection> connections = new ArrayList<>();
        TerminalNode extendsToken = ctx.getToken(JavaParser.EXTENDS, 0);
        TerminalNode implementsToken = ctx.getToken(IMPLEMENTS, 0);
        int lastReadedChild = 2;
        while ((ctx.getChild(lastReadedChild) == extendsToken) || (ctx.getChild(lastReadedChild) == implementsToken)) {
            ParseTree extendsChild = ctx.getChild(lastReadedChild + 1);
            connections.addAll(parseListOfCurrentBoxExtensions(extendsChild));
            lastReadedChild += 2;
        };
        return connections;
    }

    private List<BoxConnection> parseListOfCurrentBoxExtensions(ParseTree interfaceList) {
        List<BoxConnection> connections = new ArrayList<>();
        for (int i = 0; i < interfaceList.getChildCount(); i += 2) {
            String currentExtensionName = interfaceList.getChild(i).getText();
            connections.add(
                    new BoxConnection(
                            EXTENDS,
                            currentExtensionName
                    )
            );
        }
        return connections;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Directives

    /*
    typeDeclaration
    : classOrInterfaceModifier*
      (classDeclaration | enumDeclaration | interfaceDeclaration )
    | ';'
    ;
     */

    private int lastReadTokenIndex = -1;

    @Override
    public void visitTerminal(TerminalNode terminalNode) {
        manageDirectivesOnCurrentNode(terminalNode.getSymbol(), terminalNode.getSymbol());
    }

    // TODO search for other entry points following rules
    private void manageDirectivesOnCurrentNode(Token startToken, Token stopToken) {
        if (typeScopeStack.empty())
            return;
        // TODO refactoring and complete with righ comments e filter on already readed comments
        List<Token> result = new ArrayList<>();
        List<Token> leftResult = bufferedTokenStream.getHiddenTokensToLeft(startToken.getTokenIndex());
        if (leftResult != null) {
            for (Token token : leftResult) {
                if (token.getTokenIndex() > lastReadTokenIndex) {
                    result.add(token);
                    lastReadTokenIndex = token.getTokenIndex();
                }
            }
        }
        List<Token> rightResult = bufferedTokenStream.getHiddenTokensToRight(stopToken.getTokenIndex());
        if (rightResult != null) {
            for (Token token : rightResult) {
                if (token.getLine() == stopToken.getLine()) {
                    result.add(token);
                    lastReadTokenIndex = token.getTokenIndex();
                }
            }
        }
        if ((result != null) && result.size() > 0) {
            String leftComments = result.stream()
                    .filter(token -> (token.getType() == JavaLexer.LINE_COMMENT) || (token.getType() == JavaLexer.COMMENT))
                    .map(token -> token.getText())
                    .collect(Collectors.joining(" "));
            List<Directive> directives = DirectivesRecognizer.extractDirectives(leftComments);
            if (directives != null) {
                for (Directive baseDirective: directives) {
                    if (baseDirective instanceof LiterateMapConnection) {
                        LiterateMapConnection directive = (LiterateMapConnection)baseDirective;
                        typeScopeStack.peek().getConnections().add(
                                new BoxConnection(
                                        INVOKE,
                                        directive.getTargetBox(),
                                        directive.getDescription()
                                )
                        );
                    }
                }
            }
        }
    }

}
