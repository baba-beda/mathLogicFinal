package expression;

import com.sun.istack.internal.Pool;
import com.sun.org.apache.xpath.internal.operations.Neg;
import utils.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by baba_beda on 8/17/16.
 */
public class Expression {
    private Set<String> variables = new HashSet<>();
    private Map<String, Integer> realVariables;

    private Set<String> getVariables() {
        if (this instanceof Implication) {
            variables.addAll(((Implication) this).getLeft().getVariables());
            variables.addAll(((Implication) this).getRight().getVariables());
        }
        else if (this instanceof Disjunction) {
            variables.addAll(((Disjunction) this).getLeft().getVariables());
            variables.addAll(((Disjunction) this).getRight().getVariables());
        }
        else if (this instanceof Conjunction) {
            variables.addAll(((Conjunction) this).getLeft().getVariables());
            variables.addAll(((Conjunction) this).getRight().getVariables());
        }
        else if (this instanceof Negation) {
            variables.addAll(((Negation) this).getInside().getVariables());
        }
        else if (this instanceof Variable) {
            variables.add(((Variable) this).getValue());
        }
        return variables;
    }


    public Map<String, Integer> getRealVariables() {
        if (realVariables == null) {
            getVariables();
            realVariables = new HashMap<>();
            for (String s : variables) {
                realVariables.put(s, realVariables.size());
            }
            this.setRealVariables(realVariables);
        }
        return realVariables;
    }

    private void setRealVariables(Map<String, Integer> realVariables) {
        this.realVariables = realVariables;
        if (this instanceof Implication) {
            ((Implication) this).getLeft().setRealVariables(this.realVariables);
            ((Implication) this).getRight().setRealVariables(this.realVariables);
        }
        if (this instanceof Disjunction) {
            ((Disjunction) this).getLeft().setRealVariables(this.realVariables);
            ((Disjunction) this).getRight().setRealVariables(this.realVariables);
        }
        if (this instanceof Conjunction) {
            ((Conjunction) this).getLeft().setRealVariables(this.realVariables);
            ((Conjunction) this).getRight().setRealVariables(this.realVariables);
        }
        if (this instanceof Negation) {
            ((Negation) this).getInside().setRealVariables(this.realVariables);
        }
    }
}
