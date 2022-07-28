package lobby.network;

import java.io.*;
import java.net.*;

public class ServerOne extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean working = true;
    private GameServer game = null;
    volatile int playerInGameID = 0;
    volatile String message;
    volatile boolean hasMessage = false;

    public ServerOne(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        start();
    }

    @Override
    public void run() {
        try {
            while (working) {
                String str = in.readLine();
                System.out.println("line read: " + str);
                if (str.equals("exit")) {
                    working = false;
                }
                if (game != null && !hasMessage && game.turn == playerInGameID) {
                    message = str;
                    System.out.println("Отправлено: " + message);
                    hasMessage = true;
                } else {
                    out.println("Не твой ход, а ты тут пишешь");
                }
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

    public BufferedReader getIn() {
        return in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setGame(GameServer game) {
        this.game = game;
    }
    synchronized void setMessage(String message) {
        out.println(message);
    }
}
