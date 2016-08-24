package expression;

/**
 * Created by baba_beda on 8/24/16.
 */
public class Next extends Expression {
    private Expression inside;

    public Next(Expression inside) {
        this.inside = inside;
    }

    public Expression getInside() {
        return inside;
    }

    @Override
    public String toString() {
        return "(" + inside.toString() + ")" + "\'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Next next = (Next) o;

        return inside.equals(next.inside);

    }

    @Override
    public int hashCode() {
        return inside.hashCode();
    }
}
