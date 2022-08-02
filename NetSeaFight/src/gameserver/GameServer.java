package gameserver;

import lobby.network.PlayerMirror;

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
            playerOne.setPlayerInGameID(1);
            playerTwo.setPlayerInGameID(2);
            System.out.println("Игра началась");
            gameOn = true;
            turn = playerOne.getPlayerInGameID();
            while (gameOn) {

                /*if (playerOne.playerInGameID == turn && playerOne.hasMessage) {
                    processingMessage(playerOne.message, 1, 2);
                    setTurn(playerTwo);
                }
                if (playerTwo.playerInGameID == turn && playerTwo.hasMessage) {
                    processingMessage(playerTwo.message, 2, 1);
                    setTurn(playerOne);
                }*/
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
        /*synchronized (this) {
            if (sender == playerOne) {
                playerTwo.setMessage(playerOne.message);
            } else {
                playerOne.setMessage(playerTwo.message);
            }
        }
        sender.hasMessage = false;
        sender.message = null;*/

        turn = playerOne.getPlayerInGameID();
    }

    public void setTurn(PlayerMirror receiver) {
        turn = receiver.getPlayerInGameID();
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
                shoot(message[1], sender);
                break; //выстрел: координаты
            case "msg":
                chatMessage(message[1], sender);
                break; //просто сообщение - переслать в чат
            case "serv":
                serviceMessage(message[1], sender);
                break; //сервисноее сообщение - начало игры, конец игры, дисконнект, передача игрового поля итд
        }
    }

    public synchronized String chatMessage(String message, int sender) {
        if (sender == playerOne.getPlayerInGameID()) {
            //synchronized (this) {
                playerTwo.setMessage(message);
            //}
            return "Выслано второму игроку";
        }
        if (sender == playerTwo.getPlayerInGameID()) {
            //synchronized (this) {
                playerOne.setMessage(message);
            //}
            return "Выслано первому игроку";
        }
        return "ошибка чата";
    }

    public synchronized String serviceMessage(String message, int sender) { //
        return null;
    }

    public synchronized String shoot(String message, int sender) { //нужен только отправитель - получатель всегда сервер, но унифицируем логику
        if (sender == playerOne.getPlayerInGameID()) {
            //synchronized (this) {
                playerTwo.setMessage("пригнись второй");
            //}
            return "Выслано второму игроку";
        }
        if (sender == playerTwo.getPlayerInGameID()) {
            //synchronized (this) {
                playerOne.setMessage("пригнись первый");
            //}
            return "Выслано первому игроку";
        }
        return "ошибка выстрела";
    }
}
