package com.plumblum.queue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: cpb
 * @Date: 2019/1/29 09:42
 * @Description:
 */
@Component
@RabbitListener(queues = "queue_demo")
public class Consumer2 {

    @RabbitHandler
    public void process(String message){
        System.out.println("consumer2:"+message);
    }

}




