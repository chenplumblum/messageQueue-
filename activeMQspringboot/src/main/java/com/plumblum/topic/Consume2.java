package com.plumblum.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @Auther: cpb
 * @Date: 2018/12/20 16:59
 * @Description:
 */
@Service("consume2")
public class Consume2 {
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "topic")
    public void receiveQueue(String text) {
        System.out.println("Consumer2收到的报文为:"+text);
    }
}
