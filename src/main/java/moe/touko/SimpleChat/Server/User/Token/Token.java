package moe.touko.SimpleChat.Server.User.Token;

import moe.touko.SimpleChat.Database.Database;
import moe.touko.SimpleChat.Server.Responser.Responser;
import moe.touko.SimpleChat.Server.User.UserModel;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

public class Token extends Responser {
    private int tokenID;
    private String username;
    private String token;
    private String ip;

    Token(String username, String password, String ip){
        // Init vars
        this.username = username;
        this.ip = ip;
        Database db = Database.getNewDatabase();
        // Check user
        if (UserModel.check(username, password)){
            // If user and password is valid
            this.status = 1;
            this.msg = "Success";
            this.token = RandomStringUtils.randomAlphanumeric(32);
            this.tokenID = db.findMax("Token", "id") + 1;
            // Init push hash map
            Map<String, Object> pushData = new HashMap<>();
            pushData.put("id", this.tokenID);
            pushData.put("username", this.username);
            pushData.put("token", this.token);
            pushData.put("gentime", System.currentTimeMillis() / 1000L);
            pushData.put("expiretime", 3600);
            pushData.put("ip", this.ip);
            // Push data
            db.insert("Token", pushData);
        }
        else{
            this.msg = "Username or password wrong!";
        }
    }


    public int getTokenID() {
        return tokenID;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
