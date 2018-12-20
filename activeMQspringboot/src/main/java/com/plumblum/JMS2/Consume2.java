package com.plumblum.JMS2;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

/**
 * @Auther: cpb
 * @Date: 2018/12/20 17:33
 * @Description:
 */
@Service
public class Consume2 {
    @JmsListener(destination = "topic")
    @SendTo("out.queue")
    public String receiveQueue(String text) {
        System.out.println("Consumer2收到的报文为:"+text);
        return "return message"+text;
    }
}
