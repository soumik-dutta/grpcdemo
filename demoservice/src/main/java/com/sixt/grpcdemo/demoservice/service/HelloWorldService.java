package com.sixt.grpcdemo.demoservice.service;


import com.sixt.demoservice.DemoserviceGrpc;
import com.sixt.demoservice.DemoserviceOuterClass;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;


public class HelloWorldService extends DemoserviceGrpc.DemoserviceImplBase {

    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void getHelloWorld(DemoserviceOuterClass.HelloWorldRequest request, StreamObserver<DemoserviceOuterClass.HelloWorldResponse> responseObserver) {
        System.out.println("Hello world grpc called ");
        String name = new StringBuffer().append("Hello, ")
                .append(request.getName())
                .toString();

        DemoserviceOuterClass.HelloWorldResponse response = DemoserviceOuterClass.HelloWorldResponse.newBuilder()
                .setMessage(name)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
