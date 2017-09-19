package moe.touko.SimpleChat.Server.Message;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/api")
public class MessageController {

    @RequestMapping(value = "new")
    public Receive receive(
            @RequestParam(value = "room", defaultValue = "10000") String room,
            @RequestParam(value = "text", defaultValue = "NoContent") String text,
            @RequestParam(value = "username", defaultValue = "Anonymous") String username,
            @RequestParam(value = "ip", defaultValue = "0.0.0.0") String ip
    ){
        Date nowTime = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String time = df.format(nowTime);
        long roomNumber = Long.parseLong(room);
        return new Receive(roomNumber, text, username, ip, time);
    }
}
