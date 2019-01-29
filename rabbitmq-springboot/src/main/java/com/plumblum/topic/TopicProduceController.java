package com.plumblum.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cpb
 * @Date: 2019/1/29 09:25
 * @Description:
 */
@RestController
public class TopicProduceController {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public String topic(){
        String message = "hello topic_demo";
        String routingKey = "topic_exchange_demo";
        String exchange = "topic_exchange_demo";
        rabbitTemplate.convertAndSend(exchange,routingKey, message);
        return message;
    }

}
