package annotated_expression;

import expression.Expression;

/**
 * Created by baba_beda on 8/18/16.
 */
public class AnnotatedMP extends AnnotatedExpression {
    public int alphaIndex, alphaBetaIndex;
    public AnnotatedMP(Expression expression, int alphaIndex, int alphaBetaIndex) {
        this.expression = expression;
        this.alphaIndex = alphaIndex;
        this.alphaBetaIndex = alphaBetaIndex;
    }

    @Override
    public String toString() {
        return expression.toString() + " MP " + (alphaIndex + 1) + ", " + (alphaBetaIndex + 1);
    }
}
