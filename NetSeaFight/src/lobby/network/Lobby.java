package lobby.network;

import gameserver.GameServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Lobby implements Runnable {

    private static final int PORT = 6666;
    private ArrayList<PlayerMirror> serverOnes = new ArrayList<>();
    private ArrayList<GameServer> games = new ArrayList<>();

    private void startLobby() throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Старт лобби");
        try {
            while (true) {
                Socket socket = s.accept();
                PlayerMirror serverOne;
                try {
                    System.out.println("Попытка входа в лобби");
                    serverOne = new PlayerMirror(socket);
                    serverOnes.add(serverOne);
                    System.out.println("Новое соединение установлено");

                    boolean freePlayer = true;
                    int counter = games.size(); //если убрать, то игры будут создаваться бесконечно. т.к. размер массива каждый раз все растет
                    for (int i = 0; i < counter; i++) {
                        if (!games.get(i).isStarted() && games.get(i).getPlayersCount() == 1 && freePlayer) {
                            games.get(i).addPlayer(serverOne);
                            freePlayer = false;
                            Thread gameStarted = new Thread(games.get(i));
                            gameStarted.start();
                        }
                    }
                    if (freePlayer) {
                        games.add(new GameServer(serverOne));
                        freePlayer = false;
                    }

                    for (int i = 0; i < games.size(); i++) {
                        System.out.println("Игр сейчас " + games.size());
                        System.out.println("Эта игра начата? " + games.get(i).isStarted());
                        System.out.println("Игроков в этой игре: " + games.get(i).getPlayersCount());
                    }

                } catch (IOException e) {
                    System.out.println("Вход в лобби неудался.");
                    socket.close();
                }

            }
        } finally {
            System.out.println("Лобби завершается");
            s.close();
        }
    }

    @Override
    public void run() {
        try {
            startLobby();
        } catch (IOException ex) {
            System.out.println("Лобби не стартовало");
        }
    }
}
