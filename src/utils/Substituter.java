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
        if (expression instanceof Equality) {
            return new Equality(substitute(((Equality) expression).getLeft(), variables), substitute(((Equality) expression).getRight(), variables));
        }
        if (expression instanceof Exists) {
            Variable v = ((Exists) expression).getVar();
            if (variables.containsKey(v.getValue())) {
                System.err.println("Trying to substitute bounded variable " + v.getValue() + " in statement " + expression.toString());
                System.exit(1);
            }
            return new Exists(((Exists) expression).getVar(), substitute(((Exists) expression).getExpr(), variables));
        }
        if (expression instanceof Forall) {
            Variable v = ((Forall) expression).getVar();
            if (variables.containsKey(v.getValue())) {
                System.err.println("Trying to substitute bounded variable " + v.getValue() + " in statement " + expression.toString());
                System.exit(1);
            }
            return new Forall(((Forall) expression).getVar(), substitute(((Forall) expression).getExpr(), variables));
        }
        if (expression instanceof Function) {
            Function fun = new Function(((Function) expression).getName());
            for (int i = 0; i < ((Function) expression).getLength(); i++) {
                fun.addTerm(substitute(((Function) expression).getTerm(i), variables));
            }
            return fun;
        }
        if (expression instanceof Predicate) {
            Predicate pred = new Predicate(((Predicate) expression).getName());
            for (int i = 0; i < ((Predicate) expression).getLength(); i++) {
                pred.addTerm(substitute(((Predicate) expression).getTerm(i), variables));
            }
            return pred;
        }
        if (expression instanceof Multiplicity) {
            return new Multiplicity(substitute(((Multiplicity) expression).getLeft(), variables), substitute(((Multiplicity) expression).getRight(), variables));
        }
        if (expression instanceof Sum) {
            return new Sum(substitute(((Sum) expression).getLeft(), variables), substitute(((Sum) expression).getRight(), variables));
        }
        if (expression instanceof Next) {
            return new Next(substitute(((Next) expression).getInside(), variables));
        }
        if (expression instanceof Zero) {
            return expression;
        }
        if (expression instanceof Variable) {
            return variables.get(((Variable) expression).getValue());
        }
        return null;
    }
}
