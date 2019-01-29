package com.plumblum.config;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: cpb
 * @Date: 2019/1/29 11:41
 * @Description:
 */
@Configuration
public class TopicsConfig {
    @Bean
    public Queue AMessage() {
        return new Queue("topics_A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("topics_B");
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicsExchange");
    }

    @Bean
    Binding bindingExchangeMessageA(Queue AMessage, TopicExchange exchange) {
        return BindingBuilder.bind(AMessage).to(exchange).with("*.colour");
    }

    @Bean
    Binding bindingExchangeMessageB(Queue BMessage, TopicExchange exchange) {
        return BindingBuilder.bind(BMessage).to(exchange).with("animal");
    }

}
