package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author GAV
 */
public class ClientReceiver implements Runnable {

    Socket server = null;
    BufferedReader receiver = null;
    ObjectInputStream objectReceiver;

    ClientReceiver(Socket server) throws IOException {
        this.server = server;
        System.out.println("ресеивер стартует 2");
    }

    @Override
    public void run() {
        Object msg = null;
        //while (true) {
            //try {
                //try {
                    //objectReceiver = new ObjectInputStream(server.getInputStream());
                    System.out.println("что-то принялось");
                    //msg = objectReceiver.readObject();
                //} catch (ClassNotFoundException ex) {
                    System.out.println("что-то не то принялось");
                //}

                if (msg != null) {
                    System.out.println((CellState) msg);
                    msg = null;
                }
            //} catch (IOException ex) {
            //    msg = null;
            //    System.out.println(ex + "С приемом сообщения что-то не так");
            //}
        //}
    }
}
