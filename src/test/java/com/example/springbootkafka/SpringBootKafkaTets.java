package com.example.springbootkafka;


import com.example.springbootkafka.provider.KafkaProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootKafkaTets {
    @Autowired
    private KafkaProvider kafkaProvider;

    @Test
    public void sendMessage() throws InterruptedException {
        // 发送 1000 个消息
        for (int i = 0; i < 1000; i++) {
            long orderId = i+1;
            String orderNum = UUID.randomUUID().toString();
            kafkaProvider.sendMessage(orderId, orderNum, LocalDateTime.now());
        }

        TimeUnit.MINUTES.sleep(1);
    }

}
