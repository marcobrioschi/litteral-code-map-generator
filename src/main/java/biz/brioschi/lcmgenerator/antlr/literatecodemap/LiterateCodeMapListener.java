// Generated from /Users/m40030213/MyData/_workingdir/repositories/git/litteral-code-map-generator/antlr/literatecodemapper/LiterateCodeMap.g4 by ANTLR 4.9.2
package biz.brioschi.lcmgenerator.antlr.literatecodemap;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LiterateCodeMapParser}.
 */
public interface LiterateCodeMapListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LiterateCodeMapParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterSentence(LiterateCodeMapParser.SentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link LiterateCodeMapParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitSentence(LiterateCodeMapParser.SentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link LiterateCodeMapParser#directiveDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterDirectiveDeclaration(LiterateCodeMapParser.DirectiveDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LiterateCodeMapParser#directiveDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitDirectiveDeclaration(LiterateCodeMapParser.DirectiveDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LiterateCodeMapParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(LiterateCodeMapParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link LiterateCodeMapParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(LiterateCodeMapParser.ParamContext ctx);
}