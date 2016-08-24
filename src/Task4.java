import task4.annotation.*;
import task4.exceptions.AnnotatorException;
import task4.exceptions.ParserException;
import task4.logic.Implication;
import task4.logic.Statement;
import task4.utils.PredicateParser;
import task4.utils.Proof;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by baba_beda on 8/24/16.
 */
public class Task4 {
    static void solve() {
        try (Scanner in = new Scanner(new FileInputStream(new File("task4.in")));
             PrintWriter out = new PrintWriter(new File("task4.out"))) {
            PredicateParser parser = new PredicateParser();
            List<Statement> context = new ArrayList<>();
            List<AnnotatedStatement> statements = new ArrayList<>();

            String[] header = in.next().split("\\|-");
            String[] contextStr = commaSplit(header[0]);

            Statement expectedBeta = parser.parse(header[1]);
            Set<String> freeContextVariables = new HashSet<>();

            for (int i = 0; i < contextStr.length; i++) {
                try {
                    Statement statement = parser.parse(contextStr[i]);
                    context.add(statement);
                    if (i == contextStr.length - 1) {
                        freeContextVariables.addAll(statement.getFreeVariables());
                    }
                    statements.add(new AnnotatedAssumption(statement, i));
                } catch (ParserException ignore) {
                }
            }

            // parse statements
            while (in.hasNext()) {
                Statement stmt = parser.parse(in.next());
                statements.add(new Unannotated(stmt));
            }

            // combine context and statements in a Proof instance
            Proof proof = new Proof(context, statements);

            // perform deduction and annotate proof
            Proof annotatedDeducedProof = proof.deduce().removeRedundantStatements().getAnnotatedProof();
            List<AnnotatedStatement> proofStatements = annotatedDeducedProof.getStatements();

            // prepare the error message
            String errorFormat = "Proof is incorrect starting with statement %d: %s\n";

            Statement actualBeta = statements.get(statements.size() - 1).statement;
            if (!actualBeta.equals(expectedBeta)) {
                System.err.println("Unexpected conclusion. Expected " + expectedBeta + ", but got " + actualBeta);
            } else {
                for (int i = 0; i < proofStatements.size(); i++) {
                    AnnotatedStatement annotatedStatement = proofStatements.get(i);
                    Statement statement = annotatedStatement.statement;
                    Statement toCheck = null;
                    boolean isAxiom = true;

                    if (annotatedStatement instanceof AnnotatedAxiom) {
                        if (((AnnotatedAxiom) annotatedStatement).axiomId == 10) {
                            toCheck = ((Implication) statement).left;
                        } else if (((AnnotatedAxiom) annotatedStatement).axiomId == 11) {
                            toCheck = ((Implication) statement).right;
                        }
                    } else if (annotatedStatement instanceof AnnotatedIR) {
                        if (((AnnotatedIR) annotatedStatement).ruleId == 1) {
                            toCheck = ((Implication) statement).right;
                        } else if (((AnnotatedIR) annotatedStatement).ruleId == 2) {
                            toCheck = ((Implication) statement).left;
                        }
                        isAxiom = false;
                    }

                    if (toCheck != null) {
                        Set<String> boundVars = toCheck.getBoundVariables();
                        for (String var : freeContextVariables) {
                            if (boundVars.contains(var) && !isAxiom) {
                                out.format(errorFormat, i + 1, "Using a rule with a quantifier over variable " + var + ", which is free in assumption " + context.get(context.size() - 1));
                                System.exit(1);
                            }
                        }
                    }
                }

                for (int i = 0; i < proofStatements.size(); i++) {
                    AnnotatedStatement annotatedStatement = proofStatements.get(i);
                    Statement stmt = annotatedStatement.statement;
                    out.print("" + (i + 1) + ") " + stmt + " ");
                    if (annotatedStatement instanceof AnnotatedAssumption) {
                        out.print("assumption " + (((AnnotatedAssumption) annotatedStatement).lineNo + 1) + "");
                    } else if (annotatedStatement instanceof AnnotatedAxiom) {
                        out.print("axiom " + (((AnnotatedAxiom) annotatedStatement).axiomId + 1) + "");
                    }
                    if (annotatedStatement instanceof AnnotatedMP) {
                        AnnotatedMP mpStatement = (AnnotatedMP) annotatedStatement;
                        out.print("MP " + (mpStatement.alpha + 1) + ", " + (mpStatement.beta + 1) + "");
                    }
                    if (annotatedStatement instanceof AnnotatedIR) {
                        AnnotatedIR irStatement = (AnnotatedIR) annotatedStatement;
                        out.print("inference rule " + irStatement.ruleId + ", " + (irStatement.lineNo + 1) + "");
                    }
                    out.println();
                }
            }

            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            System.err.println("Error while parsing " + e.getLine());
        } catch (AnnotatorException e) {
            System.err.println("Error while annotating " + e.getStatement() + ": " + e.getMessage());
        }
    }

    private static String[] commaSplit(String s) {
        int balance = 0;
        List<String> temp = new ArrayList<>();
        int k = 0;
        int lastPos = 0;
        while (k < s.length()) {
            char c = s.charAt(k);
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
            } else if (c == ',' && balance == 0) {
                temp.add(s.substring(lastPos, k));
                lastPos = k + 1;
            }
            k++;
        }
        temp.add(s.substring(lastPos, s.length()));
        return temp.toArray(new String[temp.size()]);
    }
}