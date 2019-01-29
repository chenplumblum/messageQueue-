package com.plumblum.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: cpb
 * @Date: 2019/1/29 10:13
 * @Description:
 */
@Configuration
public class TopicConfig {
    @Bean
    public Queue AMessage() {
        return new Queue("topic_A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("topic_B");
    }


    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("topic_exchange_demo");
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }


}
