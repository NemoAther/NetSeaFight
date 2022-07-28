package lobby.network;

/**
 *
 * @author ksmnote
 */
public class GameServer implements Runnable {

    private PlayerMirror playerOne;
    private PlayerMirror playerTwo;
    private boolean gameOn = false;
    private int playersCount = 0;
    volatile int turn = 0;

    public GameServer(PlayerMirror player) {
        playerOne = player;
        playersCount++;
        System.out.println("Игрок 1 присоединился");
    }

    public String addPlayer(PlayerMirror player) {
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
            gameOn = true;
            turn = playerOne.playerInGameID;
            while (gameOn) {
                if (playerOne.playerInGameID == turn && playerOne.hasMessage) {
                    processingMessage(playerOne.message, 1, 2);
                    setTurn(playerTwo);
                }
                if (playerTwo.playerInGameID == turn && playerTwo.hasMessage) {
                    processingMessage(playerTwo.message, 2, 1);
                    setTurn(playerOne);
                }
                /*
                server.message(string, 0, playerID)
                
                
                 */
            }
        }
    }

    public boolean isStarted() {
        return gameOn;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    /*public void messagePlayerOne() {
        //System.out.print("Игрок " + playerOne.playerInGameID + " прислал сообщение: " + playerOne.message);
        synchronized (this) {
            playerTwo.setMessage(playerOne.message);
        }
        playerOne.hasMessage = false;
        playerOne.message = null;
        turn = playerTwo.playerInGameID;
    }*/
    public void processingMessage(PlayerMirror sender) {
        //System.out.print("Игрок " + playerTwo.playerInGameID + " прислал сообщение: " + playerTwo.message);
        synchronized (this) {
            if (sender == playerOne) {
                playerTwo.setMessage(playerOne.message);
            } else {
                playerOne.setMessage(playerTwo.message);
            }
        }
        sender.hasMessage = false;
        sender.message = null;
        turn = playerOne.playerInGameID;
    }

    public void setTurn(PlayerMirror receiver) {
        turn = receiver.playerInGameID;
    }

    /**
     * @param rawMessage исходное сообщение для разбора
     * @param sender отправитель 0 - сервер, 1 - первый игрок, 2 - второй игрок
     * @param reseiver адресат 0 - сервер, 1 - первый игрок, 2 - второй игрок
     */
    public void processingMessage(String rawMessage, int sender, int reseiver) {

        String[] message = rawMessage.split(";"); //добавить ограничитель, чтобы делил только по первому вхождению
        switch (message[0]) {
            case "shoot":
                shoot(message[1], sender, reseiver);
                break; //выстрел: координаты
            case "msg":
                chatMessage(message[1], sender, reseiver);
                break; //просто сообщение - переслать в чат
            case "serv":
                serviceMessage(message[1], sender, reseiver);
                break; //сервисноее сообщение - начало игры, конец игры, дисконнект, передача игрового поля итд
        }
    }

    public void chatMessage(String message, int sender, int reseiver) {

    }

    public void serviceMessage(String message, int sender, int reseiver) { //

    }

    public void shoot(String message, int sender, int reseiver) { //нужен только отправитель - получатель всегда сервер, но унифицируем логику

    }
}
