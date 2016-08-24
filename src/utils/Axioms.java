package utils;

import expression.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import parser.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by baba_beda on 8/18/16.
 */
public class Axioms {
    public static int isAxiom(Expression expression) {
        for (int i = 1; i <= 10; i++) {
            if (isAxiom(i, expression)) {
                return i;
            }
        }

        for (int i = 1; i <= 8; i++) {
            if (isArithmeticAxiom(i, expression)) {
                return i + 10;
            }
        }
        return 0;
    }

    private static boolean isArithmeticAxiom(int num, Expression expression) {
        switch (num) {
            case 1:
                return expression instanceof Implication
                        && ((Implication) expression).getLeft() instanceof Equality
                        && ((Implication) expression).getRight() instanceof Equality
                        && ((Equality) ((Implication) expression).getRight()).getLeft() instanceof Next
                        && ((Equality) ((Implication) expression).getRight()).getRight() instanceof Next
                        && ((Equality) ((Implication) expression).getLeft()).getLeft().equals(((Next) ((Equality) ((Implication) expression).getRight()).getLeft()).getInside())
                        && ((Equality) ((Implication) expression).getLeft()).getRight().equals(((Next) ((Equality) ((Implication) expression).getRight()).getRight()).getInside());
            case 2:
                return expression instanceof Implication
                        && ((Implication) expression).getRight() instanceof Implication
                        && ((Implication) expression).getLeft() instanceof Equality
                        && ((Implication) ((Implication) expression).getRight()).getLeft() instanceof Equality
                        && ((Implication) ((Implication) expression).getRight()).getRight() instanceof Equality
                        && ((Equality) ((Implication) expression).getLeft()).getLeft().equals(((Equality) ((Implication) ((Implication) expression).getRight()).getLeft()).getLeft())
                        && ((Equality) ((Implication) expression).getLeft()).getRight().equals(((Equality) ((Implication) ((Implication) expression).getRight()).getRight()).getLeft())
                        && ((Equality) ((Implication) ((Implication) expression).getRight()).getLeft()).getRight().equals(((Equality) ((Implication) ((Implication) expression).getRight()).getRight()).getRight());
            case 3:
                return expression instanceof Implication
                        && ((Implication) expression).getLeft() instanceof Equality
                        && ((Implication) expression).getRight() instanceof Equality
                        && ((Equality) ((Implication) expression).getLeft()).getLeft() instanceof Next
                        && ((Equality) ((Implication) expression).getLeft()).getRight() instanceof Next
                        && ((Next) ((Equality) ((Implication) expression).getLeft()).getLeft()).getInside().equals(((Equality) ((Implication) expression).getRight()).getLeft())
                        && ((Next) ((Equality) ((Implication) expression).getLeft()).getRight()).getInside().equals(((Equality) ((Implication) expression).getRight()).getRight());
            case 4:
                return expression instanceof Negation
                        && ((Negation) expression).getInside() instanceof Equality
                        && ((Equality) ((Negation) expression).getInside()).getRight() instanceof Zero
                        && ((Equality) ((Negation) expression).getInside()).getLeft() instanceof Next;
            case 5:
                return expression instanceof Equality
                        && ((Equality) expression).getLeft() instanceof Sum
                        && ((Equality) expression).getRight() instanceof Next
                        && ((Sum) ((Equality) expression).getLeft()).getRight() instanceof Next
                        && ((Next) ((Equality) expression).getRight()).getInside() instanceof Sum
                        && ((Sum) ((Equality) expression).getLeft()).getLeft().equals(((Sum) ((Next) ((Equality) expression).getRight()).getInside()).getLeft())
                        && ((Next) ((Sum) ((Equality) expression).getLeft()).getRight()).getInside().equals(((Sum) ((Next) ((Equality) expression).getRight()).getInside()).getRight());
            case 6:
                return expression instanceof Equality
                        && ((Equality) expression).getLeft() instanceof Sum
                        && ((Sum) ((Equality) expression).getLeft()).getLeft().equals(((Equality) expression).getRight())
                        && ((Sum) ((Equality) expression).getLeft()).getRight() instanceof Zero;
            case 7:
                return expression instanceof Equality
                        && ((Equality) expression).getLeft() instanceof Multiplicity
                        && ((Multiplicity) ((Equality) expression).getLeft()).getRight() instanceof Zero
                        && ((Multiplicity) ((Equality) expression).getLeft()).getLeft().equals(((Equality) expression).getRight());
            case 8:
                return expression instanceof Equality
                        && ((Equality) expression).getLeft() instanceof Multiplicity
                        && ((Equality) expression).getRight() instanceof Sum
                        && ((Multiplicity) ((Equality) expression).getLeft()).getRight() instanceof Next
                        && ((Sum) ((Equality) expression).getRight()).getLeft() instanceof Multiplicity
                        && ((Multiplicity) ((Equality) expression).getLeft()).getLeft().equals(((Multiplicity) ((Sum) ((Equality) expression).getRight()).getLeft()).getLeft())
                        && ((Multiplicity) ((Equality) expression).getLeft()).getLeft().equals(((Sum) ((Equality) expression).getRight()).getRight())
                        && ((Next) ((Multiplicity) ((Equality) expression).getLeft()).getRight()).getInside().equals(((Multiplicity) ((Sum) ((Equality) expression).getRight()).getLeft()).getRight());
        }
        return false;
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
    
    private static Expression[] axioms = new Expression[10];
    private static Expression[] arithmeticAxioms = new Expression[8];
    
    public static void parseAxioms() {
        try (Scanner in = new Scanner(new File("rsc" + File.separator + "axioms"))) {
            ANTLRInputStream is;
            for (int i = 0; i < 10; i++) {
                is = new ANTLRInputStream(in.next());
                LogicLexer lexer = new LogicLexer(is);
                TokenStream ts = new CommonTokenStream(lexer);
                LogicParser parser = new LogicParser(ts);
                axioms[i] = parser.expression().value;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    static Expression getAxiom1(Expression alpha, Expression beta) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("A", alpha);
        variables.put("B", beta);
        return Substituter.substitute(axioms[0], variables);
    }
    static Expression getAxiom2(Expression alpha, Expression beta, Expression gamma) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("A", alpha);
        variables.put("B", beta);
        variables.put("C", gamma);
        return Substituter.substitute(axioms[1], variables);
    }
    static Expression getAxiom3(Expression alpha, Expression beta) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("A", alpha);
        variables.put("B", beta);
        return Substituter.substitute(axioms[2], variables);
    }
    static Expression getAxiom4(Expression alpha, Expression beta) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("A", alpha);
        variables.put("B", beta);
        return Substituter.substitute(axioms[3], variables);
    }
    static Expression getAxiom5(Expression alpha, Expression beta) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("A", alpha);
        variables.put("B", beta);
        return Substituter.substitute(axioms[4], variables);
    }
    static Expression getAxiom6(Expression alpha, Expression beta) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("A", alpha);
        variables.put("B", beta);
        return Substituter.substitute(axioms[5], variables);
    }
    static Expression getAxiom7(Expression alpha, Expression beta) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("A", alpha);
        variables.put("B", beta);
        return Substituter.substitute(axioms[6], variables);
    }
    static Expression getAxiom8(Expression alpha, Expression beta, Expression gamma) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("A", alpha);
        variables.put("B", beta);
        variables.put("C", gamma);
        return Substituter.substitute(axioms[7], variables);
    }
    static Expression getAxiom9(Expression alpha, Expression beta) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("A", alpha);
        variables.put("B", beta);
        return Substituter.substitute(axioms[8], variables);
    }
    static Expression getAxiom10(Expression alpha) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("A", alpha);
        return Substituter.substitute(axioms[9], variables);
    }

    static Expression getArithmeticAxiom1(Expression a, Expression b) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("a", a);
        variables.put("b", b);
        return Substituter.substitute(arithmeticAxioms[0], variables);
    }

    static Expression getArithmeticAxiom2(Expression a, Expression b, Expression c) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("a", a);
        variables.put("b", b);
        variables.put("c", c);
        return Substituter.substitute(arithmeticAxioms[1], variables);
    }
    static Expression getArithmeticAxiom3(Expression a, Expression b) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("a", a);
        variables.put("b", b);
        return Substituter.substitute(arithmeticAxioms[2], variables);
    }
    static Expression getArithmeticAxiom4(Expression a) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("a", a);
        return Substituter.substitute(arithmeticAxioms[3], variables);
    }
    static Expression getArithmeticAxiom5(Expression a, Expression b) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("a", a);
        variables.put("b", b);
        return Substituter.substitute(arithmeticAxioms[4], variables);
    }
    static Expression getArithmeticAxiom6(Expression a) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("a", a);
        return Substituter.substitute(arithmeticAxioms[5], variables);
    }
    static Expression getArithmeticAxiom7(Expression a) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("a", a);
        return Substituter.substitute(arithmeticAxioms[6], variables);
    }
    static Expression getArithmeticAxiom8(Expression a, Expression b) {
        Map<String, Expression> variables = new HashMap<>();
        variables.put("a", a);
        variables.put("b", b);
        return Substituter.substitute(arithmeticAxioms[7], variables);
    }
}
