package com.sixt.grpcdemo.gateway.controller;

import com.sixt.grpcdemo.gateway.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private HelloWorldService service;

    @GetMapping("/hello")
    public String getHelloWorld(@RequestParam("name") String name){
        return service.getMessage(name);
    }
}
