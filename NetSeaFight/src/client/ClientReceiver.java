package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import lobby.network.Message;

/**
 *
 * @author GAV
 */
public class ClientReceiver implements Runnable {

    Socket server = null;
    //BufferedReader receiver = null;
    ObjectInputStream objectReceiver;

    ClientReceiver(Socket server) {
        this.server = server;
        System.out.println("receiver стартует 1");
    }

    @Override
    public void run() {
        Message msg = null;
        try {
            objectReceiver = new ObjectInputStream(server.getInputStream());
        } catch (IOException ex) {
            System.out.println(objectReceiver + " objectReceiver не стартует");
        }
        while (true) {
            try {
                try {
                    msg = (Message)objectReceiver.readObject();
                    
                } catch (ClassNotFoundException ex) {
                    System.out.println("что-то не то принялось");
                }

                if (msg.getType().equals("String")) {
                    System.out.println((String)msg.getData()); //это потом превратится в messageProcess
                }
                msg = null;
            } catch (IOException ex) {
                msg = null;
                System.out.println(ex + "С приемом сообщения что-то не так");
            }
        }
    }
}