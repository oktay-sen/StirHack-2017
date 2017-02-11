import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader extends Thread {

    BufferedReader brs;

    public Reader(BufferedReader br) {
        this.brs = br;
    }

    @Override
    public void run() {
        String message = "";
        do {
            try {
                message = brs.readLine();
            } catch (IOException ex) {
                break;
            }
            System.out.println(message);
        } while (true);
    }
}
