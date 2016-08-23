package utils;
import expression.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import parser.LogicLexer;
import parser.LogicParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by baba_beda on 8/18/16.
 */
public class Proofs {
    // I don't know better way to hardcode these template proofs
    // so I just parse them from corresponding files and substitute variables with custom expressions
    private static List<Expression> addDoubleNot;
    private static List<Expression> contraposition;
    private static List<Expression> selfCons;
    private static List<Expression> exclusion;
    private static List<Expression> prependAnd;
    private static List<Expression> removeAnd;
    private static List<Expression> rotateImpl;
    private static List<Expression> tertiumNonDatum;
    private static List<Expression> noNoImpl;
    private static List<Expression> noYesImpl;
    private static List<Expression> yesNoNotImpl;
    private static List<Expression> yesYesImpl;
    private static List<Expression> noNoNotAnd;
    private static List<Expression> noYesNotAnd;
    private static List<Expression> yesNoNotAnd;
    private static List<Expression> yesYesAnd;
    private static List<Expression> noNoNotOr;
    private static List<Expression> noYesOr;
    private static List<Expression> yesNoOr;
    private static List<Expression> yesYesOr;

    public static void parseProofs() {
        addDoubleNot = parseFile("AddDoubleNot");
        contraposition = parseFile("Contraposition");
        selfCons = parseFile("SelfCons");
        exclusion = parseFile("Exclusion");
        prependAnd = parseFile("PrependAnd");
        removeAnd = parseFile("RemoveAnd");
        rotateImpl = parseFile("RotateImpl");
        tertiumNonDatum = parseFile("TertiumNonDatum");
        noNoImpl = parseFile("NoNoImpl");
        noYesImpl = parseFile("NoYesImpl");
        yesNoNotImpl = parseFile("YesNoNotImpl");
        yesYesImpl = parseFile("YesYesImpl");
        noNoNotAnd = parseFile("NoNoNotAnd");
        noYesNotAnd = parseFile("NoYesNotAnd");
        yesNoNotAnd = parseFile("YesNoNotAnd");
        yesYesAnd = parseFile("YesYesAnd");
        noNoNotOr = parseFile("NoNoNotOr");
        noYesOr = parseFile("NoYesOr");
        yesNoOr = parseFile("YesNoOr");
        yesYesOr = parseFile("YesYesOr");
    }
    private static List<Expression> parseFile(String fileName) {
        List<Expression> list = new ArrayList<>();
        try (Scanner in = new Scanner(new File("rsc/proofs/" + fileName + ".txt"))) {
            ANTLRInputStream is;
            while (in.hasNext()) {
                is = new ANTLRInputStream(in.next());
                LogicLexer lexer = new LogicLexer(is);
                TokenStream ts = new CommonTokenStream(lexer);
                LogicParser parser = new LogicParser(ts);
                list.add(parser.expression().value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Expression> getAddDoubleNot(Expression a) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        result.addAll(addDoubleNot.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getContraposition(Expression a, Expression b) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        result.addAll(contraposition.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getSelfCons(Expression a) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        result.addAll(selfCons.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getExclusion(Expression a, Expression b) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        result.addAll(exclusion.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getPrependAnd(Expression a, Expression b, Expression c) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        variables.put("C", c);
        result.addAll(prependAnd.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getRemoveAnd(Expression a, Expression b, Expression c) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        variables.put("C", c);
        result.addAll(removeAnd.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getRotateImpl(Expression a, Expression b, Expression c) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        variables.put("C", c);
        result.addAll(rotateImpl.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getTertiumNonDatum(Expression a) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        result.addAll(tertiumNonDatum.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getNoNoImpl(Expression a, Expression b) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        result.addAll(noNoImpl.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getNoYesImpl(Expression a, Expression b) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        result.addAll(noYesImpl.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getYesNoNotImpl(Expression a, Expression b) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        result.addAll(yesNoNotImpl.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getYesYesImpl(Expression a, Expression b) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        result.addAll(yesYesImpl.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getNoNoNotAnd(Expression a, Expression b) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        result.addAll(noNoNotAnd.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getNoYesNotAnd(Expression a, Expression b) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        result.addAll(noYesNotAnd.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getYesNoNotAnd(Expression a, Expression b) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        result.addAll(yesNoNotAnd.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getYesYesAnd(Expression a, Expression b) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        result.addAll(yesYesAnd.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getNoNoNotOr(Expression a, Expression b) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        result.addAll(noNoNotOr.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getNoYesOr(Expression a, Expression b) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        result.addAll(noYesOr.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getYesNoOr(Expression a, Expression b) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        result.addAll(yesNoOr.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }

    public static List<Expression> getYesYesOr(Expression a, Expression b) {
        List<Expression> result = new ArrayList<>();
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", a);
        variables.put("B", b);
        result.addAll(yesYesOr.stream().map(e -> Substituter.substitute(e, variables)).collect(Collectors.toList()));
        return result;
    }
}

