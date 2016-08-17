package expression;

/**
 * Created by baba_beda on 8/17/16.
 */
public class Negation extends Expression {
    String type;
    Expression inside;

    public Negation(String type, Expression inside) {
        this.type = type;
        this.inside = inside;
    }

    @Override
    public String toString() {
        String ret = "";
        switch (type) {
            case "var":
                ret = inside.toString();
                break;
            case "neg":
                ret = "!" + "(" + inside.toString() + ")";
                break;
            case "expr":
                ret = inside.toString();
                break;
        }
        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Negation negation = (Negation) o;

        if (!type.equals(negation.type)) return false;
        return inside.equals(negation.inside);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + inside.hashCode();
        return result;
    }
}
