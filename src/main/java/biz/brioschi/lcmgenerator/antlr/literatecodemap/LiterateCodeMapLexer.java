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
		LITERATEMAPINVOKE=1, NUMBER=2, DQSTRING=3, SQSTRING=4, LPAREN=5, RPAREN=6, 
		COMMA=7, FILLCHARS=8, WS=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LITERATEMAPINVOKE", "NUMBER", "DQSTRING", "SQSTRING", "LPAREN", "RPAREN", 
			"COMMA", "FILLCHARS", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\13Q\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\3\7\3*\n\3\f\3\16\3-\13\3\3\4\3\4\7\4\61\n\4\f\4\16\4\64\13\4\3\4\3\4"+
		"\3\5\3\5\7\5:\n\5\f\5\16\5=\13\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\n\6\nL\n\n\r\n\16\nM\3\n\3\n\2\2\13\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\3\2\7\3\2\62;\6\2\f\f\17\17$$^^\6\2\f\f\17\17))^"+
		"^\3\2BB\5\2\13\f\16\17\"\"\2T\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\3\25\3\2\2\2\5+\3\2\2\2\7.\3\2\2\2\t\67\3\2\2\2\13@\3\2\2\2\rB\3\2"+
		"\2\2\17D\3\2\2\2\21F\3\2\2\2\23K\3\2\2\2\25\26\7B\2\2\26\27\7N\2\2\27"+
		"\30\7k\2\2\30\31\7v\2\2\31\32\7g\2\2\32\33\7t\2\2\33\34\7c\2\2\34\35\7"+
		"v\2\2\35\36\7g\2\2\36\37\7O\2\2\37 \7c\2\2 !\7r\2\2!\"\7K\2\2\"#\7p\2"+
		"\2#$\7x\2\2$%\7q\2\2%&\7m\2\2&\'\7g\2\2\'\4\3\2\2\2(*\t\2\2\2)(\3\2\2"+
		"\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,\6\3\2\2\2-+\3\2\2\2.\62\7$\2\2/\61\n"+
		"\3\2\2\60/\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\65\3\2"+
		"\2\2\64\62\3\2\2\2\65\66\7$\2\2\66\b\3\2\2\2\67;\7)\2\28:\n\4\2\298\3"+
		"\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<>\3\2\2\2=;\3\2\2\2>?\7)\2\2?\n\3"+
		"\2\2\2@A\7*\2\2A\f\3\2\2\2BC\7+\2\2C\16\3\2\2\2DE\7.\2\2E\20\3\2\2\2F"+
		"G\n\5\2\2GH\3\2\2\2HI\b\t\2\2I\22\3\2\2\2JL\t\6\2\2KJ\3\2\2\2LM\3\2\2"+
		"\2MK\3\2\2\2MN\3\2\2\2NO\3\2\2\2OP\b\n\2\2P\24\3\2\2\2\7\2+\62;M\3\2\3"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}