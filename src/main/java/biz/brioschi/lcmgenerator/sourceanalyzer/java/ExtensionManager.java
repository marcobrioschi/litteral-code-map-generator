package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser;
import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

import static biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser.IMPLEMENTS;

public class ExtensionManager {

    ///////////////////////////////////////////////////////////////////////////
    // Init

    private final ListenerStatus currentStatus;

    public ExtensionManager(ListenerStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Listener hooks

    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        addCurrentTypeExtensions(ctx);
    }

    public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        addCurrentTypeExtensions(ctx);
    }

    public void enterEnumDeclaration(JavaParser.EnumDeclarationContext ctx) {
        addCurrentTypeExtensions(ctx);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Utils methods

    private void addCurrentTypeExtensions(ParserRuleContext ctx) {
        List<BoxConnection> connections = getCurrentBoxExtensions(ctx);
        this.currentStatus.typeScopeStack.peek().getConnections().addAll(connections);  // TODO Interface segregation
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
                    BoxConnection.generateExtends(
                            currentExtensionName
                    )
            );
        }
        return connections;
    }

}
