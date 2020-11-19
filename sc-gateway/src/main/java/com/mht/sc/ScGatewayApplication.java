package com.mht.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ScGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScGatewayApplication.class);
    }

}
