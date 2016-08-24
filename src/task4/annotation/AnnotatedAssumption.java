package task4.annotation;

import task4.logic.Statement;

public class AnnotatedAssumption extends AnnotatedStatement {
    public int lineNo;

    public AnnotatedAssumption(Statement statement, int lineNo) {
        super(statement);

        this.lineNo = lineNo;
    }
}
