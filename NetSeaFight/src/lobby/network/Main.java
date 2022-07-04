package lobby.network;

/**
 *
 * @author ksmnote
 */
public class Main {
    
    public static void main(String[] args) {
        Lobby lobby = new Lobby();    
        Thread lobbyThread = new Thread(lobby);
        lobbyThread.run();
        
        
    }
    
    
}
