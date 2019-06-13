package moe.touko.SimpleChat.Server.Message;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/message")
public class MessageController {

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public GetMessage getMessage(
            HttpServletRequest request,
            @RequestParam(value = "room", defaultValue = "10000") long room,
            @RequestParam(value = "number", defaultValue = "10") long number,
            @RequestParam(value = "page", defaultValue = "1") long page
    ){
        return new GetMessage(room, number, page);
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public NewMessage newMessage(
            HttpServletRequest request,
            @RequestParam(value = "room", defaultValue = "10000") String room,
            @RequestParam(value = "text", defaultValue = "NoContent") String text,
            @RequestParam(value = "username", defaultValue = "Anonymous") String username,
            @RequestParam(value = "token", defaultValue = "Anonymous") String token
    ){
        // GetMessage ip address
        String ip = request.getRemoteAddr();
        // GetMessage now time
        Date nowTime = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String time = df.format(nowTime);
        // GetMessage room number
        long roomNumber = Long.parseLong(room);
        // Push to receive entity
        return new NewMessage(roomNumber, text, username, ip, time, token);
    }
}
