package com.plumblum.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: cpb
 * @Date: 2019/1/29 09:23
 * @Description:
 */
@Configuration
public class QueueConfig {

    @Bean
    public Queue queue(){
        return new Queue("queue_demo");
    }

}
