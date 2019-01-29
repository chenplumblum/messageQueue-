package com.plumblum.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: cpb
 * @Date: 2019/1/29 11:16
 * @Description:
 */
@Configuration
public class RoutingConfig {

    @Bean
    public Queue AQueue() {
        return new Queue("routing_A");
    }

    @Bean
    public Queue BQueue() {
        return new Queue("routing_B");
    }

    @Bean
    TopicExchange routingExchange() {
        return new TopicExchange("routing_exchange_demo");
    }

    @Bean
    Binding bindingExchangeMessageA(Queue AQueue, TopicExchange routingExchange) {
        return BindingBuilder.bind(AQueue).to(routingExchange).with("insert");
    }

    @Bean
    Binding bindingExchangeMessageB(Queue BQueue, TopicExchange routingExchange) {
        return BindingBuilder.bind(BQueue).to(routingExchange).with("delete");
    }

}
