package lobby.network;

import client.CellState;
import gameserver.GameServer;
import java.io.*;
import java.net.*;

/**
 *
 * @author GAV
 */
public class PlayerMirror implements Runnable {

    private Socket socket;
    private boolean working = true;
    private GameServer game = null;
    ObjectInputStream objectReceiver;
    ObjectOutputStream objectSender;
    volatile int playerInGameID = 0;

    public PlayerMirror(Socket s) throws IOException {
        socket = s;
        objectReceiver = new ObjectInputStream(socket.getInputStream());
        objectSender = new ObjectOutputStream(socket.getOutputStream());
        Thread player = new Thread(this);
        player.start();
    }

    @Override
    public void run() {
        try {

            while (working) {
                try {
                    Object obj = objectReceiver.readObject();
                    System.out.println(obj);
                    //processingMessage((Message) objectReceiver.readObject());
                    processingMessage((Message) obj);

                } catch (ClassNotFoundException ex) {
                    System.out.println(ex + " что-то не то принялось");
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

    void processingMessage(Message message) {
        switch (message.getType()) {
            case "String":
                String msg = (String) message.getData();
                game.chatMessage(msg, playerInGameID);
                System.out.println("отправили меседж из миррора");
                break;
            case "field":
                CellState[][] field = (CellState[][]) message.getData();
                System.out.println("получено в мироре");
                for (int i = 0; i < field.length; i++) {
                    for (int j = 0; j < field[0].length; j++) {
                        System.out.print(field[j][i] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
                break;
            default:
                break;
        }
    }

    public void setGame(GameServer game) {
        this.game = game;
    }

    public void setMessage(String message) {
        try {
            objectSender.writeObject(new Message("String", message));
            objectSender.flush();
        } catch (IOException ex) {
            System.out.println(ex + " objectSender не справился");
        }
    }

    public void setPlayerInGameID(int playerInGameID) {
        this.playerInGameID = playerInGameID;
    }

    public int getPlayerInGameID() {
        return playerInGameID;
    }

}
