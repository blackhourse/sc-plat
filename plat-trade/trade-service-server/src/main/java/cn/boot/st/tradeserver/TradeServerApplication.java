package cn.boot.st.tradeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = "cn.boot.st")
@EnableDiscoveryClient
public class TradeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeServerApplication.class, args);
    }


}
