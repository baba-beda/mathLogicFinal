package expression;

import utils.ExpressionType;

/**
 * Created by baba_beda on 8/17/16.
 */
public class Negation extends Expression {
    Expression inside;

    public Negation(Expression inside) {
        this.inside = inside;
    }

    @Override
    public String toString() {
        return "!" + inside.toString();
    }

    public Expression getInside() {
        return inside;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Negation negation = (Negation) o;

        return inside.equals(negation.inside);

    }

    @Override
    public int hashCode() {
        return inside.hashCode();
    }
}
