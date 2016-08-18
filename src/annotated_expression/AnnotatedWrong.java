package annotated_expression;

import expression.Expression;

/**
 * Created by baba_beda on 8/18/16.
 */
public class AnnotatedWrong extends AnnotatedExpression {
    public AnnotatedWrong(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return expression.toString();
    }
}
