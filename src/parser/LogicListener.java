// Generated from /home/baba_beda/mathLogicFinal/Logic.g4 by ANTLR 4.5.3

package parser;
import expression.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LogicParser}.
 */
public interface LogicListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LogicParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(LogicParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(LogicParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void enterDisjunction(LogicParser.DisjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void exitDisjunction(LogicParser.DisjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(LogicParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(LogicParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicParser#negation}.
	 * @param ctx the parse tree
	 */
	void enterNegation(LogicParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicParser#negation}.
	 * @param ctx the parse tree
	 */
	void exitNegation(LogicParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(LogicParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(LogicParser.VariableContext ctx);
}