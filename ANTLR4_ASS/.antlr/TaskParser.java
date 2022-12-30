// Generated from c:\Users\Bas-SilverPC\Downloads\Semester 10\Compiler's Lab\ANTLR4_ASS\Task.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaskParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, WHITESPACE=2, NEWLINE=3, ONE=4, ZERO=5;
	public static final int
		RULE_prog = 0, RULE_state_0 = 1, RULE_state_1 = 2, RULE_state_2 = 3, RULE_state_3 = 4;
	public static final String[] ruleNames = {
		"prog", "state_0", "state_1", "state_2", "state_3"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, "'1'", "'0'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "WHITESPACE", "NEWLINE", "ONE", "ZERO"
	};
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
	public String getGrammarFileName() { return "Task.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TaskParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public State_0Context state_0() {
			return getRuleContext(State_0Context.class,0);
		}
		public TerminalNode EOF() { return getToken(TaskParser.EOF, 0); }
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			state_0();
			setState(11);
			match(EOF);
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

	public static class State_0Context extends ParserRuleContext {
		public List<TerminalNode> ZERO() { return getTokens(TaskParser.ZERO); }
		public TerminalNode ZERO(int i) {
			return getToken(TaskParser.ZERO, i);
		}
		public TerminalNode ONE() { return getToken(TaskParser.ONE, 0); }
		public State_1Context state_1() {
			return getRuleContext(State_1Context.class,0);
		}
		public State_0Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_state_0; }
	}

	public final State_0Context state_0() throws RecognitionException {
		State_0Context _localctx = new State_0Context(_ctx, getState());
		enterRule(_localctx, 2, RULE_state_0);
		int _la;
		try {
			setState(27);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(16);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ZERO) {
					{
					{
					setState(13);
					match(ZERO);
					}
					}
					setState(18);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ZERO) {
					{
					{
					setState(19);
					match(ZERO);
					}
					}
					setState(24);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(25);
				match(ONE);
				setState(26);
				state_1();
				}
				break;
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

	public static class State_1Context extends ParserRuleContext {
		public State_0Context state_0() {
			return getRuleContext(State_0Context.class,0);
		}
		public List<TerminalNode> ONE() { return getTokens(TaskParser.ONE); }
		public TerminalNode ONE(int i) {
			return getToken(TaskParser.ONE, i);
		}
		public TerminalNode ZERO() { return getToken(TaskParser.ZERO, 0); }
		public State_2Context state_2() {
			return getRuleContext(State_2Context.class,0);
		}
		public State_1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_state_1; }
	}

	public final State_1Context state_1() throws RecognitionException {
		State_1Context _localctx = new State_1Context(_ctx, getState());
		enterRule(_localctx, 4, RULE_state_1);
		int _la;
		try {
			int _alt;
			setState(46);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(29);
						match(ONE);
						}
						} 
					}
					setState(34);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				}
				setState(35);
				state_0();
				print("01")
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ONE) {
					{
					{
					setState(38);
					match(ONE);
					}
					}
					setState(43);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(44);
				match(ZERO);
				setState(45);
				state_2();
				}
				break;
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

	public static class State_2Context extends ParserRuleContext {
		public TerminalNode ZERO() { return getToken(TaskParser.ZERO, 0); }
		public State_0Context state_0() {
			return getRuleContext(State_0Context.class,0);
		}
		public TerminalNode ONE() { return getToken(TaskParser.ONE, 0); }
		public State_3Context state_3() {
			return getRuleContext(State_3Context.class,0);
		}
		public State_2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_state_2; }
	}

	public final State_2Context state_2() throws RecognitionException {
		State_2Context _localctx = new State_2Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_state_2);
		try {
			setState(55);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				match(ZERO);
				setState(49);
				state_0();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(50);
				match(ONE);
				setState(51);
				state_3();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(52);
				state_0();
				print("10")
				}
				break;
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

	public static class State_3Context extends ParserRuleContext {
		public List<TerminalNode> ZERO() { return getTokens(TaskParser.ZERO); }
		public TerminalNode ZERO(int i) {
			return getToken(TaskParser.ZERO, i);
		}
		public List<TerminalNode> ONE() { return getTokens(TaskParser.ONE); }
		public TerminalNode ONE(int i) {
			return getToken(TaskParser.ONE, i);
		}
		public State_3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_state_3; }
	}

	public final State_3Context state_3() throws RecognitionException {
		State_3Context _localctx = new State_3Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_state_3);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(57);
					match(ZERO);
					}
					} 
				}
				setState(62);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(66);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(63);
					match(ONE);
					}
					} 
				}
				setState(68);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ZERO) {
				{
				{
				setState(69);
				match(ZERO);
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ONE) {
				{
				{
				setState(75);
				match(ONE);
				}
				}
				setState(80);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\7T\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\3\7\3\21\n\3\f\3\16\3\24\13\3"+
		"\3\3\7\3\27\n\3\f\3\16\3\32\13\3\3\3\3\3\5\3\36\n\3\3\4\7\4!\n\4\f\4\16"+
		"\4$\13\4\3\4\3\4\3\4\3\4\7\4*\n\4\f\4\16\4-\13\4\3\4\3\4\5\4\61\n\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5:\n\5\3\6\7\6=\n\6\f\6\16\6@\13\6\3\6\7"+
		"\6C\n\6\f\6\16\6F\13\6\3\6\7\6I\n\6\f\6\16\6L\13\6\3\6\7\6O\n\6\f\6\16"+
		"\6R\13\6\3\6\2\2\7\2\4\6\b\n\2\2\2Z\2\f\3\2\2\2\4\35\3\2\2\2\6\60\3\2"+
		"\2\2\b9\3\2\2\2\n>\3\2\2\2\f\r\5\4\3\2\r\16\7\2\2\3\16\3\3\2\2\2\17\21"+
		"\7\7\2\2\20\17\3\2\2\2\21\24\3\2\2\2\22\20\3\2\2\2\22\23\3\2\2\2\23\36"+
		"\3\2\2\2\24\22\3\2\2\2\25\27\7\7\2\2\26\25\3\2\2\2\27\32\3\2\2\2\30\26"+
		"\3\2\2\2\30\31\3\2\2\2\31\33\3\2\2\2\32\30\3\2\2\2\33\34\7\6\2\2\34\36"+
		"\5\6\4\2\35\22\3\2\2\2\35\30\3\2\2\2\36\5\3\2\2\2\37!\7\6\2\2 \37\3\2"+
		"\2\2!$\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#%\3\2\2\2$\"\3\2\2\2%&\5\4\3\2&\'"+
		"\b\4\1\2\'\61\3\2\2\2(*\7\6\2\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2"+
		"\2,.\3\2\2\2-+\3\2\2\2./\7\7\2\2/\61\5\b\5\2\60\"\3\2\2\2\60+\3\2\2\2"+
		"\61\7\3\2\2\2\62\63\7\7\2\2\63:\5\4\3\2\64\65\7\6\2\2\65:\5\n\6\2\66\67"+
		"\5\4\3\2\678\b\5\1\28:\3\2\2\29\62\3\2\2\29\64\3\2\2\29\66\3\2\2\2:\t"+
		"\3\2\2\2;=\7\7\2\2<;\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?D\3\2\2\2@"+
		">\3\2\2\2AC\7\6\2\2BA\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2EJ\3\2\2\2"+
		"FD\3\2\2\2GI\7\7\2\2HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KP\3\2\2\2"+
		"LJ\3\2\2\2MO\7\6\2\2NM\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2Q\13\3\2\2"+
		"\2RP\3\2\2\2\r\22\30\35\"+\609>DJP";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}