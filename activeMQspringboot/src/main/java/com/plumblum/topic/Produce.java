package com.plumblum.topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;


/**
 * @Auther: cpb
 * @Date: 2018/12/20 17:00
 * @Description:
 */
@Component("topicProduce")
public class Produce {

    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsTemplate;

    public void sendMessage(String destinationName,final String message){
        ActiveMQTopic activeMQTopic = new ActiveMQTopic(destinationName);
        jmsTemplate.convertAndSend(activeMQTopic,message);
    }

}
