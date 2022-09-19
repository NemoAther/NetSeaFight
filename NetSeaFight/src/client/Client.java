package client;

import client.GUI.MainWindow;
import java.io.*;
import java.net.*;

/**
 *
 * @author GAV
 */
public class Client {

    MainWindow mainWindow;

    String address = "localhost";
    int port = 6666;
    Socket server;

    ClientReceiver receiver;
    Thread clientReceiver;

    PrintWriter sender;
    ObjectOutputStream objectSender;
    BufferedReader input;

    String fuser;

    public Client() throws IOException {
        System.out.println("Клиент стартует 1");
        address = "localhost";
        port = 6666;
        server = new Socket(address, port);
        objectSender = new ObjectOutputStream(server.getOutputStream());
        System.out.println("Клиент стартует 2");
        receiver = new ClientReceiver(server);
        clientReceiver = new Thread(receiver);
        clientReceiver.start();
        System.out.println("Клиент стартует 3");
        System.out.println("Соединяемся с сервером " + address + ":" + port);

        sender = new PrintWriter(server.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(System.in));

        String fuser;

        mainWindow = new MainWindow();
        mainWindow.createGUI(this);
        while (true) {
            fuser = input.readLine();
            if (fuser != null) {
                sender.println(fuser);
                if (fuser.equalsIgnoreCase("exit")) {
                    break;
                }
            }
        }
        sender.close();
        input.close();
        server.close();
    }

    public void sendMessage(String msg) {

        try {
            objectSender.writeObject(CellState.EMPTY);
            System.out.println("записался объект");
            objectSender.flush();
            System.out.println("сделали флаш");

        } catch (IOException ex) {
            System.out.println(ex + " не записался объект");
        }
    }
}
