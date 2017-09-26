package moe.touko.SimpleChat.Server.User.Token;

import moe.touko.SimpleChat.Database.Database;
import moe.touko.SimpleChat.Server.Responser.Responser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Clear extends Responser {

    Clear(){
        Database db = Database.getNewDatabase();
        ArrayList tokens = db.select("Token");
        for (Object tokenobj: tokens) {
            Map token = (Map) tokenobj;
            if (
                    (Integer)token.get("gentime") + (Integer) token.get("expiretime")
                            < System.currentTimeMillis()/1000L
                    ){
                Map delData = new HashMap<>();
                delData.put("id", token.get("id"));
                db.delete("Token", delData);
            }
        }
        this.status = 1;
        this.msg = "Success";
    }
}
