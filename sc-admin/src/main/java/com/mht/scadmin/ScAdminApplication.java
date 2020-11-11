package com.mht.scadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ScAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScAdminApplication.class, args);
    }

}
