package task4.annotation;

import task4.logic.Statement;

public class AnnotatedIR extends AnnotatedStatement {
    public int ruleId;
    public int lineNo;

    public AnnotatedIR(Statement statement, int ruleId, int lineNo) {
        super(statement);

        this.ruleId = ruleId;
        this.lineNo = lineNo;
    }
}
