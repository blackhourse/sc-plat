package com.boot.sentinel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SentinalController
 * @Description
 * @Date 2021/1/4
 * @Created by maht
 */

@RestController
@Slf4j
public class SentinalController {
    @GetMapping("/getA")
    public String getA() {
        log.info("Hello -- A");
        return "Hello -- A";
    }

    @GetMapping("/getB")
    public String getB() {
        log.info("Hello -- B");
        return "Hello -- B";
    }
}