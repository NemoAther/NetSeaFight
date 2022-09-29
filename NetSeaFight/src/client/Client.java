package client;

import client.GUI.MainWindow;
import java.io.*;
import java.net.*;
import lobby.network.Message;

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

    ObjectOutputStream objectSender;
    BufferedReader input;
    boolean gameProcess = true;

    public Client() {
        System.out.println("Клиент стартует 0");
        address = "localhost";
        port = 6666;
        try {
            server = new Socket(address, port);
        } catch (IOException ex) {
            System.out.println(ex + " сокет не открылся");
        }
        System.out.println("Клиент стартует 1");
        receiver = new ClientReceiver(server);
        System.out.println("Клиент стартует 11");
        try {
            objectSender = new ObjectOutputStream(server.getOutputStream());
        } catch (IOException ex) {
            System.out.println(ex + " objectSender не открылся");
        }
        clientReceiver = new Thread(receiver);
        clientReceiver.start();
        System.out.println("Соединяемся с сервером " + address + ":" + port);
        input = new BufferedReader(new InputStreamReader(System.in));
        mainWindow = new MainWindow();
        mainWindow.createGUI(this);
        while (gameProcess) {
            try {
                String message = input.readLine();
                if (message != null) {
                    try {
                        objectSender.writeObject(new Message("String", message));
                        objectSender.flush();
                    } catch (IOException ex) {
                        System.out.println(ex + " не записался объект");
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex + " message не прочитался с клавиатуры");
            }
        }

        try {
            input.close();
            server.close();
        } catch (IOException ex) {
            System.out.println(ex + " сокет не закрылся");
        }
    }

    public void sendMessage(String msg) {
        try {
            System.out.println("отправлено на сервер string");
            objectSender.writeObject(new Message("String", msg));
            objectSender.flush();
        } catch (IOException ex) {
            System.out.println(ex + " не записался объект");
        }
    }

    public void sendMessage(CellState[][] msg) {
        try {
            
            objectSender.writeObject(new Message("field", msg));
            objectSender.flush();
        } catch (IOException ex) {
            System.out.println(ex + " не записался объект");
        }
    }
}
