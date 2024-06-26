package com.sixt.grpcdemo.gateway.client;

import com.sixt.demoservice.DemoserviceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;


@Service
public class DemoServiceClientProxy {
    ManagedChannel channel;
    @PostConstruct
    private void postConstruct(){
        channel = ManagedChannelBuilder.forAddress("10.105.98.156", 8081)
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
