package lobby.network;

/**
 *
 * @author GAV
 */
public class ParseSignal {
    
    //String[] parsedSignal;
    //String signalType;
    //String signalValue;
    
    public static String[] parseSignal(String rawSignal) {
        //parsedSignal = rawSignal.split(";");
        /*switch(parsedSignal[0]) {
            case "shoot": break; //выстрел: координаты
            case "msg": break; //просто сообщение - переслать в чат
            case "serv": break; //сервисноее сообщение - начало игры, конец игры, дисконнект, передача игрового поля итд
        }*/
        return rawSignal.split(";");
    }
}
