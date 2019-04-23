package producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Auther: cpb
 * @Date: 2019/4/23 17:35
 * @Description:
 */
public class Producer {
//    public static void main(String[] args) {
//        DefaultMQProducer producer = new DefaultMQProducer("Producer");
//        producer.setNamesrvAddr("172.0.0.1:9876");
//        //设置自动创建topic的key值
//        producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");
//        try {
//            producer.start();
//
//            Message msg = new Message("PushTopic",
//                    "push",
//                    "1",
//                    "Just for test.".getBytes());
//
//            SendResult result = producer.send(msg);
//            System.out.println("id:" + result.getMsgId() +
//                    " result:" + result.getSendStatus());
//
//            msg = new Message("PushTopic",
//                    "push",
//                    "2",
//                    "Just for test.".getBytes());
//
//            result = producer.send(msg);
//            System.out.println("id:" + result.getMsgId() +
//                    " result:" + result.getSendStatus());
//
//            msg = new Message("PullTopic",
//                    "pull",
//                    "1",
//                    "Just for test.".getBytes());
//
//            result = producer.send(msg);
//            System.out.println("id:" + result.getMsgId() +
//                    " result:" + result.getSendStatus());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            producer.shutdown();
//        }
//    }
    public static void main(String[] args) throws Exception {
    //Instantiate with a producer group name.
    DefaultMQProducer producer = new
            DefaultMQProducer("please_rename_unique_group_name");
    // Specify name server addresses.
    producer.setNamesrvAddr("localhost:9876");
    //Launch the instance.
    producer.start();
    for (int i = 0; i < 100; i++) {
        //Create a message instance, specifying topic, tag and message body.
        Message msg = new Message("TopicTest" /* Topic */,
                "TagA" /* Tag */,
                ("Hello RocketMQ " +
                        i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
        );
        //Call send message to deliver message to one of brokers.
        SendResult sendResult = producer.send(msg);
        System.out.printf("%s%n", sendResult);
    }
    //Shut down once the producer instance is not longer in use.
    producer.shutdown();
}
}
