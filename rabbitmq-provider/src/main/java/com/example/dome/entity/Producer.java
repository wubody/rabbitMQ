package com.example.dome.entity;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Producer {

    @Resource
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String message = "hello";
        this.rabbitTemplate.convertAndSend("simple", message);
    }
}