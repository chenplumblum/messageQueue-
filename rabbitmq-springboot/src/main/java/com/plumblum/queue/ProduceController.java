package com.plumblum.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cpb
 * @Date: 2019/1/29 09:25
 * @Description:
 */
@RestController
public class ProduceController {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public String queue(){
        String message = "hello queue_demo";
        String routingKey = "queue_demo";
        for(int i = 0; i<10; i++) {
            rabbitTemplate.convertAndSend(routingKey, message+" "+i);
        }
        return message;
    }

}
