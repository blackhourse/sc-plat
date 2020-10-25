package com.mht.rocketmq.controller;

import com.mht.rocketmq.model.AccountInfo;
import com.mht.rocketmq.service.UserService;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-10-22 16:05
 **/
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @GetMapping("{id}")
    public AccountInfo getAccountInfo(@PathVariable Long id) {
        return userService.getAccountInfo(id);
    }

    @GetMapping("test")
    public String test(@PathVariable Long id) throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        String name = "aaaa";
        Message msg = new Message("TopicTest", "tags1", name.getBytes(RemotingHelper.DEFAULT_CHARSET));
        // 发送消息到一个Broker
        SendResult sendResult = defaultMQProducer.send(msg);
        // 通过sendResult返回消息是否成功送达
        System.out.printf("%s%n", sendResult);
        return "1";
    }


}
