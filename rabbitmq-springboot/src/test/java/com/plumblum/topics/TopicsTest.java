package com.plumblum.topics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: cpb
 * @Date: 2019/1/29 11:56
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicsTest {
    @Autowired
    private TopicsProduceController topics;

    @Test
    public void topics(){
        topics.topics();
        topics.topics1();
        topics.topics2();
    }
}
