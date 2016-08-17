package expression;

/**
 * Created by baba_beda on 8/17/16.
 */
public class Conjunction extends Expression {
    String type;
    Expression left;
    Expression right;

    public Conjunction(String type, Expression inside) {
        this.type = type;
        this.left = inside;
    }
    public Conjunction(String type, Expression left, Expression right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }


    @Override
    public String toString() {
        String ret = "";
        switch (type) {
            case "neg":
                ret = left.toString();
                break;
            case "conj":
                ret = "(" + left.toString() + "&" + right.toString() + ")";
                break;
        }
        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conjunction that = (Conjunction) o;

        if (!type.equals(that.type)) return false;
        if (!left.equals(that.left)) return false;
        return right != null ? right.equals(that.right) : that.right == null;

    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + left.hashCode();
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}
