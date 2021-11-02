package com.example.springbootkafka.provider;

import com.alibaba.fastjson.JSONObject;
import com.example.springbootkafka.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;

/**
 * 消息提供者
 */
@Slf4j
@Component
public class KafkaProvider {
    /**
     * 消息队列
     */
    private static final String TOPIC = "message";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(long orderId, String orderNum, LocalDateTime createTime) {
        //构建一个订单类
        Order order = Order.builder().orderId(orderId).orderNum(orderNum).createTime(createTime).build();
        //发送消息 订单类的json 作为消息体
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, JSONObject.toJSONString(order));
        //监听回馈
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("message send fail....");
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("message send success ...");
            }
        });

    }

}
