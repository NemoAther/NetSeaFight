package lobby.network;

import java.io.*;
import java.net.*;

public class ServerOne extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServerOne(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String str = in.readLine();
                if (str.equals("exit")) {
                    break;
                }
                System.out.println("Получено: " + str);
                out.println(str);
            }
            System.out.println("Соединение закрыто");
        } catch (IOException e) {
            System.err.println("Ошибка чтения/записи");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Сокет не закрыт");
            }
        }
    }
}
