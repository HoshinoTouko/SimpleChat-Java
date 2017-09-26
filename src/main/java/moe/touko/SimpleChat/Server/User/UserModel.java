package moe.touko.SimpleChat.Server.User;

import moe.touko.SimpleChat.Common.Common;
import moe.touko.SimpleChat.Database.Database;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;

public class UserModel {

    public static Boolean check (String username, String password){
        Database db = Database.getNewDatabase();
        ArrayList users = db.select("User");
        if(users.size() == 0){
            return false;
        }
        else{
            // Init vars
            Map user;
            String passAfterSHA = "";
            // Foreach users to check the password
            for (Object userobj: users
                 ) {
                user = (Map)userobj;
                if(username.equals(user.get("username"))){
                    try{
                        MessageDigest shaMD = MessageDigest.getInstance("SHA-256");
                        shaMD.update(password.getBytes("UTF-8"));
                        passAfterSHA = Common.byte2Hex(shaMD.digest());
                    }
                    catch (NoSuchAlgorithmException e){
                        e.printStackTrace();
                    }
                    catch (UnsupportedEncodingException e){
                        e.printStackTrace();
                        return false;
                    }
                    if (passAfterSHA.equals(user.get("password"))) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
