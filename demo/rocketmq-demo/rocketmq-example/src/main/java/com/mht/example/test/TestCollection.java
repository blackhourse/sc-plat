package com.mht.example.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-10-29 20:14
 **/
public class TestCollection {
    public static void main(String[] args) {

        LinkedList<String> strings = new LinkedList<>();


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("", "");

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>(16);
        String put = concurrentHashMap.put("", "");
        String s = concurrentHashMap.get("");
        concurrentHashMap.forEach((s1, s2) -> {

        });
        concurrentHashMap.entrySet().stream().forEach(stringStringEntry -> {

        });

    }

}
