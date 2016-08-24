package expression;

/**
 * Created by baba_beda on 8/24/16.
 */
public class Forall extends Expression {
    private Variable var;
    private Expression expr;

    public Forall(Variable var, Expression expr) {
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
        return "@" + var.toString() + expr.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Forall forall = (Forall) o;

        if (!var.equals(forall.var)) return false;
        return expr.equals(forall.expr);

    }

    @Override
    public int hashCode() {
        int result = var.hashCode();
        result = 31 * result + expr.hashCode();
        return result;
    }
}
