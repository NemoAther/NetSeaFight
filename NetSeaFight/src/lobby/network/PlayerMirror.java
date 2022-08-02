package lobby.network;

import gameserver.GameServer;
import java.io.*;
import java.net.*;

public class PlayerMirror implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean working = true;
    private GameServer game = null;
    volatile int playerInGameID = 0;
    //volatile String message;
    //volatile boolean hasMessage = false;

    public PlayerMirror(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        Thread player = new Thread(this);
        player.start();
    }

    @Override
    public void run() {
        try {
            while (working) {
                String msg = in.readLine();
                msg = processingMessage(msg);
                

                /*System.out.println("line read: " + str);
                if (str.equals("exit")) {
                    working = false;
                }
                if (game != null && !hasMessage && game.turn == playerInGameID) {
                    message = str;
                    System.out.println("Отправлено: " + message);
                    hasMessage = true;
                } else {
                    out.println("Не твой ход, а ты тут пишешь");
                }*/
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

    String processingMessage(String rawMessage) {

        String[] message = rawMessage.split(";"); //добавить ограничитель, чтобы делил только по первому вхождению
        String result = null;
        switch (message[0]) {
            case "shoot":
                //synchronized (this) {
                result = game.shoot(message[1], playerInGameID);
                //}
                break; //выстрел: координаты
            case "msg":
                //synchronized (this) {
                result = game.chatMessage(message[1], playerInGameID);
                //}
                break; //просто сообщение - переслать в чат
            case "serv":
                //synchronized (this) {
                result = game.serviceMessage(message[1], playerInGameID);
                //}
                break; //сервисноее сообщение - начало игры, конец игры, дисконнект, передача игрового поля итд
            default: result = game.chatMessage(message[0], playerInGameID); result = "некорректное сообщение";
        }
        return result;
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

    public void setMessage(String message) {
        out.println(message);
        out.flush();
    }

    public void setPlayerInGameID(int playerInGameID) {
        this.playerInGameID = playerInGameID;
    }

    public int getPlayerInGameID() {
        return playerInGameID;
    }
    
}
