package JMS;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.transport.amqp.protocol.AmqpConnection;

import javax.jms.*;

/**
 * @Auther: cpb
 * @Date: 2018/12/20 10:38
 * @Description:
 */
public class Produce {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD =ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKERURL =ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        //1.创建连接工厂
        ConnectionFactory connectionFactory;
        //2.获取连接
        Connection connection;
        //3.创建会话
        Session session;
        //4.创建会话队列
        Queue queue;
        //5.创建消息生产者
        MessageProducer messageProducer;
        //6.主题
        Destination destination;

        try {
            connectionFactory = new ActiveMQConnectionFactory(Produce.USERNAME,Produce.PASSWORD,Produce.BROKERURL);
            connection = connectionFactory.createConnection();
            connection.start();
            /*4.获取session  (参数1：是否启动事务,
             参数2：消息确认模式[
             AUTO_ACKNOWLEDGE = 1    自动确认
             CLIENT_ACKNOWLEDGE = 2    客户端手动确认
             DUPS_OK_ACKNOWLEDGE = 3    自动批量确认
             SESSION_TRANSACTED = 0    事务提交并确认
            ])*/
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            queue = session.createQueue("firstQueue");
            messageProducer = session.createProducer(queue);
            for(int i = 0 ; i<10 ; i++){
                //7.创建消息
                TextMessage textMessage = session.createTextMessage("activeMq发送信息："+i);
               //8.发送消息
                messageProducer.send(textMessage);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {

        }
    }


}
