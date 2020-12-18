package cn.boot.st.managementweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ManagementWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementWebApplication.class, args);
    }


}
