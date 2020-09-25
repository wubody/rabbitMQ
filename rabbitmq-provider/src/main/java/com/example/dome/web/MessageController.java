package com.example.dome.web;

import com.example.dome.entity.Producer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.ws.RequestWrapper;

@RestController
public class MessageController {
    @Resource
    private Producer producer;

    @RequestMapping("/message")
    public void send() {
        for (int i = 0; i < 10; i++) {
            this.producer.send();
        }
    }
}
