package com.example.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfiguration {

    /**
     * 声明一个名为simple的队列
     */
    @Bean
    public Queue testQueue() {
        return new Queue("simple");
    }
}