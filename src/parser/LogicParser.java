// Generated from /home/baba_beda/mathLogicFinal/Logic.g4 by ANTLR 4.5.3

package parser;
import expression.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LogicParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IMPLIES=1, OR=2, AND=3, NOT=4, OB=5, CB=6, VAR=7;
	public static final int
		RULE_expression = 0, RULE_disjunction = 1, RULE_conjunction = 2, RULE_negation = 3, 
		RULE_variable = 4;
	public static final String[] ruleNames = {
		"expression", "disjunction", "conjunction", "negation", "variable"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'->'", "'|'", "'&'", "'!'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "IMPLIES", "OR", "AND", "NOT", "OB", "CB", "VAR"
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
	public String getGrammarFileName() { return "Logic.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LogicParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public Expression value;
		public DisjunctionContext disjunction;
		public ExpressionContext expression;
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public TerminalNode IMPLIES() { return getToken(LogicParser.IMPLIES, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expression);
		try {
			setState(18);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(10);
				((ExpressionContext)_localctx).disjunction = disjunction();
				((ExpressionContext)_localctx).value =  ((ExpressionContext)_localctx).disjunction.value;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(13);
				((ExpressionContext)_localctx).disjunction = disjunction();
				setState(14);
				match(IMPLIES);
				setState(15);
				((ExpressionContext)_localctx).expression = expression();
				((ExpressionContext)_localctx).value =  new Implication(((ExpressionContext)_localctx).disjunction.value, ((ExpressionContext)_localctx).expression.value);
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

	public static class DisjunctionContext extends ParserRuleContext {
		public Expression value;
		public ConjunctionContext conjunction;
		public DisjunctionContext disjunction;
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public TerminalNode OR() { return getToken(LogicParser.OR, 0); }
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public DisjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterDisjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitDisjunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitDisjunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DisjunctionContext disjunction() throws RecognitionException {
		DisjunctionContext _localctx = new DisjunctionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_disjunction);
		try {
			setState(28);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				((DisjunctionContext)_localctx).conjunction = conjunction();
				((DisjunctionContext)_localctx).value =  ((DisjunctionContext)_localctx).conjunction.value;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(23);
				((DisjunctionContext)_localctx).conjunction = conjunction();
				setState(24);
				match(OR);
				setState(25);
				((DisjunctionContext)_localctx).disjunction = disjunction();
				((DisjunctionContext)_localctx).value =  new Disjunction(((DisjunctionContext)_localctx).conjunction.value, ((DisjunctionContext)_localctx).disjunction.value);
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

	public static class ConjunctionContext extends ParserRuleContext {
		public Expression value;
		public NegationContext negation;
		public ConjunctionContext conjunction;
		public NegationContext negation() {
			return getRuleContext(NegationContext.class,0);
		}
		public TerminalNode AND() { return getToken(LogicParser.AND, 0); }
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public ConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitConjunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitConjunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConjunctionContext conjunction() throws RecognitionException {
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_conjunction);
		try {
			setState(38);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				((ConjunctionContext)_localctx).negation = negation();
				((ConjunctionContext)_localctx).value =  ((ConjunctionContext)_localctx).negation.value;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(33);
				((ConjunctionContext)_localctx).negation = negation();
				setState(34);
				match(AND);
				setState(35);
				((ConjunctionContext)_localctx).conjunction = conjunction();
				((ConjunctionContext)_localctx).value =  new Conjunction(((ConjunctionContext)_localctx).negation.value, ((ConjunctionContext)_localctx).conjunction.value);
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

	public static class NegationContext extends ParserRuleContext {
		public Expression value;
		public VariableContext variable;
		public NegationContext negation;
		public ExpressionContext expression;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode NOT() { return getToken(LogicParser.NOT, 0); }
		public NegationContext negation() {
			return getRuleContext(NegationContext.class,0);
		}
		public TerminalNode OB() { return getToken(LogicParser.OB, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CB() { return getToken(LogicParser.CB, 0); }
		public NegationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitNegation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NegationContext negation() throws RecognitionException {
		NegationContext _localctx = new NegationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_negation);
		try {
			setState(52);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				((NegationContext)_localctx).variable = variable();
				((NegationContext)_localctx).value =  ((NegationContext)_localctx).variable.value;
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				match(NOT);
				setState(44);
				((NegationContext)_localctx).negation = negation();
				((NegationContext)_localctx).value =  new Negation(((NegationContext)_localctx).negation.value);
				}
				break;
			case OB:
				enterOuterAlt(_localctx, 3);
				{
				setState(47);
				match(OB);
				setState(48);
				((NegationContext)_localctx).expression = expression();
				setState(49);
				match(CB);
				((NegationContext)_localctx).value =  ((NegationContext)_localctx).expression.value;
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

	public static class VariableContext extends ParserRuleContext {
		public Expression value;
		public Token VAR;
		public TerminalNode VAR() { return getToken(LogicParser.VAR, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			((VariableContext)_localctx).VAR = match(VAR);
			((VariableContext)_localctx).value =  new Variable((((VariableContext)_localctx).VAR!=null?((VariableContext)_localctx).VAR.getText():null));
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\t<\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\25\n\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\37\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\5\4)\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\67"+
		"\n\5\3\6\3\6\3\6\3\6\2\2\7\2\4\6\b\n\2\2;\2\24\3\2\2\2\4\36\3\2\2\2\6"+
		"(\3\2\2\2\b\66\3\2\2\2\n8\3\2\2\2\f\r\5\4\3\2\r\16\b\2\1\2\16\25\3\2\2"+
		"\2\17\20\5\4\3\2\20\21\7\3\2\2\21\22\5\2\2\2\22\23\b\2\1\2\23\25\3\2\2"+
		"\2\24\f\3\2\2\2\24\17\3\2\2\2\25\3\3\2\2\2\26\27\5\6\4\2\27\30\b\3\1\2"+
		"\30\37\3\2\2\2\31\32\5\6\4\2\32\33\7\4\2\2\33\34\5\4\3\2\34\35\b\3\1\2"+
		"\35\37\3\2\2\2\36\26\3\2\2\2\36\31\3\2\2\2\37\5\3\2\2\2 !\5\b\5\2!\"\b"+
		"\4\1\2\")\3\2\2\2#$\5\b\5\2$%\7\5\2\2%&\5\6\4\2&\'\b\4\1\2\')\3\2\2\2"+
		"( \3\2\2\2(#\3\2\2\2)\7\3\2\2\2*+\5\n\6\2+,\b\5\1\2,\67\3\2\2\2-.\7\6"+
		"\2\2./\5\b\5\2/\60\b\5\1\2\60\67\3\2\2\2\61\62\7\7\2\2\62\63\5\2\2\2\63"+
		"\64\7\b\2\2\64\65\b\5\1\2\65\67\3\2\2\2\66*\3\2\2\2\66-\3\2\2\2\66\61"+
		"\3\2\2\2\67\t\3\2\2\289\7\t\2\29:\b\6\1\2:\13\3\2\2\2\6\24\36(\66";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}