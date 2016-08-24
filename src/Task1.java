import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import expression.*;
import utils.Annotator;

import parser.*;

/**
 * Created by baba_beda on 8/18/16.
 */
public class Task1 {
    public static void solve() {
        try (Scanner in = new Scanner(new File("task1.in")); PrintWriter out = new PrintWriter(new File("task1.out"))) {
            List<Expression> proof = new ArrayList<>();
            ANTLRInputStream is;
            while (in.hasNext()) {
                String statement = in.next();
                is = new ANTLRInputStream(statement);
                LogicLexer lexer = new LogicLexer(is);
                TokenStream ts = new CommonTokenStream(lexer);
                LogicParser parser = new LogicParser(ts);
                proof.add(parser.expression().value);
            }
            Annotator.getAnnotatedProof(proof, new HashSet<>()).forEach(out::println);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
