/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author ksmnote
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
