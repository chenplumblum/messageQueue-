package com.plumblum.topic;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;

/**
 * @Auther: cpb
 * @Date: 2019/1/25 17:29
 * @Description:
 */
public class Consumer {


    static Logger log = LoggerFactory.getLogger(Producer.class);

    private static final String TOPIC = "test_topic";
    private static final String BROKER_LIST = "localhost:9092";
    private static KafkaConsumer<String,String> consumer = null;

    static {
        Properties configs = initConfig();
        consumer = new KafkaConsumer<String, String>(configs);
    }

    private static Properties initConfig(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers",BROKER_LIST);
        properties.put("group.id","0");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.offset.reset", "earliest");
        return properties;
    }


    public static void main(String[] args) {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(10);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
            }
        }
    }


}
