package moe.touko.SimpleChat.Model;

import moe.touko.SimpleChat.Database.Database;

import java.util.HashMap;

public class Message {
    private long id;
    private long room = 10000;
    private String text;
    private String username;
    private String time;
    private String ip;

    // Init function
    public Message(String username, String text, String time, String ip){
        this.Update(username, text, time, ip, 10000);
    }
    public Message(String username, String text, String time, String ip, long room){
        this.Update(username, text, time, ip, room);
    }

    // Init function
    public void Update(String username, String text, String time, String ip){
        this.Update(username, text, time, ip, 10000);
    }
    public void Update(String username, String text, String time, String ip, long room){
        this.username = username;
        this.text = text;
        this.room = room;
        this.time = time;
        this.ip = ip;
    }

    public boolean commit(){
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
            return true;
        }
        else{
            return false;
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getRoom() {
        return room;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public String getIp() {
        return ip;
    }
}
