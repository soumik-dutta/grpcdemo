package com.sixt.grpcdemo.gateway.controller;

import com.sixt.grpcdemo.gateway.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class HelloWorldController {

    @Autowired
    private HelloWorldService service;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value="${message.topic.name}")
    private String topicName;

    @GetMapping("/hello")
    public String getHelloWorld(@RequestParam("name") String name){
        return service.getMessage(name);
    }

    @GetMapping("/publish")
    public void publishMessage(@RequestParam("message") String message) throws ExecutionException, InterruptedException {
        kafkaTemplate.send(topicName, message);
    }


}
