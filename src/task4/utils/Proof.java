package task4.utils;

import task4.annotation.AnnotatedStatement;
import task4.exceptions.AnnotatorException;
import task4.logic.Statement;

import java.util.ArrayList;
import java.util.List;

// Helper class for convenience
public class Proof {
    private final List<Statement> context;
    private final List<AnnotatedStatement> statements;

    public Proof(List<Statement> context, List<AnnotatedStatement> statements) {
        this.context = context;
        this.statements = statements;
    }

    public List<AnnotatedStatement> getStatements() {
        return statements;
    }

    public Proof getAnnotatedProof() throws AnnotatorException {
        return new Proof(context, ProofAnnotator.getAnnotatedProof(context, statements));
    }

    public Proof deduce() throws AnnotatorException {
        List<Statement> newContext = new ArrayList<>(context);
        Statement assumption = newContext.remove(newContext.size() - 1);

        return new Proof(newContext, Deductor.deduce(ProofAnnotator.getAnnotatedProof(context, statements), assumption));
    }

    public Proof removeRedundantStatements() throws AnnotatorException {
        List<Statement> newStatements = Helper.removeRedundantStatements(ProofAnnotator.getAnnotatedProof(context, statements));

        return new Proof(context, Helper.annotateInContext(context, newStatements));
    }
}
