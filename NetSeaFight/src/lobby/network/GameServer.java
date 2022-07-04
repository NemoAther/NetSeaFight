package lobby.network;

/**
 *
 * @author ksmnote
 */
public class GameServer implements Runnable {

    private ServerOne playerOne;
    private ServerOne playerTwo;
    private boolean gameStarted = false;
    private int playersCount = 0;

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
        if(playerOne != null && playerTwo != null) {
            System.out.println("Игра началась");
            gameStarted = true;
            
            System.out.println("Игра закончилась");
            //gameStarted = false;
        }
    }
    public boolean isStarted() {
        return gameStarted;
    }
    public int getPlayersCount() {
        return playersCount;
    }
}
