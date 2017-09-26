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

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public New receive(
            HttpServletRequest request,
            @RequestParam(value = "room", defaultValue = "10000") String room,
            @RequestParam(value = "text", defaultValue = "NoContent") String text,
            @RequestParam(value = "username", defaultValue = "Anonymous") String username,
            @RequestParam(value = "token", defaultValue = "Anonymous") String token
    ){
        // Get ip address
        String ip = request.getRemoteAddr();
        // Get now time
        Date nowTime = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String time = df.format(nowTime);
        // Get room number
        long roomNumber = Long.parseLong(room);
        // Push to receive entity
        return new New(roomNumber, text, username, ip, time, token);
    }
}
