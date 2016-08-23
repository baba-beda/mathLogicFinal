package utils;
import expression.*;

import java.util.*;


/**
 * Created by baba_beda on 8/18/16.
 */
public class Deductor {
    public static List<Expression> completeProof(Set<Expression> assumptions, Expression alpha, List<Expression> proof) {
        List<Expression> result = new ArrayList<>();
        Map<Expression,Integer> expressionIndex = new HashMap<>();
        Map<Expression, Pair> mpPairs = new HashMap<>();
        Map<Expression, Integer> alphaRequired = new HashMap<>();
        for (int i = 0; i < proof.size(); i++) {
            Expression curExpr = proof.get(i);
            int a = Axioms.isAxiom(curExpr);
            if (a > 0 || assumptions.contains(curExpr)) {
                result.add(curExpr);
                result.add(Axioms.getAxiom1(curExpr, alpha));
                result.add(new Implication(alpha, curExpr));
            }
            else {
                if (curExpr.equals(alpha)) {
                    result.addAll(Proofs.getSelfCons(alpha));
                }
                else if (mpPairs.containsKey(curExpr)) {
                    Expression deltaJ = proof.get(mpPairs.get(curExpr).getFst());
                    Expression deltaK = proof.get(mpPairs.get(curExpr).getSnd());
                    result.add(Axioms.getAxiom2(alpha, deltaJ, curExpr));
                    result.add(new Implication(new Implication(alpha, deltaK), new Implication(alpha, curExpr)));
                    result.add(new Implication(alpha, curExpr));
                }
                else {
                    result.add(curExpr);
                }
            }
            if (curExpr instanceof Implication) {
                Expression alphaCur = ((Implication) curExpr).getLeft();
                Expression betaCur = ((Implication) curExpr).getRight();
                if (expressionIndex.containsKey(alphaCur)) {
                    mpPairs.put(betaCur, new Pair(expressionIndex.get(alphaCur), i));
                }
                else {
                    alphaRequired.put(alphaCur, i);
                }
            }
            if (alphaRequired.containsKey(curExpr)) {
                mpPairs.put(((Implication) proof.get(alphaRequired.get(curExpr))).getRight(), new Pair(i, alphaRequired.get(curExpr)));
                alphaRequired.remove(curExpr);
            }
            expressionIndex.put(curExpr, i);
        }
        return result;
    }
}
