package com.mht.sc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-11-25 15:14
 **/
@Data
@Component
@ConfigurationProperties(prefix = "gateway.ignore")
public class IgnoreUrls {
    private ArrayList<String> urls;

}
