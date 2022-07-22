package lobby.network;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 *
 * @author ksmnote
 */
public class GameServer implements Runnable {

    private ServerOne playerOne;
    private ServerOne playerTwo;
    private boolean gameStarted = false;
    private int playersCount = 0;
    private BufferedReader inOne;
    private BufferedReader inTwo;
    private PrintWriter out;
    volatile int turn = 0;

    public GameServer(ServerOne player) {
        playerOne = player;
        playersCount++;
        System.out.println("Игрок 1 присоединился");
    }

    public String addPlayer(ServerOne player) {
        if (playerTwo == null && playersCount == 1) {
            playerTwo = player;
            playersCount++;
            return ("Игрок 2 присоединился");
        } else {
            return ("Игра уже укомплектована");
        }
    }

    public void run() {

        if (playerOne != null && playerTwo != null) {
            playerOne.setGame(this);
            playerTwo.setGame(this);
            playerOne.playerInGameID = 1;
            playerTwo.playerInGameID = 2;
            System.out.println("Игра началась");
            gameStarted = true;
            turn = playerOne.playerInGameID;
            while (gameStarted) {
                
                //System.out.println("playerID= " + playerOne.playerInGameID + " turn =" + turn);
                if (playerOne.playerInGameID == turn && playerOne.hasMessage) {
                    System.out.println("Игрок " + playerOne.playerInGameID + " прислал сообщение: " + playerOne.message);
                    playerOne.hasMessage = false;
                    //System.out.println("playerOne.hasMessage " + playerOne.hasMessage);
                    playerOne.message = null;
                    //System.out.println("playerOne.message " + playerOne.message);
                    turn = playerTwo.playerInGameID;
                    System.out.println("turnOnServer " + turn);
                } 
                if (playerTwo.playerInGameID == turn && playerTwo.hasMessage) {
                    //System.out.println("зачем я здесь? ");
                    System.out.println("Игрок " + playerTwo.playerInGameID + " прислал сообщение: " + playerTwo.message);
                    playerTwo.hasMessage = false;
                    playerTwo.message = null;
                    turn = playerOne.playerInGameID;
                }
            }
        }
    }

    public boolean isStarted() {
        return gameStarted;
    }

    public int getPlayersCount() {
        return playersCount;
    }
}
