package utils;

import expression.*;

import java.util.Map;

/**
 * Created by baba_beda on 8/18/16.
 */
public class Substituter {
    public static Expression substitute(Expression expression, Map<String, Expression> variables) {
        if (expression instanceof Implication) {
            return new Implication(substitute(((Implication) expression).getLeft(), variables), substitute(((Implication) expression).getRight(), variables));
        }
        if (expression instanceof Disjunction) {
            return new Disjunction(substitute(((Disjunction) expression).getLeft(), variables), substitute(((Disjunction) expression).getRight(), variables));
        }
        if (expression instanceof Conjunction) {
            return new Conjunction(substitute(((Conjunction) expression).getLeft(), variables), substitute(((Conjunction) expression).getRight(), variables));
        }
        if (expression instanceof Negation) {
            return new Negation(substitute(((Negation) expression).getInside(), variables));
        }
        if (expression instanceof Variable) {
            return variables.get(((Variable) expression).getValue());
        }
        return null;
    }
}
