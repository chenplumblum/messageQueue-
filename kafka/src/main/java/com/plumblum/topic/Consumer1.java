package com.plumblum.topic;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Auther: cpb
 * @Date: 2019/1/25 17:29
 * @Description:
 */
public class Consumer1 {


    static Logger log = LoggerFactory.getLogger(Producer.class);

    private static final String TOPIC = "test_topic";
    private static final String BROKER_LIST = "localhost:9092";
    private static KafkaConsumer<String,String> consumer = null;

    static {
        Properties configs = initConfig();
        consumer = new KafkaConsumer<String, String>(configs);
    }

    private static Properties initConfig(){
//        配置项在ConsumerConfig类中查看
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,BROKER_LIST);
        //group_id用于分组，每组只有一个consumer获取到消息，用于做负载均衡，group内部开多少个consumer来消费完全取决于数据量和业务的实时性
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"1");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return properties;
    }


    public static void main(String[] args) {
        consumer.subscribe(Arrays.asList(TOPIC));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
            }
        }
    }


}
