// Generated from /home/baba_beda/mathLogicFinal/Logic.g4 by ANTLR 4.5.3

package parser;
import expression.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LogicParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LogicVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LogicParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(LogicParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicParser#disjunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisjunction(LogicParser.DisjunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicParser#conjunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConjunction(LogicParser.ConjunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicParser#negation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(LogicParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(LogicParser.VariableContext ctx);
}