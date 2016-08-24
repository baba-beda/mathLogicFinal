package expression;

/**
 * Created by baba_beda on 8/24/16.
 */
public class Exists extends Expression {
    private Variable var;
    private Expression expr;

    public Exists(Variable var, Expression expr) {
        this.var = var;
        this.expr = expr;
    }

    public Variable getVar() {
        return var;
    }

    public Expression getExpr() {
        return expr;
    }


    @Override
    public String toString() {
        return "?" + var.toString() + expr.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exists exists = (Exists) o;

        if (!var.equals(exists.var)) return false;
        return expr.equals(exists.expr);

    }

    @Override
    public int hashCode() {
        int result = var.hashCode();
        result = 31 * result + expr.hashCode();
        return result;
    }
}

