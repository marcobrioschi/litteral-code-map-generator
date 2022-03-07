package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.antlr.java.parser.JavaParserBaseListener;
import biz.brioschi.lcmgenerator.literatemap.Box;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

import static biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser.*;

public class JavaLiterateCodeMapListener extends JavaParserBaseListener {

    private ListenerStatus currentStatus;
    private ScopeManager scopeManager;
    private ExtensionManager extensionManager;
    private DirectiveManager directiveManager;

    public JavaLiterateCodeMapListener(BufferedTokenStream bufferedTokenStream) {
        this.currentStatus = new ListenerStatus(bufferedTokenStream);
        this.scopeManager = new ScopeManager(currentStatus);
        this.extensionManager = new ExtensionManager(currentStatus);
        this.directiveManager = new DirectiveManager(currentStatus);
    }

    public List<Box> getLiterateCodeMapBoxes() {
        return this.currentStatus.boxes;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Scope management

    @Override
    public void enterTypeDeclaration(TypeDeclarationContext ctx) {
        scopeManager.enterTypeDeclaration(ctx);
    }

    @Override
    public void exitTypeDeclaration(TypeDeclarationContext ctx) {
        scopeManager.exitTypeDeclaration(ctx);
    }

    @Override
    public void enterClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
        scopeManager.enterClassBodyDeclaration(ctx);
    }

    @Override
    public void exitClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
        scopeManager.exitClassBodyDeclaration(ctx);
    }

    @Override
    public void enterInterfaceBodyDeclaration(InterfaceBodyDeclarationContext ctx) {
        scopeManager.enterInterfaceBodyDeclaration(ctx);
    }

    @Override
    public void exitInterfaceBodyDeclaration(InterfaceBodyDeclarationContext ctx) {
        scopeManager.exitInterfaceBodyDeclaration(ctx);
    }

    @Override
    public void enterLocalTypeDeclaration(LocalTypeDeclarationContext ctx) {
        scopeManager.enterLocalTypeDeclaration(ctx);
    }

    @Override
    public void exitLocalTypeDeclaration(LocalTypeDeclarationContext ctx) {
        scopeManager.exitLocalTypeDeclaration(ctx);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Extensions

    @Override
    public void enterClassDeclaration(ClassDeclarationContext ctx) {
        extensionManager.enterClassDeclaration(ctx);
    }

    @Override
    public void enterInterfaceDeclaration(InterfaceDeclarationContext ctx) {
        extensionManager.enterInterfaceDeclaration(ctx);
    }

    @Override
    public void enterEnumDeclaration(EnumDeclarationContext ctx) {
        extensionManager.enterEnumDeclaration(ctx);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Directives

    @Override
    public void visitTerminal(TerminalNode terminalNode) {
        directiveManager.visitTerminal(terminalNode);
    }

}
