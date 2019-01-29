package com.plumblum.topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: cpb
 * @Date: 2019/1/29 10:39
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTest {
    @Autowired
    private TopicProduceController topicProduceController;

    @Test
    public void topic (){
        topicProduceController.topic();
    }
}
