package com.mht.rocketmq.controller;

import com.mht.rocketmq.listener.TransactionListenerImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-10-21 15:45
 **/
@RestController
@RequestMapping("provider")
public class MQProducerController {

    public static final Logger LOGGER = LoggerFactory.getLogger(MQProducerController.class);

    @Autowired
    TransactionMQProducer defaultMQProducer;

    /**
     * 发送简单的MQ消息
     *
     * @param msg
     * @return
     */
    @GetMapping("/send")
    public Object send(String msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        if (StringUtils.isEmpty(msg)) {
            return "success";
        }

        LOGGER.info("发送MQ消息内容：" + msg);
        Message sendMsg = new Message("TestTopic", "MyTag", msg.getBytes());
        // 默认3秒超时
        SendResult sendResult = defaultMQProducer.send(sendMsg);
        LOGGER.info("消息发送响应：" + sendResult.toString());
        return sendResult;
    }

    @GetMapping("/send2")
    public Object send2(String msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        if (StringUtils.isEmpty(msg)) {
            return "success";
        }
        LOGGER.info("发送MQ消息内容：" + msg);
        Message sendMsg = new Message("TestTopic", "TestTag", msg.getBytes());
        // 默认3秒超时
        SendResult sendResult = defaultMQProducer.send(sendMsg);
        LOGGER.info("消息发送响应：" + sendResult.toString());
        return sendResult;
    }


}
