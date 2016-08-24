package expression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baba_beda on 8/24/16.
 */
public class Function extends Expression {
    private String name;
    private List<Expression> terms;

    public Function(String name) {
        this.name = name;
        terms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addTerm(Expression term) {
        terms.add(term);
    }

    public int getLength() {
        return terms.size();
    }

    public Expression getTerm(int i) {
        return terms.get(i);
    }

    @Override
    public String toString() {
        String res = name + "(" + terms.get(0);
        for (int i = 1; i < terms.size(); i++) {
            res += "," + terms.get(i);
        }
        return res + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Function function = (Function) o;

        if (!name.equals(function.name)) return false;
        return terms.equals(function.terms);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + terms.hashCode();
        return result;
    }
}
