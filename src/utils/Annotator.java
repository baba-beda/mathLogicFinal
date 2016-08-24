package utils;

import annotated_expression.*;
import com.sun.istack.internal.Pool;
import expression.*;

import java.util.*;

/**
 * Created by baba_beda on 8/18/16.
 */
public class Annotator {
    private static boolean annotatedOnce = false;
    private static boolean[] used;


    private static List<AnnotatedExpression> annotateProof(List<Expression> proof, Set<Expression> assumptions) {
        Map<Expression, Integer> expressionIndex = new HashMap<>();
        Map<Expression, HashSet<Integer>> alphaRequired = new HashMap<>();
        Map<Expression, Pair> mpPairs = new HashMap<>();
        List<AnnotatedExpression> result = new ArrayList<>();

        for (int i = 0; i < proof.size(); i++) {
            Expression curExpr = proof.get(i);
            int a = Axioms.isAxiom(curExpr);
            if (a > 0) {
                result.add(new AnnotatedAxiom(curExpr, a));
            }
            else {
                if (assumptions.contains(curExpr)) {
                    result.add(new AnnotatedAssumption(curExpr));
                }
                else if (mpPairs.containsKey(curExpr)) {
                    result.add(new AnnotatedMP(curExpr, mpPairs.get(curExpr).getFst(), mpPairs.get(curExpr).getSnd()));
                }
                else {
                    result.add(new AnnotatedWrong(curExpr));
                    break;
                }
            }

            if (curExpr instanceof Implication) {
                Expression alpha = ((Implication) curExpr).getLeft();
                Expression beta = ((Implication) curExpr).getRight();
                if (expressionIndex.containsKey(alpha)) {
                    mpPairs.put(beta, new Pair(expressionIndex.get(alpha),i));
                }
                else {
                    alphaRequired.putIfAbsent(alpha, new HashSet<>());
                    alphaRequired.get(alpha).add(i);
                }
            }

            if (alphaRequired.containsKey(curExpr)) {
                for (int k : alphaRequired.get(curExpr)) {
                    mpPairs.put(((Implication) proof.get(k)).getRight(), new Pair(i, k));
                }
                alphaRequired.remove(curExpr);
            }
            expressionIndex.put(curExpr, i);
        }
        return removeRedundantStatements(result, assumptions);
    }

    private static List<AnnotatedExpression> removeRedundantStatements(List<AnnotatedExpression> annotatedProof, Set<Expression> assumptions) {
        if (annotatedOnce) {
            return annotatedProof;
        }
        used = new boolean[annotatedProof.size()];
        markImportantStatements(annotatedProof.size() - 1, annotatedProof);
        List<Expression> resultAux = new ArrayList<>();
        for (int i = 0; i < annotatedProof.size(); i++) {
            if (used[i]) {
                resultAux.add(annotatedProof.get(i).expression);
            }
        }
        annotatedOnce = true;
        return annotateProof(resultAux, assumptions);

    }

    private static void markImportantStatements(int current, List<AnnotatedExpression> annotatedProof) {
        used[current] = true;
        if (annotatedProof.get(current) instanceof AnnotatedMP) {
            markImportantStatements(((AnnotatedMP) annotatedProof.get(current)).alphaIndex, annotatedProof);
            markImportantStatements(((AnnotatedMP) annotatedProof.get(current)).alphaBetaIndex, annotatedProof);
        }
    }

    public static List<String> getAnnotatedProof(List<Expression> proof, Set<Expression> assumptions) {
        List<AnnotatedExpression> result = annotateProof(proof, assumptions);
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            ans.add("(" + (i + 1) + ") " + result.get(i).toString());
            if (result.get(i) instanceof AnnotatedWrong) {
                ans.add("Proof is incorrect from statement " + (i + 1));
                break;
            }
        }
        return ans;
    }
}
