package com.plumblum.queue;

import com.rabbitmq.client.*;
import java.io.IOException;


/**
 * @Auther: cpb
 * @Date: 2019/1/24 15:53
 * @Description:
 */
public class Customer {
    private static final String QUEUE_NAME ="helloMytest";

    public static void main(String[] argv) throws Exception {
        //建立连接和通道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //consumer消费完一条信息后producer才能发送。prefetch
        channel.basicQos(1);
        //声明要消费的队列【参数说明：参数一：队列名称，参数二：是否持久化；参数三：是否独占模式；参数四：消费者断开连接时是否删除队列；参数五：消息其他参数】
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //回调消费消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        //【参数说明：参数一：队列名称，参数二：ack，表示消费者接受完参数mq才能删除消息；参数三：消费者回调函数】
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
