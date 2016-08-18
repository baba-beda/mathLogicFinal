package annotated_expression;

import expression.Expression;

/**
 * Created by baba_beda on 8/18/16.
 */
public class AnnotatedAssumption extends AnnotatedExpression {
    public AnnotatedAssumption(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return expression.toString() + " assumption";
    }
}
