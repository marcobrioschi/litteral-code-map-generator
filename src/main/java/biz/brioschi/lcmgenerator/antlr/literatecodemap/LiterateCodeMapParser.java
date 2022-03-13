// Generated from /Users/m40030213/MyData/_workingdir/repositories/git/litteral-code-map-generator/antlr/literatecodemapper/LiterateCodeMap.g4 by ANTLR 4.9.2
package biz.brioschi.lcmgenerator.antlr.literatecodemap;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LiterateCodeMapParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LITERATEMAPINVOKE=1, LITERATEMAPBLOCK=2, NUMBER=3, DQSTRING=4, SQSTRING=5, 
		LPAREN=6, RPAREN=7, COMMA=8, FILLCHARS=9, WS=10;
	public static final int
		RULE_commentsentence = 0, RULE_directiveDeclaration = 1, RULE_literatemapinvoke = 2, 
		RULE_literatemapblock = 3, RULE_params = 4, RULE_param = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"commentsentence", "directiveDeclaration", "literatemapinvoke", "literatemapblock", 
			"params", "param"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'@LiterateMapInvoke'", "'@LiterateMapBlock'", null, null, null, 
			"'('", "')'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LITERATEMAPINVOKE", "LITERATEMAPBLOCK", "NUMBER", "DQSTRING", 
			"SQSTRING", "LPAREN", "RPAREN", "COMMA", "FILLCHARS", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "LiterateCodeMap.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LiterateCodeMapParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class CommentsentenceContext extends ParserRuleContext {
		public List<DirectiveDeclarationContext> directiveDeclaration() {
			return getRuleContexts(DirectiveDeclarationContext.class);
		}
		public DirectiveDeclarationContext directiveDeclaration(int i) {
			return getRuleContext(DirectiveDeclarationContext.class,i);
		}
		public CommentsentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commentsentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiterateCodeMapListener ) ((LiterateCodeMapListener)listener).enterCommentsentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiterateCodeMapListener ) ((LiterateCodeMapListener)listener).exitCommentsentence(this);
		}
	}

	public final CommentsentenceContext commentsentence() throws RecognitionException {
		CommentsentenceContext _localctx = new CommentsentenceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_commentsentence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LITERATEMAPINVOKE || _la==LITERATEMAPBLOCK) {
				{
				{
				setState(12);
				directiveDeclaration();
				}
				}
				setState(17);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DirectiveDeclarationContext extends ParserRuleContext {
		public LiteratemapinvokeContext literatemapinvoke() {
			return getRuleContext(LiteratemapinvokeContext.class,0);
		}
		public LiteratemapblockContext literatemapblock() {
			return getRuleContext(LiteratemapblockContext.class,0);
		}
		public DirectiveDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directiveDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiterateCodeMapListener ) ((LiterateCodeMapListener)listener).enterDirectiveDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiterateCodeMapListener ) ((LiterateCodeMapListener)listener).exitDirectiveDeclaration(this);
		}
	}

	public final DirectiveDeclarationContext directiveDeclaration() throws RecognitionException {
		DirectiveDeclarationContext _localctx = new DirectiveDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_directiveDeclaration);
		try {
			setState(20);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LITERATEMAPINVOKE:
				enterOuterAlt(_localctx, 1);
				{
				setState(18);
				literatemapinvoke();
				}
				break;
			case LITERATEMAPBLOCK:
				enterOuterAlt(_localctx, 2);
				{
				setState(19);
				literatemapblock();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteratemapinvokeContext extends ParserRuleContext {
		public TerminalNode LITERATEMAPINVOKE() { return getToken(LiterateCodeMapParser.LITERATEMAPINVOKE, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public LiteratemapinvokeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literatemapinvoke; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiterateCodeMapListener ) ((LiterateCodeMapListener)listener).enterLiteratemapinvoke(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiterateCodeMapListener ) ((LiterateCodeMapListener)listener).exitLiteratemapinvoke(this);
		}
	}

	public final LiteratemapinvokeContext literatemapinvoke() throws RecognitionException {
		LiteratemapinvokeContext _localctx = new LiteratemapinvokeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_literatemapinvoke);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			match(LITERATEMAPINVOKE);
			setState(23);
			params();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteratemapblockContext extends ParserRuleContext {
		public TerminalNode LITERATEMAPBLOCK() { return getToken(LiterateCodeMapParser.LITERATEMAPBLOCK, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public LiteratemapblockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literatemapblock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiterateCodeMapListener ) ((LiterateCodeMapListener)listener).enterLiteratemapblock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiterateCodeMapListener ) ((LiterateCodeMapListener)listener).exitLiteratemapblock(this);
		}
	}

	public final LiteratemapblockContext literatemapblock() throws RecognitionException {
		LiteratemapblockContext _localctx = new LiteratemapblockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_literatemapblock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(LITERATEMAPBLOCK);
			setState(26);
			params();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(LiterateCodeMapParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(LiterateCodeMapParser.RPAREN, 0); }
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LiterateCodeMapParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LiterateCodeMapParser.COMMA, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiterateCodeMapListener ) ((LiterateCodeMapListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiterateCodeMapListener ) ((LiterateCodeMapListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(LPAREN);
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER) | (1L << DQSTRING) | (1L << SQSTRING))) != 0)) {
				{
				setState(29);
				param();
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(30);
					match(COMMA);
					setState(31);
					param();
					}
					}
					setState(34); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==COMMA );
				}
			}

			setState(38);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(LiterateCodeMapParser.NUMBER, 0); }
		public TerminalNode DQSTRING() { return getToken(LiterateCodeMapParser.DQSTRING, 0); }
		public TerminalNode SQSTRING() { return getToken(LiterateCodeMapParser.SQSTRING, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiterateCodeMapListener ) ((LiterateCodeMapListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiterateCodeMapListener ) ((LiterateCodeMapListener)listener).exitParam(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_param);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER) | (1L << DQSTRING) | (1L << SQSTRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\f-\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\7\2\20\n\2\f\2\16\2\23\13\2\3\3"+
		"\3\3\5\3\27\n\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\6\6#\n\6\r\6\16"+
		"\6$\5\6\'\n\6\3\6\3\6\3\7\3\7\3\7\2\2\b\2\4\6\b\n\f\2\3\3\2\5\7\2*\2\21"+
		"\3\2\2\2\4\26\3\2\2\2\6\30\3\2\2\2\b\33\3\2\2\2\n\36\3\2\2\2\f*\3\2\2"+
		"\2\16\20\5\4\3\2\17\16\3\2\2\2\20\23\3\2\2\2\21\17\3\2\2\2\21\22\3\2\2"+
		"\2\22\3\3\2\2\2\23\21\3\2\2\2\24\27\5\6\4\2\25\27\5\b\5\2\26\24\3\2\2"+
		"\2\26\25\3\2\2\2\27\5\3\2\2\2\30\31\7\3\2\2\31\32\5\n\6\2\32\7\3\2\2\2"+
		"\33\34\7\4\2\2\34\35\5\n\6\2\35\t\3\2\2\2\36&\7\b\2\2\37\"\5\f\7\2 !\7"+
		"\n\2\2!#\5\f\7\2\" \3\2\2\2#$\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%\'\3\2\2\2"+
		"&\37\3\2\2\2&\'\3\2\2\2\'(\3\2\2\2()\7\t\2\2)\13\3\2\2\2*+\t\2\2\2+\r"+
		"\3\2\2\2\6\21\26$&";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}