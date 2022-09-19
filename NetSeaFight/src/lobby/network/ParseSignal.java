package lobby.network;

/**
 *
 * @author GAV
 */
public class ParseSignal {
    
    public static String[] parseSignal(String rawSignal) {
        return rawSignal.split(";");
    }
}
