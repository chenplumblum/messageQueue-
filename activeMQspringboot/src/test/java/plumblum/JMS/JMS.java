package plumblum.JMS;

import com.plumblum.JMS.Produce;
import com.plumblum.PlumblumApplication;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

/**
 * @Auther: cpb
 * @Date: 2018/12/20 17:13
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlumblumApplication.class)
public class JMS {

    @Autowired
    private Produce produce;

    @Test
    public void test() throws InterruptedException {

        for(int i=0; i<100; i++){
            produce.sendMessage("queue", "接收信息!!!");
        }
    }

}
