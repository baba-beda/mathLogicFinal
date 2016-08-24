import expression.Expression;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import parser.LogicLexer;
import parser.LogicParser;
import utils.Annotator;
import utils.Prover;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * Created by baba_beda on 8/23/16.
 */
class Task3 {
    static void solve() {
        try (Scanner in = new Scanner(new FileInputStream(new File("task3.in")));
             PrintWriter out = new PrintWriter(new File("task3.out"))) {
            String toProve = in.next();
            ANTLRInputStream is = new ANTLRInputStream(toProve);
            LogicLexer lexer = new LogicLexer(is);
            TokenStream ts = new CommonTokenStream(lexer);
            LogicParser parser = new LogicParser(ts);
            Expression expression = parser.expression().value;

            List<Expression> proof = Prover.prove(expression);
            if (proof != null) {
                Annotator.getAnnotatedProof(proof, new HashSet<>()).forEach(out::println);
            }
            out.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
