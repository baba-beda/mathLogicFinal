package utils;

/**
 * Created by baba_beda on 8/18/16.
 */
public class Pair {
    private int fst, snd;
    public Pair(int fst, int snd) {
        this.fst = fst;
        this.snd = snd;
    }

    public int getFst() {
        return fst;
    }

    public int getSnd() {
        return snd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        return fst == pair.fst && snd == pair.snd;

    }

    @Override
    public int hashCode() {
        int result = fst;
        result = 31 * result + snd;
        return result;
    }
}
