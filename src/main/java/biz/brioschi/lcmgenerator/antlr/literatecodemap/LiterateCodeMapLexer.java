// Generated from /Users/m40030213/MyData/_workingdir/repositories/git/litteral-code-map-generator/antlr/literatecodemapper/LiterateCodeMap.g4 by ANTLR 4.9.2
package biz.brioschi.lcmgenerator.antlr.literatecodemap;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LiterateCodeMapLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LITERATEMAPINVOKE=1, LITERATEMAPBLOCK=2, NUMBER=3, DQSTRING=4, SQSTRING=5, 
		LPAREN=6, RPAREN=7, COMMA=8, FILLCHARS=9, WS=10;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LITERATEMAPINVOKE", "LITERATEMAPBLOCK", "NUMBER", "DQSTRING", "SQSTRING", 
			"LPAREN", "RPAREN", "COMMA", "FILLCHARS", "WS"
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


	public LiterateCodeMapLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LiterateCodeMap.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\fe\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\4\7\4>\n\4\f\4\16\4A\13\4\3\5\3\5\7\5E\n\5\f\5\16\5H\13"+
		"\5\3\5\3\5\3\6\3\6\7\6N\n\6\f\6\16\6Q\13\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\3\13\6\13`\n\13\r\13\16\13a\3\13\3\13\2\2\f\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\3\2\7\3\2\62;\6\2\f\f\17\17$"+
		"$^^\6\2\f\f\17\17))^^\3\2BB\5\2\13\f\16\17\"\"\2h\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\3\27\3\2\2\2\5*\3\2\2\2\7?\3\2\2\2"+
		"\tB\3\2\2\2\13K\3\2\2\2\rT\3\2\2\2\17V\3\2\2\2\21X\3\2\2\2\23Z\3\2\2\2"+
		"\25_\3\2\2\2\27\30\7B\2\2\30\31\7N\2\2\31\32\7k\2\2\32\33\7v\2\2\33\34"+
		"\7g\2\2\34\35\7t\2\2\35\36\7c\2\2\36\37\7v\2\2\37 \7g\2\2 !\7O\2\2!\""+
		"\7c\2\2\"#\7r\2\2#$\7K\2\2$%\7p\2\2%&\7x\2\2&\'\7q\2\2\'(\7m\2\2()\7g"+
		"\2\2)\4\3\2\2\2*+\7B\2\2+,\7N\2\2,-\7k\2\2-.\7v\2\2./\7g\2\2/\60\7t\2"+
		"\2\60\61\7c\2\2\61\62\7v\2\2\62\63\7g\2\2\63\64\7O\2\2\64\65\7c\2\2\65"+
		"\66\7r\2\2\66\67\7D\2\2\678\7n\2\289\7q\2\29:\7e\2\2:;\7m\2\2;\6\3\2\2"+
		"\2<>\t\2\2\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@\b\3\2\2\2A?\3\2"+
		"\2\2BF\7$\2\2CE\n\3\2\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2"+
		"\2\2HF\3\2\2\2IJ\7$\2\2J\n\3\2\2\2KO\7)\2\2LN\n\4\2\2ML\3\2\2\2NQ\3\2"+
		"\2\2OM\3\2\2\2OP\3\2\2\2PR\3\2\2\2QO\3\2\2\2RS\7)\2\2S\f\3\2\2\2TU\7*"+
		"\2\2U\16\3\2\2\2VW\7+\2\2W\20\3\2\2\2XY\7.\2\2Y\22\3\2\2\2Z[\n\5\2\2["+
		"\\\3\2\2\2\\]\b\n\2\2]\24\3\2\2\2^`\t\6\2\2_^\3\2\2\2`a\3\2\2\2a_\3\2"+
		"\2\2ab\3\2\2\2bc\3\2\2\2cd\b\13\2\2d\26\3\2\2\2\7\2?FOa\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}