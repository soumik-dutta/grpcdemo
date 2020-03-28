package com.sixt.grpcdemo.gateway.service;

import com.sixt.demoservice.DemoserviceOuterClass;
import com.sixt.grpcdemo.gateway.client.DemoServiceClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    @Autowired
    private DemoServiceClientProxy demoServiceClientProxy;

    public String getMessage(String message){
        DemoserviceOuterClass.HelloWorldRequest request = DemoserviceOuterClass.HelloWorldRequest.newBuilder()
                .setName(message)
                .build();
       DemoserviceOuterClass.HelloWorldResponse response = demoServiceClientProxy.getStub().getHelloWorld(request);

       return response.getMessage();
    }
}
