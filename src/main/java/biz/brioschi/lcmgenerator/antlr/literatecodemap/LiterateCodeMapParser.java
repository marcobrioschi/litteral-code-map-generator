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
		LITERATEMAPINVOKE=1, NUMBER=2, DQSTRING=3, SQSTRING=4, LPAREN=5, RPAREN=6, 
		COMMA=7, FILLCHARS=8, WS=9;
	public static final int
		RULE_commentsentence = 0, RULE_directiveDeclaration = 1, RULE_literatemapinvoke = 2, 
		RULE_params = 3, RULE_param = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"commentsentence", "directiveDeclaration", "literatemapinvoke", "params", 
			"param"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'@LiterateMapInvoke'", null, null, null, "'('", "')'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LITERATEMAPINVOKE", "NUMBER", "DQSTRING", "SQSTRING", "LPAREN", 
			"RPAREN", "COMMA", "FILLCHARS", "WS"
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
			setState(13);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LITERATEMAPINVOKE) {
				{
				{
				setState(10);
				directiveDeclaration();
				}
				}
				setState(15);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			literatemapinvoke();
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
			setState(18);
			match(LITERATEMAPINVOKE);
			setState(19);
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
		enterRule(_localctx, 6, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			match(LPAREN);
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER) | (1L << DQSTRING) | (1L << SQSTRING))) != 0)) {
				{
				setState(22);
				param();
				setState(25); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(23);
					match(COMMA);
					setState(24);
					param();
					}
					}
					setState(27); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==COMMA );
				}
			}

			setState(31);
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
		enterRule(_localctx, 8, RULE_param);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13&\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\7\2\16\n\2\f\2\16\2\21\13\2\3\3\3\3\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\6\5\34\n\5\r\5\16\5\35\5\5 \n\5\3\5\3\5\3\6\3"+
		"\6\3\6\2\2\7\2\4\6\b\n\2\3\3\2\4\6\2#\2\17\3\2\2\2\4\22\3\2\2\2\6\24\3"+
		"\2\2\2\b\27\3\2\2\2\n#\3\2\2\2\f\16\5\4\3\2\r\f\3\2\2\2\16\21\3\2\2\2"+
		"\17\r\3\2\2\2\17\20\3\2\2\2\20\3\3\2\2\2\21\17\3\2\2\2\22\23\5\6\4\2\23"+
		"\5\3\2\2\2\24\25\7\3\2\2\25\26\5\b\5\2\26\7\3\2\2\2\27\37\7\7\2\2\30\33"+
		"\5\n\6\2\31\32\7\t\2\2\32\34\5\n\6\2\33\31\3\2\2\2\34\35\3\2\2\2\35\33"+
		"\3\2\2\2\35\36\3\2\2\2\36 \3\2\2\2\37\30\3\2\2\2\37 \3\2\2\2 !\3\2\2\2"+
		"!\"\7\b\2\2\"\t\3\2\2\2#$\t\2\2\2$\13\3\2\2\2\5\17\35\37";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}