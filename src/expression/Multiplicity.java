package expression;

/**
 * Created by baba_beda on 8/24/16.
 */
public class Multiplicity extends Expression {
    private Expression left, right;

    public Multiplicity(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "*" + right.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Multiplicity that = (Multiplicity) o;

        if (!left.equals(that.left)) return false;
        return right.equals(that.right);

    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }
}
