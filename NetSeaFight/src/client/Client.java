package client;

import client.GUI.MainWindow;
import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {
        MainWindow mainWindow = new MainWindow();
        mainWindow.createGUI();
        
        
        
        System.out.println("Клиент стартовал");
        String address = "localhost";
        int port = 6666;
        Socket server = new Socket(address, port);

        ClientReceiver receiver = new ClientReceiver(server);
        Thread clientReceiver = new Thread(receiver);
        clientReceiver.start();

        System.out.println("Соединяемся с сервером " + address + ":" + port);

        PrintWriter sender = new PrintWriter(server.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String fuser;

        while (true) {
            fuser = input.readLine();
            if (fuser != null) {
                sender.println(fuser);
                if (fuser.equalsIgnoreCase("exit")) {
                    break;
                }
            }//без попытки отправки сообщения мы не можем получить то, что пришло. надо делать 2 потока
        }
        sender.close();
        input.close();
        server.close();
    }
}
