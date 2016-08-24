package task4.annotation;

import task4.logic.Statement;

public class AnnotatedAxiom extends AnnotatedStatement {
    public int axiomId;

    public AnnotatedAxiom(Statement statement, int axiomId) {
        super(statement);

        this.axiomId = axiomId;
    }
}
