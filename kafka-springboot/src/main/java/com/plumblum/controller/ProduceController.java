package com.plumblum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cpb
 * @Date: 2019/1/28 10:23
 * @Description:
 */
@RestController
public class ProduceController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/produce")
    public String sendMessage(){
        String message = "你好，我是produce";
        String topic = "test";
        kafkaTemplate.send(topic,message);
        return message;

    }
}
