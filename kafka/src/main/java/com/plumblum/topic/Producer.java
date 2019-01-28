package com.plumblum.topic;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @Auther: cpb
 * @Date: 2019/1/25 17:20
 * @Description:
 */
public class Producer {
    static Logger log = LoggerFactory.getLogger(Producer.class);

    private static final String TOPIC = "test_topic";
    private static final String BROKER_LIST = "localhost:9092";
    private static KafkaProducer<String,String> producer = null;

    /*
    初始化生产者
     */
    static {
        Properties configs = initConfig();
        producer = new KafkaProducer<String, String>(configs);
    }

    /*
    初始化配置
     */
    private static Properties initConfig(){
        //        配置项在ProductConfig类中查看
        Properties properties = new Properties();
        //配置ip和端口
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BROKER_LIST);
        //配置key的序列化方式。
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //配置value的序列化方式
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return properties;
    }

    public static void main(String[] args) throws InterruptedException {
        //消息实体
        ProducerRecord<String , String> record = null;
        for (int i = 0; i < 10; i++) {
//            配置topic
            record = new ProducerRecord<String, String>(TOPIC, "value"+(int)(10*(Math.random())));
            //发送消息
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (null != e){
                        log.info("send error" + e.getMessage());
                    }else {
                        System.out.println(String.format("测试：offset:%s,partition:%s",recordMetadata.offset(),recordMetadata.partition()));
                    }
                }
            });
        }
        producer.close();
    }


}
