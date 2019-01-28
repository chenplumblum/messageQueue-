package com.plumblum.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cpb
 * @Date: 2019/1/28 13:26
 * @Description:
 */
@RestController
public class ConsumerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    //异步调用方法
    @KafkaListener(topics = {"test"})
    public void getMessage(ConsumerRecord<?, ?> record) {
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + record.value().toString());
    }


}
