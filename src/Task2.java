import expression.Expression;
import expression.Implication;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import parser.LogicLexer;
import parser.LogicParser;
import utils.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by baba_beda on 8/18/16.
 */
public class Task2 {
    public static void solve() {
        try (Scanner in = new Scanner(new File("task2.in")); PrintWriter out = new PrintWriter(new File("task2.out"))) {

            List<Expression> proof = new ArrayList<>();
            String fstLineString = in.next();
            String[] firstLine = fstLineString.split("\\|-");
            String[] allAssumptions = firstLine[0].split(",");
            Set<Expression> assumptions = new HashSet<>();

            ANTLRInputStream is;
            is = new ANTLRInputStream(allAssumptions[allAssumptions.length - 1]);
            LogicLexer lexer = new LogicLexer(is);
            TokenStream ts = new CommonTokenStream(lexer);
            LogicParser parser = new LogicParser(ts);
            Expression alpha = parser.expression().value;

            for (int i = 0; i < allAssumptions.length - 1; i++) {
                is = new ANTLRInputStream(allAssumptions[i]);
                lexer = new LogicLexer(is);
                ts = new CommonTokenStream(lexer);
                parser = new LogicParser(ts);
                assumptions.add(parser.expression().value);
                out.print(allAssumptions[i]);
                if (i != allAssumptions.length - 2) {
                    out.print(",");
                }
            }



            while (in.hasNext()) {
                is = new ANTLRInputStream(in.next());
                lexer = new LogicLexer(is);
                ts = new CommonTokenStream(lexer);
                parser = new LogicParser(ts);
                proof.add(parser.expression().value);
            }

            out.println("|-" + (new Implication(alpha, proof.get(proof.size() - 1))));
            Annotator.getAnnotatedProof(Deductor.completeProof(assumptions, alpha, proof), assumptions).forEach(out::println);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
