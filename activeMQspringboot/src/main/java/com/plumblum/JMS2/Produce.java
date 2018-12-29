package com.plumblum.JMS2;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;


/**
 * @Auther: cpb
 * @Date: 2018/12/20 17:33
 * @Description:
 */
@Component("jms2Produce")
public class Produce {
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    public void sendMessage(String destinationName,String message){
        Destination destination = new ActiveMQQueue("topic");
        jmsTemplate.convertAndSend(destination,message);
    }

    @JmsListener(destination="out.queue")
    public void consumerMessage(String text){
        System.out.println("从out.queue队列收到的回复报文为:"+text);
    }

}
