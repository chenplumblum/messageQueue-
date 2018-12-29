package com.plumblum.JMS2;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

/**
 * @Auther: cpb
 * @Date: 2018/12/20 17:33
 * @Description:
 */
@Service("jms2Consume1")
public class Consume1 {

    @JmsListener(destination = "queue2")
    @SendTo("out.queue")//返回consume打印信息
    public String receiveQueue(String text) {
        System.out.println("Consumer1收到的报文为:"+text);
        return "return 消息1"+text;
    }

}
