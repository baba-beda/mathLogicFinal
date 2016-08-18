package annotated_expression;

import expression.Expression;

/**
 * Created by baba_beda on 8/18/16.
 */
public class AnnotatedAxiom extends AnnotatedExpression {
    int axiomNumber;
    public AnnotatedAxiom(Expression expression, int axiomNumber) {
        this.expression = expression;
        this.axiomNumber = axiomNumber;
    }

    @Override
    public String toString() {
        return expression.toString() + " axiom " + axiomNumber;
    }
}
