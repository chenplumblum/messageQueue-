package com.plumblum.topic;

import com.rabbitmq.client.*;

import java.io.IOException;


/**
 * @Auther: cpb
 * @Date: 2019/1/24 15:53
 * @Description:
 */
public class Customer {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明交换机名称及其类型
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        //创建随机队列名称
        String queueName = channel.queueDeclare().getQueue();
        //绑定交换机和队列之间的关系；destination：队列名称，source：交换机名称，routing:路由键，map：arguments
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
