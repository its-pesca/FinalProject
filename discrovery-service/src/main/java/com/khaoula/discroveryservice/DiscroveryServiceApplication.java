package com.khaoula.discroveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscroveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscroveryServiceApplication.class, args);
    }

}
