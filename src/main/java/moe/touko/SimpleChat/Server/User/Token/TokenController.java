package moe.touko.SimpleChat.Server.User.Token;

import moe.touko.SimpleChat.Database.Database;
import moe.touko.SimpleChat.Server.User.Token.Token;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user/token")
public class TokenController {

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Token receive(
            HttpServletRequest request,
            @RequestParam(value = "username", defaultValue = "Anonymous") String username,
            @RequestParam(value = "password", defaultValue = "NoPassword") String password
    ){
        // GetMessage ip address
        String ip = request.getRemoteAddr();
        // Push to receive entity
        return new Token(username, password, ip);
    }

    @RequestMapping(value = "check", method = RequestMethod.GET)
    public Check check(
            @RequestParam(value = "username", defaultValue = "Anonymous") String username,
            @RequestParam(value = "token", defaultValue = "NoToken") String token
    ){
        return new Check(username, token);
    }

    @RequestMapping(value = "clear")
    public Clear clear(){
        return new Clear();
    }
}
