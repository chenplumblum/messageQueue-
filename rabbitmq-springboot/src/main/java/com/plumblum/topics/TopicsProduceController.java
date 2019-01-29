package com.plumblum.topics;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cpb
 * @Date: 2019/1/29 09:25
 * @Description:
 */
@RestController
public class TopicsProduceController {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public String topics(){
        String message = "hello orange and red";
        String routingKey = "fruit.colour";
        String exchange = "topicsExchange";

        rabbitTemplate.convertAndSend(exchange,routingKey, message);

        return message;
    }

    public String topics1(){
        String message = "hello rabbit and white";
        String routingKey = "animal.colour";
        String exchange = "topicsExchange";

        rabbitTemplate.convertAndSend(exchange,routingKey, message);

        return message;
    }
    public String topics2(){
        String message = "hello cat";
        String routingKey = "animal";
        String exchange = "topicsExchange";

        rabbitTemplate.convertAndSend(exchange,routingKey, message);

        return message;
    }

}
