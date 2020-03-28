package com.sixt.grpcdemo.demoservice;

import com.sixt.grpcdemo.demoservice.service.HelloWorldService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;


public class DemoserviceApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8081)
                .addService(new HelloWorldService()).build();

        server.start();
        server.awaitTermination();
    }
}
