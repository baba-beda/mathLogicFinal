package utils;

import com.sun.istack.internal.Pool;
import expression.*;

/**
 * Created by baba_beda on 8/18/16.
 */
public class Axioms {
    public static int isAxiom(Expression expression) {
        for (int i = 1; i <= 10; i++) {
            if (isAxiom(i, expression)) {
                return i;
            };
        }
        return 0;
    }

    private static boolean isAxiom(int num, Expression expression) {
        switch (num) {
            case 1:
                return expression instanceof Implication
                        && ((Implication) expression).getRight() instanceof Implication
                        &&  ((Implication) expression).getLeft().equals(((Implication) ((Implication) expression).getRight()).getRight());
            case 2:
                return expression instanceof Implication
                        && ((Implication) expression).getLeft() instanceof Implication
                        && ((Implication) expression).getRight() instanceof Implication
                        && ((Implication) ((Implication) expression).getRight()).getLeft() instanceof Implication
                        && ((Implication) ((Implication) ((Implication) expression).getRight()).getLeft()).getRight() instanceof Implication
                        && ((Implication) ((Implication) expression).getRight()).getRight() instanceof Implication
                        && ((Implication) ((Implication) expression).getLeft()).getLeft().equals(((Implication) ((Implication) ((Implication) expression).getRight()).getLeft()).getLeft())
                        && ((Implication) ((Implication) expression).getLeft()).getLeft().equals(((Implication) ((Implication) ((Implication) expression).getRight()).getRight()).getLeft())
                        && ((Implication) ((Implication) expression).getLeft()).getRight().equals(((Implication) ((Implication) ((Implication) ((Implication) expression).getRight()).getLeft()).getRight()).getLeft())
                        && ((Implication) ((Implication) ((Implication) ((Implication) expression).getRight()).getLeft()).getRight()).getRight().equals(((Implication) ((Implication) ((Implication) expression).getRight()).getRight()).getRight());
            case 3:
                return expression instanceof Implication
                        && ((Implication) expression).getLeft() instanceof Conjunction
                        && ((Conjunction) ((Implication) expression).getLeft()).getLeft().equals(((Implication) expression).getRight());
            case 4:
                return expression instanceof Implication
                        && ((Implication) expression).getLeft() instanceof Conjunction
                        && ((Conjunction) ((Implication) expression).getLeft()).getRight().equals(((Implication) expression).getRight());
            case 5:
                return expression instanceof Implication
                        && ((Implication) expression).getRight() instanceof Implication
                        && ((Implication) ((Implication) expression).getRight()).getRight() instanceof Conjunction
                        && ((Implication) expression).getLeft().equals(((Conjunction) ((Implication) ((Implication) expression).getRight()).getRight()).getLeft())
                        && ((Implication) ((Implication) expression).getRight()).getLeft().equals(((Conjunction) ((Implication) ((Implication) expression).getRight()).getRight()).getRight());
            case 6:
                return expression instanceof Implication
                        && ((Implication) expression).getRight() instanceof Disjunction
                        && ((Implication) expression).getLeft().equals(((Disjunction) ((Implication) expression).getRight()).getLeft());
            case 7:
                return expression instanceof Implication
                        && ((Implication) expression).getRight() instanceof Disjunction
                        && ((Implication) expression).getLeft().equals(((Disjunction) ((Implication) expression).getRight()).getRight());
            case 8:
                return expression instanceof Implication
                        && ((Implication) expression).getLeft() instanceof Implication
                        && ((Implication) expression).getRight() instanceof Implication
                        && ((Implication) ((Implication) expression).getRight()).getLeft() instanceof Implication
                        && ((Implication) ((Implication) expression).getRight()).getRight() instanceof Implication
                        && ((Implication) ((Implication) ((Implication) expression).getRight()).getRight()).getLeft() instanceof Disjunction
                        && ((Implication) ((Implication) expression).getLeft()).getLeft().equals(((Disjunction) ((Implication) ((Implication) ((Implication) expression).getRight()).getRight()).getLeft()).getLeft())
                        && ((Implication) ((Implication) expression).getLeft()).getRight().equals(((Implication) ((Implication) ((Implication) expression).getRight()).getLeft()).getRight())
                        && ((Implication) ((Implication) expression).getLeft()).getRight().equals(((Implication) ((Implication) ((Implication) expression).getRight()).getRight()).getRight())
                        && ((Implication) ((Implication) ((Implication) expression).getRight()).getLeft()).getLeft().equals(((Disjunction) ((Implication) ((Implication) ((Implication) expression).getRight()).getRight()).getLeft()).getRight());

            case 9:
                return expression instanceof Implication
                        && ((Implication) expression).getLeft() instanceof Implication
                        && ((Implication) expression).getRight() instanceof Implication
                        && ((Implication) ((Implication) expression).getRight()).getLeft() instanceof Implication
                        && ((Implication) ((Implication) expression).getRight()).getRight() instanceof Negation
                        && ((Implication) ((Implication) ((Implication) expression).getRight()).getLeft()).getRight() instanceof Negation
                        && ((Implication) ((Implication) expression).getLeft()).getLeft().equals(((Implication) ((Implication) ((Implication) expression).getRight()).getLeft()).getLeft())
                        && ((Implication) ((Implication) expression).getLeft()).getLeft().equals(((Negation) ((Implication) ((Implication) expression).getRight()).getRight()).getInside())
                        && ((Implication) ((Implication) expression).getLeft()).getRight().equals(((Negation) ((Implication) ((Implication) ((Implication) expression).getRight()).getLeft()).getRight()).getInside());
            case 10:
                return expression instanceof Implication
                        && ((Implication) expression).getLeft() instanceof Negation
                        && ((Negation) ((Implication) expression).getLeft()).getInside() instanceof Negation
                        && ((Implication) expression).getRight().equals(((Negation) ((Negation) ((Implication) expression).getLeft()).getInside()).getInside());
        }

        return false;
    }
}
