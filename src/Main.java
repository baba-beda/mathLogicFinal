
import utils.*;

import java.io.IOException;

/**
 * Created by baba_beda on 8/17/16.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Axioms.parseAxioms();
        Proofs.parseProofs();
        for (String arg : args) {
            if (arg.equals("1")) {
                System.out.println("Solving Task1");
                Task1.solve();
            }
            if (arg.equals("2")) {
                System.out.println("Solving Task2");
                Task2.solve();
            }
            if (arg.equals("3")) {
                System.out.println("Solving Task3");
                Task3.solve();
            }
            if (arg.equals("4")) {
                System.out.println("Solving Task4");
                Task4.solve();
            }
        }
    }
}
