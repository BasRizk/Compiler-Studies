// Generated from Task_8.g4 by ANTLR 4.8

    import java.lang.Math;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Task_8Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DOT=1, ZERO=2, ONE=3, WS=4;
	public static final int
		RULE_start = 0, RULE_operation = 1, RULE_left = 2, RULE_right = 3, RULE_b_ = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "operation", "left", "right", "b_"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'0'", "'1'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DOT", "ZERO", "ONE", "WS"
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
	public String getGrammarFileName() { return "Task_8.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Task_8Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public OperationContext operation;
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public TerminalNode EOF() { return getToken(Task_8Parser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task_8Listener ) ((Task_8Listener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task_8Listener ) ((Task_8Listener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			((StartContext)_localctx).operation = operation();
			setState(11);
			match(EOF);
			System.out.println(((StartContext)_localctx).operation.val);
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

	public static class OperationContext extends ParserRuleContext {
		public double val;
		public LeftContext left;
		public RightContext right;
		public LeftContext left() {
			return getRuleContext(LeftContext.class,0);
		}
		public TerminalNode DOT() { return getToken(Task_8Parser.DOT, 0); }
		public RightContext right() {
			return getRuleContext(RightContext.class,0);
		}
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task_8Listener ) ((Task_8Listener)listener).enterOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task_8Listener ) ((Task_8Listener)listener).exitOperation(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			((OperationContext)_localctx).left = left();
			setState(15);
			match(DOT);
			setState(16);
			((OperationContext)_localctx).right = right();
			((OperationContext)_localctx).val =  ((OperationContext)_localctx).left.val + ((OperationContext)_localctx).right.val;
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

	public static class LeftContext extends ParserRuleContext {
		public double n;
		public double val;
		public B_Context b_;
		public LeftContext left;
		public B_Context b_() {
			return getRuleContext(B_Context.class,0);
		}
		public LeftContext left() {
			return getRuleContext(LeftContext.class,0);
		}
		public LeftContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_left; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task_8Listener ) ((Task_8Listener)listener).enterLeft(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task_8Listener ) ((Task_8Listener)listener).exitLeft(this);
		}
	}

	public final LeftContext left() throws RecognitionException {
		LeftContext _localctx = new LeftContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_left);
		try {
			setState(26);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(19);
				((LeftContext)_localctx).b_ = b_();
				setState(20);
				((LeftContext)_localctx).left = left();
				((LeftContext)_localctx).n =  ((LeftContext)_localctx).left.n + 1; ((LeftContext)_localctx).val =  ((LeftContext)_localctx).b_.val * Math.pow(2, _localctx.n) + ((LeftContext)_localctx).left.val;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(23);
				((LeftContext)_localctx).b_ = b_();
				((LeftContext)_localctx).n =  0; ((LeftContext)_localctx).val =  ((LeftContext)_localctx).b_.val;
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

	public static class RightContext extends ParserRuleContext {
		public double val;
		public B_Context b_;
		public RightContext right;
		public B_Context b_() {
			return getRuleContext(B_Context.class,0);
		}
		public RightContext right() {
			return getRuleContext(RightContext.class,0);
		}
		public RightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task_8Listener ) ((Task_8Listener)listener).enterRight(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task_8Listener ) ((Task_8Listener)listener).exitRight(this);
		}
	}

	public final RightContext right() throws RecognitionException {
		RightContext _localctx = new RightContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_right);
		try {
			setState(35);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(28);
				((RightContext)_localctx).b_ = b_();
				setState(29);
				((RightContext)_localctx).right = right();
				((RightContext)_localctx).val =  (((RightContext)_localctx).right.val * 0.5) + (((RightContext)_localctx).b_.val * 0.5);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				((RightContext)_localctx).b_ = b_();
				((RightContext)_localctx).val =  ((RightContext)_localctx).b_.val * 0.5;
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

	public static class B_Context extends ParserRuleContext {
		public double val;
		public TerminalNode ZERO() { return getToken(Task_8Parser.ZERO, 0); }
		public TerminalNode ONE() { return getToken(Task_8Parser.ONE, 0); }
		public B_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_b_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task_8Listener ) ((Task_8Listener)listener).enterB_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task_8Listener ) ((Task_8Listener)listener).exitB_(this);
		}
	}

	public final B_Context b_() throws RecognitionException {
		B_Context _localctx = new B_Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_b_);
		try {
			setState(41);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ZERO:
				enterOuterAlt(_localctx, 1);
				{
				setState(37);
				match(ZERO);
				((B_Context)_localctx).val =  0;
				}
				break;
			case ONE:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				match(ONE);
				((B_Context)_localctx).val =  1;
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\6.\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\5\4\35\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5&\n\5\3"+
		"\6\3\6\3\6\3\6\5\6,\n\6\3\6\2\2\7\2\4\6\b\n\2\2\2+\2\f\3\2\2\2\4\20\3"+
		"\2\2\2\6\34\3\2\2\2\b%\3\2\2\2\n+\3\2\2\2\f\r\5\4\3\2\r\16\7\2\2\3\16"+
		"\17\b\2\1\2\17\3\3\2\2\2\20\21\5\6\4\2\21\22\7\3\2\2\22\23\5\b\5\2\23"+
		"\24\b\3\1\2\24\5\3\2\2\2\25\26\5\n\6\2\26\27\5\6\4\2\27\30\b\4\1\2\30"+
		"\35\3\2\2\2\31\32\5\n\6\2\32\33\b\4\1\2\33\35\3\2\2\2\34\25\3\2\2\2\34"+
		"\31\3\2\2\2\35\7\3\2\2\2\36\37\5\n\6\2\37 \5\b\5\2 !\b\5\1\2!&\3\2\2\2"+
		"\"#\5\n\6\2#$\b\5\1\2$&\3\2\2\2%\36\3\2\2\2%\"\3\2\2\2&\t\3\2\2\2\'(\7"+
		"\4\2\2(,\b\6\1\2)*\7\5\2\2*,\b\6\1\2+\'\3\2\2\2+)\3\2\2\2,\13\3\2\2\2"+
		"\5\34%+";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}