import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by baba_beda on 8/24/16.
 */
public class Task8 {
    static void solve() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec(new String[]{"/bin/sh", "-c", "~/mathLogicFinal/task8/test.sh"});
            InputStream is = process.getInputStream();
            Scanner in = new Scanner(is);
            while (in.hasNext()) {
                System.out.println(in.next());
            }
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
