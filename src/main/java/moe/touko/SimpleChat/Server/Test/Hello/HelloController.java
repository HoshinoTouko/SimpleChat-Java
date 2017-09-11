package moe.touko.SimpleChat.Server.Test.Hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/Hello")
    public String index() {
        return "Hello world.";
    }
}
