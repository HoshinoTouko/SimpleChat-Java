package moe.touko.SimpleChat.Server.Message;

import moe.touko.SimpleChat.Database.Database;
import moe.touko.SimpleChat.Model.Conversation.ConversationEntity;

import java.util.HashMap;

public class Receive extends ConversationEntity {

    private int status;
    private String msg;

    Receive (long room, String text, String username, String ip, String time){
        this.room = room;
        this.text = text;
        this.username = username;
        this.ip = ip;
        this.time = time;

        Database database = Database.getNewDatabase();
        this.id = database.findMax("Conversation", "id") + 1;
        HashMap<String, Object> pushData = new HashMap<>();
        pushData.put("id", this.id);
        pushData.put("room", this.room);
        pushData.put("text", this.text);
        pushData.put("username", this.username);
        pushData.put("ip", this.ip);
        pushData.put("time", this.time);

        if (!this.text.equals("NoContent")){
            database.insert("Conversation", pushData);
            this.status = 1;
            this.msg = "OK!";
        }
        else{
            this.msg = "No text.";
        }

    }

    public int getStatus() {
        return status;
    }
    public String getMsg() {
        return msg;
    }
}