package moe.touko.SimpleChat.Server.User.Token;

import moe.touko.SimpleChat.Database.Database;
import moe.touko.SimpleChat.Server.Responser.Responser;

import java.util.ArrayList;
import java.util.Map;

public class Check extends Responser {

    Check(String username, String token){
        if (CheckToken(username, token)){
            this.status = 1;
            this.msg = "Success";
        }
        else{
            this.msg = "Invalid token";
        }
    }

    private static boolean CheckToken (String username, String token){
        Database db = Database.getNewDatabase();
        ArrayList tokens = db.select("Token");
        for (Object tokenobj: tokens
             ) {
            Map tokenMap = (Map) tokenobj;
            if (tokenMap.get("username").equals(username)){
                if (tokenMap.get("token").equals(token)){
                    return true;
                }
            }
        }
        return false;
    }

    public String getMsg() {
        return msg;
    }

    public int getStatus() {
        return status;
    }
}
