package com.sharding.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-29
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.sharding.datasource.mapper")
public class ShardingDatasourceDemo1Application {
    public static void main(String[] args) {
        SpringApplication.run(ShardingDatasourceDemo1Application.class, args);
    }

}
