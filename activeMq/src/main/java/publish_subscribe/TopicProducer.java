package publish_subscribe;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: cpb
 * @Date: 2018/12/20 11:14
 * @Description:
 */
public class TopicProducer {
    public static void main(String[] args) {
        try {
            //1.创建连接工厂
            ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            //2.获取连接
            Connection connection = connectionFactory.createConnection();
            //3.启动连接
            connection.start();
             /*4.获取session  (参数1：是否启动事务,
            参数2：消息确认模式)*/
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //5.创建主题对象
            Topic topic = session.createTopic("test-topic");
            //6.创建消息生产者
            MessageProducer producer = session.createProducer(topic);
            //7.创建消息
            for(int i = 0 ;i<10;i++) {
                TextMessage textMessage = session.createTextMessage("发送订阅消息"+i);
                //8.发送消息
                producer.send(textMessage);
                System.out.println("发送订阅消息"+i);
            }
            //9.关闭资源
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
