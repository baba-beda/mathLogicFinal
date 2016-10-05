package utils;

import com.sun.istack.internal.Pool;
import com.sun.org.apache.xpath.internal.operations.Neg;
import expression.*;

import javax.naming.CompositeName;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by baba_beda on 8/19/16.
 */
public class Prover {
    public static boolean evaluate(Expression expression, String bitmask) {
        if (expression instanceof Implication) {
            return !evaluate(((Implication) expression).getLeft(), bitmask) || evaluate(((Implication) expression).getRight(), bitmask);
        }
        if (expression instanceof Disjunction) {
            return evaluate(((Disjunction) expression).getLeft(), bitmask) || evaluate(((Disjunction) expression).getRight(), bitmask);
        }
        if (expression instanceof Conjunction) {
            return evaluate(((Conjunction) expression).getLeft(), bitmask) && evaluate(((Conjunction) expression).getRight(), bitmask);
        }
        if (expression instanceof Negation) {
            return !evaluate(((Negation) expression).getInside(), bitmask);
        }
        if (expression instanceof Variable) {
            return (bitmask.charAt(bitmask.length() - expression.getRealVariables().get(((Variable) expression).getValue()) - 1) == '1');
        }
        return false;
    }

    public static List<Expression> prove(Expression expression) {
        int varCount = expression.getRealVariables().size();
        int range = 1 << (varCount);
        List<Expression>[] proofs = new ArrayList[range];
        for (int bitmask = 0; bitmask < range; bitmask++) {
            String strBitmask = bitmaskToString(bitmask);
            proofs[bitmask] = proveWithEstimation(expression, strBitmask);
            if (proofs[bitmask] == null) {
                System.out.print("Statement " + expression.toString() + " is wrong with ");
                for (Map.Entry<String, Integer> entry : expression.getRealVariables().entrySet()) {
                    System.out.print(entry.getKey() + "=" + (strBitmask.charAt(strBitmask.length() - entry.getValue() - 1) == '1' ? "T " : "F "));
                }
                return null;
            }
        }
        String[] variables = new String[varCount];
        for (Map.Entry<String, Integer> entry : expression.getRealVariables().entrySet()) {
            variables[entry.getValue()] = entry.getKey();
        }

        int step = 2;
        for (int i = 0; i < varCount; i++) {
            Expression alpha = new Variable(variables[i]);
            for (int j = 0; j < range; j += step) {
                Set<Expression> assumptions = new HashSet<>();
                String bitmask = bitmaskToString(j);
                for (int k = i + 1; k < varCount; k++) {
                    assumptions.add(bitmask.charAt(bitmask.length() - expression.getRealVariables().get(variables[k]) - 1) == '1' ? new Variable(variables[k]) : new Negation(new Variable(variables[k])));
                }

                List<Expression> proof1 = Deductor.completeProof(assumptions, new Negation(alpha), proofs[j]);
                List<Expression> proof2 = Deductor.completeProof(assumptions, alpha, proofs[j + step / 2]);

                proofs[j].clear();
                proofs[j].addAll(proof1);
                proofs[j].addAll(proof2);
                proofs[j].addAll(Proofs.getTertiumNonDatum(alpha));
                proofs[j].addAll(Proofs.getExclusion(expression, alpha));
            }
            step *= 2;
        }
        return proofs[0];
    }

    private static List<Expression> proveWithEstimation(Expression expression, String bitmask) {
        if (!evaluate(expression, bitmask)) {
            return null;
        }
        Set<Expression> context = new HashSet<>();
        for (Map.Entry<String, Integer> entry : expression.getRealVariables().entrySet()) {
            if (bitmask.charAt(bitmask.length() - entry.getValue() - 1) == '1') {
                context.add(new Variable(entry.getKey()));
            }
            else {
                context.add(new Negation(new Variable(entry.getKey())));
            }
        }

        return proveRecursively(expression, context, bitmask);
    }

    private static List<Expression> proveRecursively(Expression expression, Set<Expression> context, String bitmask) {
        List<Expression> result = new ArrayList<>();
        if (expression instanceof Implication) {
            boolean leftEst = evaluate(((Implication) expression).getLeft(), bitmask);
            boolean rightEst = evaluate(((Implication) expression).getRight(), bitmask);
            if (leftEst) {
                result.addAll(proveRecursively(((Implication) expression).getLeft(), context, bitmask));
                if (rightEst) {
                    result.addAll(proveRecursively(((Implication) expression).getRight(), context, bitmask));

                    result.addAll(Proofs.getYesYesImpl(((Implication) expression).getLeft(), ((Implication) expression).getRight()));
                }
            }
            else {
                result.addAll(proveRecursively(new Negation(((Implication) expression).getLeft()), context, bitmask));
                if (rightEst) {
                    result.addAll(proveRecursively(((Implication) expression).getRight(), context, bitmask));
                    result.addAll(Proofs.getNoYesImpl(((Implication) expression).getLeft(), ((Implication) expression).getRight()));
                }
                else {
                    result.addAll(proveRecursively(new Negation(((Implication) expression).getRight()), context, bitmask));
                    result.addAll(Proofs.getNoNoImpl(((Implication) expression).getLeft(), ((Implication) expression).getRight()));
                }
            }
        }
        if (expression instanceof Conjunction) {
            boolean leftEst = evaluate(((Conjunction) expression).getLeft(), bitmask);
            boolean rightEst = evaluate(((Conjunction) expression).getRight(), bitmask);
            if (leftEst && rightEst) {
                result.addAll(proveRecursively(((Conjunction) expression).getLeft(), context, bitmask));
                result.addAll(proveRecursively(((Conjunction) expression).getRight(), context, bitmask));
                result.addAll(Proofs.getYesYesAnd(((Conjunction) expression).getLeft(), ((Conjunction) expression).getRight()));
            }
        }
        if (expression instanceof Disjunction) {
            boolean leftEst = evaluate(((Disjunction) expression).getLeft(), bitmask);
            boolean rightEst = evaluate(((Disjunction) expression).getRight(), bitmask);
            if (leftEst) {
                result.addAll(proveRecursively(((Disjunction) expression).getLeft(), context, bitmask));
                result.addAll(Proofs.getYesNoOr(((Disjunction) expression).getLeft(), ((Disjunction) expression).getRight()));
            }
            else if (rightEst) {
                result.addAll(proveRecursively(((Disjunction) expression).getRight(), context, bitmask));
                result.addAll(Proofs.getNoYesOr(((Disjunction) expression).getLeft(), ((Disjunction) expression).getRight()));
            }
        }
        if (expression instanceof Negation) {
            Expression inside = ((Negation) expression).getInside();
            if (inside instanceof Negation) {
                result.addAll(proveRecursively(((Negation) inside).getInside(), context, bitmask));
                result.addAll(Proofs.getAddDoubleNot(((Negation) inside).getInside()));
            }
            if (inside instanceof Implication) {
                boolean leftEst = evaluate(((Implication) inside).getLeft(), bitmask);
                boolean rightEst = evaluate(((Implication) inside).getRight(), bitmask);
                if (leftEst && !rightEst) {
                    result.addAll(proveRecursively(((Implication) inside).getLeft(), context, bitmask));
                    result.addAll(proveRecursively(new Negation(((Implication) inside).getRight()), context, bitmask));
                    result.addAll(Proofs.getYesNoNotImpl(((Implication) inside).getLeft(), ((Implication) inside).getRight()));
                }
            }
            if (inside instanceof Conjunction) {
                boolean leftEst = evaluate(((Conjunction) inside).getLeft(), bitmask);
                boolean rightEst = evaluate(((Conjunction) inside).getRight(), bitmask);
                if (leftEst) {
                    result.addAll(proveRecursively(((Conjunction) inside).getLeft(), context, bitmask));
                    if (!rightEst) {
                        result.addAll(proveRecursively(new Negation(((Conjunction) inside).getRight()), context, bitmask));
                        result.addAll(Proofs.getYesNoNotAnd(((Conjunction) inside).getLeft(), ((Conjunction) inside).getRight()));
                    }
                }
                else {
                    result.addAll(proveRecursively(new Negation(((Conjunction) inside).getLeft()), context, bitmask));
                    if (rightEst) {
                        result.addAll(proveRecursively(((Conjunction) inside).getRight(), context, bitmask));
                        result.addAll(Proofs.getNoYesNotAnd(((Conjunction) inside).getLeft(), ((Conjunction) inside).getRight()));
                    }
                    else {
                        result.addAll(proveRecursively(new Negation(((Conjunction) inside).getRight()), context, bitmask));
                        result.addAll(Proofs.getNoNoNotAnd(((Conjunction) inside).getLeft(), ((Conjunction) inside).getRight()));
                    }
                }
            }
            if (inside instanceof Disjunction) {
                boolean leftEst = evaluate(((Disjunction) inside).getLeft(), bitmask);
                boolean rightEst = evaluate(((Disjunction) inside).getRight(), bitmask);
                if (!leftEst && !rightEst) {
                    result.addAll(proveRecursively(new Negation(((Disjunction) inside).getLeft()), context, bitmask));
                    result.addAll(proveRecursively(new Negation(((Disjunction) inside).getRight()), context, bitmask));
                    result.addAll(Proofs.getNoNoNotOr(((Disjunction) inside).getLeft(), ((Disjunction) inside).getRight()));
                }
            }
        }
        if (context.contains(expression)) {
            result.add(expression);
        }
        return result;
    }
    private static String bitmaskToString(int bitmask) {
        return String.format("%32s", Integer.toBinaryString(bitmask)).replace(' ', '0');
    }

}
