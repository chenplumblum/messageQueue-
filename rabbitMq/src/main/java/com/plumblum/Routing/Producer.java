package com.plumblum.Routing;

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
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            String routing = "delete";
            String message = "测试 delete";

            String routing1 = "insert";
            String message2 = "测试 insert";
            channel.basicPublish(EXCHANGE_NAME, routing, null, message.getBytes());
            System.out.println(" [x] Sent '" + routing + "':'" + message + "'");
            channel.basicPublish(EXCHANGE_NAME, routing1, null, message2.getBytes());
            System.out.println(" [x] Sent '" + routing1 + "':'" + message2 + "'");

            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
