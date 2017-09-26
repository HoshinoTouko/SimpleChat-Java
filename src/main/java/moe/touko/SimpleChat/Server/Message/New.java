package moe.touko.SimpleChat.Server.Message;

import moe.touko.SimpleChat.Model.Message;
import moe.touko.SimpleChat.Server.Responser.ResponderMessage;
import moe.touko.SimpleChat.Server.User.Token.Check;

public class New extends ResponderMessage {
    private String ip;

    New(
            long room,
            String text,
            String username,
            String ip,
            String time,
            String token
    ){
        // Init a dialog and ip
        Message message = new Message(username, text, time, ip, room);
        // Check token
        this.ip = ip;
        if (Check.CheckToken(username, token)){
            // Commit data and resolve dialog.
            if (message.commit()){
                this.messages.add(message);
                this.status = 1;
                this.msg = "Success!";
            }
            else{
                this.msg = "No text";
            }
        }
        else {
            this.msg = "Invalid token";
        }

    }

    public String getIp() {
        return ip;
    }
}
