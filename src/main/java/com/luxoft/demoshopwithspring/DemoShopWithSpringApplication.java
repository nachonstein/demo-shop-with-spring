package com.luxoft.demoshopwithspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableAsync
@EnableRetry
public class DemoShopWithSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoShopWithSpringApplication.class, args);
    }

}
