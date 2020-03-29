package com.sixt.grpcdemo.gateway.client;

import com.sixt.demoservice.DemoserviceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class DemoServiceClientProxy {
    ManagedChannel channel;
    @PostConstruct
    private void postConstruct(){
        channel = ManagedChannelBuilder.forAddress("demoservice", 8081)
                .usePlaintext()
                .build();
    }

    public DemoserviceGrpc.DemoserviceBlockingStub getStub(){
        DemoserviceGrpc.DemoserviceBlockingStub stub = DemoserviceGrpc.newBlockingStub(channel);
        return stub;
    }



    @PreDestroy
    private void destroy(){
        channel.shutdown();
    }


}
