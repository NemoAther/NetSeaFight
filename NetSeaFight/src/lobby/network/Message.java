/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby.network;

import java.io.Serializable;

/**
 *
 * @author ksmnote
 */
public class Message implements Serializable {
    private final String msgType;
    private final Object msgData;
    //private final int receiverID;
    
    public Message(String type, Object data) {
        msgType = type;
        msgData = data;
        //this.receiverID = receiverId; //не нужен т.к. мессадж используется только между клиентом и его зеркалом
    }

    public String getType() {
        return msgType;
    }

    public Object getData() {
        return msgData;
    }
    /*public int getReceiverID() {
        return receiverID;
    }*/
}
