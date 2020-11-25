package com.mht.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ScAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScAdminApplication.class, args);
    }


}
