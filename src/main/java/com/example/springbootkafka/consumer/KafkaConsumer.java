package com.example.springbootkafka.consumer;

import com.alibaba.fastjson.JSONObject;
import com.example.springbootkafka.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "message",groupId = "ankki")
    public void consume(String msg) {
        System.out.println("***consum message .... ->" +msg);

    }

}
