package com.stomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by Catbat on 18.11.2016.
 */

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception{
        Thread.sleep(1000); // -> Simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

}
