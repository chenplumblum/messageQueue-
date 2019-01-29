package com.plumblum.topics;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: cpb
 * @Date: 2019/1/29 09:30
 * @Description:
 */
@Component
@RabbitListener(queues = "topics_A")
public class TopicsConsumer1 {

    @RabbitHandler
    public void process(String message){
        System.out.println("consumer1:"+message);
    }

}
