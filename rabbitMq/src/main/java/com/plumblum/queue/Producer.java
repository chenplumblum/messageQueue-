package com.plumblum.queue;

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
    private final static String QUEUE_NAME = "helloMytest";

    public static void main(String[] args) throws IOException {
        try {
            /**
             * 创建连接连接到MabbitMQ
             */
            ConnectionFactory factory = new ConnectionFactory();
            // 设置MabbitMQ所在主机ip或者主机名
            factory.setHost("localhost");            //factory.setUsername("lp");//factory.setPassword("");// factory.setPort(2088);
            // 创建一个连接
            Connection connection = factory.newConnection();
            // 创建一个频道
            Channel channel = connection.createChannel();
            // 发送的消息
            String message = "hello 你好";

            // 指定一个队列【参数说明：参数一：队列名称，参数二：是否持久化；参数三：是否独占模式；参数四：消费者断开连接时是否删除队列；参数五：消息其他参数】
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 往队列中发出一条消息【参数说明：参数一：交换机名称；参数二：路由键名称，参数三：消息的其他属性-routing headers，此属性为MessageProperties.PERSISTENT_TEXT_PLAIN用于设置纯文本消息存储到硬盘；参数四：消息主体】
            for(int i = 0 ; i<10 ; i++) {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            }
            System.out.println(" 消息队列信息：" + message );
            // 关闭频道和连接
            channel.close();
            connection.close();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
