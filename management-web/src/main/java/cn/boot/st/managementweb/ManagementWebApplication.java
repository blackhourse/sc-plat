package cn.boot.st.managementweb;

import cn.boot.common.framework.core.IntArrayValuable;
import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = "cn.boot.st")
public class ManagementWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementWebApplication.class, args);
    }


}
