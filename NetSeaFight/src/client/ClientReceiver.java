package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author GAV
 */
public class ClientReceiver implements Runnable {

    Socket server = null;
    BufferedReader receiver = null;

    ClientReceiver(Socket server) throws IOException {
        this.server = server;
        receiver = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    @Override
    public void run() {
        String msg = null;
        while (true) {
            try {
                msg = receiver.readLine();
                if (msg != null) {
                    System.out.println(msg);
                    msg = null;
                }

            } catch (IOException ex) {
                System.out.println("С приемом сообщения что-то не так");
            }
        }
    }
}
