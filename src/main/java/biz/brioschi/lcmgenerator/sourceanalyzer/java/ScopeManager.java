package biz.brioschi.lcmgenerator.sourceanalyzer.java;

import biz.brioschi.lcmgenerator.literatemap.Box;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.Optional;

import static biz.brioschi.lcmgenerator.antlr.java.parser.JavaParser.*;
import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType;
import static biz.brioschi.lcmgenerator.literatemap.Box.BoxType.*;

class ScopeManager {

    ///////////////////////////////////////////////////////////////////////////
    // Init

    private final ListenerStatus currentStatus;

    public ScopeManager(ListenerStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Listener hooks

    public void enterTypeDeclaration(TypeDeclarationContext ctx) {
        pushRightTypeContext(ctx);
    }

    public void exitTypeDeclaration(TypeDeclarationContext ctx) {
        popContextOfCurrentTypeAndStoreTheBox();
    }

    public void enterClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
        if (ctx.memberDeclaration() != null) {
            pushRightTypeContext(ctx.getRuleContext(MemberDeclarationContext.class, 0));
        }
    }

    public void exitClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
        if (ctx.memberDeclaration() != null) {
            if (findValidTypeReference(ctx.memberDeclaration()).isPresent()) {
                popContextOfCurrentTypeAndStoreTheBox();
            }
        }
    }

    public void enterInterfaceBodyDeclaration(InterfaceBodyDeclarationContext ctx) {
        if (ctx.interfaceMemberDeclaration() != null) {
            pushRightTypeContext(ctx.getRuleContext(InterfaceMemberDeclarationContext.class, 0));
        }
    }

    public void exitInterfaceBodyDeclaration(InterfaceBodyDeclarationContext ctx) {
        if (ctx.interfaceMemberDeclaration() != null) {
            if (findValidTypeReference(ctx.interfaceMemberDeclaration()).isPresent()) {
                popContextOfCurrentTypeAndStoreTheBox();
            }
        }
    }

    public void enterLocalTypeDeclaration(LocalTypeDeclarationContext ctx) {
        pushRightTypeContext(ctx);
    }

    public void exitLocalTypeDeclaration(LocalTypeDeclarationContext ctx) {
        popContextOfCurrentTypeAndStoreTheBox();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Utils methods

    private Optional<ValidType> findValidTypeReference(ParserRuleContext ctx) {
        for (ValidType validType : ValidType.values()) {
            if (ctx.getRuleContext(validType.getClazz(), 0) != null) {
                return Optional.of(validType);
            }
        }
        return Optional.empty();
    }

    private void pushRightTypeContext(ParserRuleContext ctx) {
        Optional<ValidType> type = findValidTypeReference(ctx);
        if (type.isPresent()) {
            ParserRuleContext currentCheckedClassContext = ctx.getRuleContext(type.get().getClazz(), 0);
            IdentifierContext identifier = currentCheckedClassContext.getRuleContext(IdentifierContext.class, 0);
            String typeName = identifier.getText();
            buildContextAndPushIt(typeName, type.get().getBoxType());
        }
    }

    private void buildContextAndPushIt(String typeName, BoxType boxType) {
        BoxDeclarationScope scope = new BoxDeclarationScope(typeName, boxType, new ArrayList<>(), new ArrayList<>());
        this.currentStatus.typeScopeStack.push(scope);  // TODO interface segregation
    }

    private void popContextOfCurrentTypeAndStoreTheBox() {
        BoxDeclarationScope currentScope = this.currentStatus.typeScopeStack.pop(); // TODO interface segregation
        this.currentStatus.boxes.add(
                Box.builder()
                        .type(currentScope.getBoxType())
                        .name(currentScope.getTypeName())
                        .connections(currentScope.getConnections())
                        .blocks(currentScope.getBlocks())
                        .build()
        );
    }

    private enum ValidType {

        Class(ClassDeclarationContext.class, JAVA_CLASS),
        Interface(InterfaceDeclarationContext.class, JAVA_INTERFACE),
        Enum(EnumDeclarationContext.class, JAVA_ENUM);

        private Class<? extends ParserRuleContext> clazz;
        private BoxType boxType;

        ValidType(java.lang.Class<? extends ParserRuleContext> clazz, BoxType boxType) {
            this.clazz = clazz;
            this.boxType = boxType;
        }

        public java.lang.Class<? extends ParserRuleContext> getClazz() {
            return clazz;
        }

        public BoxType getBoxType() {
            return boxType;
        }

    }

}
