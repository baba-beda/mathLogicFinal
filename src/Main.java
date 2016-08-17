import org.antlr.v4.runtime.*;
import parser.LogicLexer;
import parser.LogicParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by baba_beda on 8/17/16.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ANTLRInputStream is = new ANTLRInputStream(new FileInputStream(new File("test.txt")));
        LogicLexer lexer = new LogicLexer(is);
        TokenStream ts = new CommonTokenStream(lexer);
        LogicParser parser = new LogicParser(ts);
        System.out.println(parser.expression().value);
    }
}
