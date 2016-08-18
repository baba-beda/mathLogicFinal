package expression;

/**
 * Created by baba_beda on 8/17/16.
 */
public class Expression {
    public boolean isImplication() {
        return this instanceof Implication;
    }

    public boolean isDisjunction() {
        return this instanceof Disjunction;
    }

    public boolean isConjunction() {
        return this instanceof Conjunction;
    }

    public boolean isNegation() {
        return this instanceof Negation;
    }
}
