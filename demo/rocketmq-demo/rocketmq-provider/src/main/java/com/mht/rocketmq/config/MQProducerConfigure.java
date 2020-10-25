package com.mht.rocketmq.config;

import com.mht.rocketmq.listener.TransactionListenerImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-10-21 15:32
 **/
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "rocketmq.producer")
public class MQProducerConfigure {
    public static final Logger LOGGER = LoggerFactory.getLogger(MQProducerConfigure.class);

    private String groupName;
    private String namesrvAddr;
    /**
     * 消息最大值
     */
    private Integer maxMessageSize;
    /**
     * 消息发送超时时间
     */
    private Integer sendMsgTimeOut;
    /**
     * 失败重试次数
     */
    private Integer retryTimesWhenSendFailed;

    /**
     * mq 生成者配置
     *
     * @return
     * @throws MQClientException
     */
    @Bean
    @ConditionalOnProperty(prefix = "rocketmq.producer", value = "isOnOff", havingValue = "on")
    public TransactionMQProducer defaultProducer() throws MQClientException {
        TransactionListenerImpl transactionListener = new TransactionListenerImpl();
        LOGGER.info("defaultProducer 正在创建---------------------------------------");
        TransactionMQProducer producer = new TransactionMQProducer(groupName);
        producer.setTransactionListener(transactionListener);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeOut);
        producer.setRetryTimesWhenSendAsyncFailed(retryTimesWhenSendFailed);
        producer.start();
        LOGGER.info("rocketmq producer server 开启成功----------------------------------");
        return producer;
    }

}
