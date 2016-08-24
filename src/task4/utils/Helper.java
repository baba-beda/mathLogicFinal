package task4.utils;

import task4.annotation.*;
import task4.exceptions.ParserException;
import task4.logic.Statement;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static List<Statement> axioms = parsePreloadedLines(
            "$1->$2->$1",
            "($1->$2)->($1->$2->$3)->($1->$3)",
            "$1->$2->$1&$2",
            "$1&$2->$1",
            "$1&$2->$2",
            "$1->$1|$2",
            "$2->$1|$2",
            "($1->$3)->($2->$3)->($1|$2->$3)",
            "($1->$2)->($1->!$2)->!$1",
            "!!$1->$1"
    );

    public static List<Statement> arithmAxioms = parsePreloadedLines(
            "#1=#2->#1'=#2'",
            "#1=#2->#1=#3->#2=#3",
            "#1'=#2'->#1=#2",
            "!#1'=0",
            "#1+#2'=(#1+#2)'",
            "#1+0=#1",
            "#1*0=0",
            "#1*#2'=#1*#2+#1"
    );

    public static List<Statement> parseSomeLines(String... lines) throws ParserException {
        PredicateParser parser = new PredicateParser();
        List<Statement> statements = new ArrayList<>(lines.length);

        for (String line : lines) {
            Statement statement = parser.parse(line);
            statements.add(statement);
        }

        return statements;
    }

    public static List<Statement> parsePreloadedLines(String... lines) {
        try {
            return parseSomeLines(lines);
        } catch (ParserException ignore) {
            return null;
        }
    }

    // just a dfs-like check of used statements
    private static void markImportantStatements(boolean[] used, List<AnnotatedStatement> statements, int current) {
        AnnotatedStatement annStatement = statements.get(current);

        used[current] = true;

        if (annStatement instanceof AnnotatedMP) {
            int alpha = ((AnnotatedMP) annStatement).alpha;
            int beta = ((AnnotatedMP) annStatement).beta;

            used[alpha] = true;
            used[beta] = true;
            markImportantStatements(used, statements, alpha);
            markImportantStatements(used, statements, beta);
        } else if (annStatement instanceof AnnotatedIR) {
            int lineNo = ((AnnotatedIR) annStatement).lineNo;

            used[lineNo] = true;
            markImportantStatements(used, statements, lineNo);
        }
    }

    public static List<Statement> removeRedundantStatements(List<AnnotatedStatement> statements) {
        List<Statement> relevantStatements = new ArrayList<>();
        boolean[] used = new boolean[statements.size()];

        for (int i = 0; i < statements.size(); i++) {
            if (statements.get(i) instanceof Unannotated) {
                used[i] = true;
            }
        }

        markImportantStatements(used, statements, statements.size() - 1);

        for (int i = 0; i < statements.size(); i++) {
            if (used[i]) {
                relevantStatements.add(statements.get(i).statement);
            }
        }

        return relevantStatements;
    }

    public static List<AnnotatedStatement> annotateInContext(List<Statement> context, List<Statement> statements) {
        List<AnnotatedStatement> annStatements = new ArrayList<>(statements.size());

        for (Statement stmt : statements) {
            int assumptionNum = -1;

            for (int i = 0; i < context.size(); i++) {
                if (stmt.equals(context.get(i))) {
                    assumptionNum = i;
                    break;
                }
            }

            if (assumptionNum != -1) {
                annStatements.add(new AnnotatedAssumption(stmt, assumptionNum));
            } else {
                annStatements.add(new Unannotated(stmt));
            }
        }

        return annStatements;
    }

}
