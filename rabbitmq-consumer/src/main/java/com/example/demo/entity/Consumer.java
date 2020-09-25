package com.example.demo.entity;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = "simple")
    public void receive(String message) {
        System.out.println("消费者1收到消息："+ message);
    }
}