package com.plumblum.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: cpb
 * @Date: 2019/1/24 16:01
 * @Description:
 */
public class Producer {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) {

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            //exchange：交换机名称，type:类型，durable：消息是否启用持久化，autoDelete：是否自动删除，internal，argument
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            String message = "测试topic模式";
            //exchange:交换机名称，routing :路由键，
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
