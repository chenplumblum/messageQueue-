package com.plumblum.topics;

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

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            // 设置MabbitMQ所在主机ip或者主机名
            factory.setHost("localhost");            //factory.setUsername("lp");//factory.setPassword("");// factory.setPort(2088);
            // 创建一个连接
            Connection connection = factory.newConnection();
            // 创建一个频道
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            String routingKey = "fruit.colour";
            String message = "Hello orange and black ";

            String routingKey1 = "animal.colour";
            String message1 = "Hello black and rabbit ";

            String routingKey2 = "animal";
            String message2 = "Hello rabbit";



            channel.queueDeclare(routingKey, false, false, false, null);
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
            System.out.println(" 消息队列信息：" + message );

            channel.queueDeclare(routingKey1, false, false, false, null);
            channel.basicPublish(EXCHANGE_NAME, routingKey1, null, message1.getBytes());
            System.out.println(" 消息队列信息：" + message1 );

            channel.queueDeclare(routingKey2, false, false, false, null);
            channel.basicPublish(EXCHANGE_NAME, routingKey2, null, message2.getBytes());
            System.out.println(" 消息队列信息：" + message2 );





            // 关闭频道和连接
            channel.close();
            connection.close();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
