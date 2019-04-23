package consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2019/4/23 17:36
 * @Description:
 */
public class Consumer {
//    public static void main(String[] args){
//        DefaultMQPushConsumer consumer =
//                new DefaultMQPushConsumer("PushConsumer");
//        consumer.setNamesrvAddr("172.0.0.1:9876");
//        try {
//            //订阅PushTopic下Tag为push的消息
//            consumer.subscribe("PushTopic", "push");
//            //程序第一次启动从消息队列头取数据
//            consumer.setConsumeFromWhere(
//                    ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//            consumer.registerMessageListener(
//                    new MessageListenerConcurrently() {
//                        @Override
//                        public ConsumeConcurrentlyStatus consumeMessage(
//                                List<MessageExt> list,
//                                ConsumeConcurrentlyContext Context) {
//                            Message msg = list.get(0);
//                            System.out.println(msg.toString());
//                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                        }
//                    }
//            );
//            consumer.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
        public static void main(String[] args) throws InterruptedException, MQClientException {

            // Instantiate with specified consumer group name.
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");

            // Specify name server addresses.
            consumer.setNamesrvAddr("localhost:9876");

            // Subscribe one more more topics to consume.
            consumer.subscribe("TopicTest", "*");
            // Register callback to execute on arrival of messages fetched from brokers.
            consumer.registerMessageListener(new MessageListenerConcurrently() {

                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {
                    System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            //Launch the consumer instance.
            consumer.start();

            System.out.printf("Consumer Started.%n");
        }

}
