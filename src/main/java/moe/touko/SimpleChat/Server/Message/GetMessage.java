package moe.touko.SimpleChat.Server.Message;

import moe.touko.SimpleChat.Database.Database;
import moe.touko.SimpleChat.Model.Message;
import moe.touko.SimpleChat.Server.Responser.ResponderMessage;

import java.util.*;

public class GetMessage extends ResponderMessage {

    public GetMessage(){
        this(10, 1);
    }
    public GetMessage(long number){
        this(number, 1);
    }
    public GetMessage(long number, long page){
        // GetMessage message array and reverse it.
        Database database = Database.getNewDatabase();
        List msgArrs = database.select("Conversation");
        for (Object msgArrObj: msgArrs
             ) {
            Map msgArr = (HashMap)msgArrObj;
            Message message = new Message(
                    msgArr.get("text").toString(),
                    msgArr.get("time").toString(),
                    msgArr.get("time").toString(),
                    msgArr.get("ip").toString(),
                    Long.parseLong(msgArr.get("room").toString())
            );
            message.setId(Long.parseLong(msgArr.get("id").toString()));
            this.messages.add(message);
        }
        Collections.reverse(msgArrs);

        this.status = 1;
        this.msg = "Success";
    }
}
