package task4.annotation;

import task4.logic.Statement;

public class AnnotatedMP extends AnnotatedStatement {
    public int alpha;
    public int beta;

    public AnnotatedMP(Statement statement, int alpha, int beta) {
        super(statement);

        this.alpha = alpha;
        this.beta = beta;
    }
}
