import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        String userName = "Default";
        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));

        try {
            Socket sc = new Socket("192.168.0.63", 8888);

            PrintWriter pw = new PrintWriter(sc.getOutputStream(), true);
            BufferedReader brs = new BufferedReader(new InputStreamReader(sc.getInputStream()));

            pw.println(userName);

            Reader reader = new Reader(brs);
            reader.start();

            do {
                String message = brc.readLine();
                pw.println(message);
            } while (reader.isAlive());
            System.out.println("Lost connection to server!");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
