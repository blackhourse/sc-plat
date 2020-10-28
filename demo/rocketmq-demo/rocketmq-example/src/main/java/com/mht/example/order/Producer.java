package com.mht.example.order;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-10-28 19:12
 **/
public class Producer {
    public static void main(String[] args) {
        try {
            MQProducer producer = new DefaultMQProducer("testMqGro");
            ((DefaultMQProducer) producer).setNamesrvAddr("127.0.0.1:9876");
            producer.start();

            //顺序发送100条编号为0到99的，orderId为1 的消息
            new Thread(() -> {
                Integer orderId = 1;
                sendMessage(producer, orderId);
            }).start();
            //顺序发送100条编号为0到99的，orderId为2 的消息
            new Thread(() -> {
                Integer orderId = 2;
                sendMessage(producer, orderId);
            }).start();
            //sleep 30秒让消息都发送成功再关闭
            Thread.sleep(1000 * 30);

            producer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(MQProducer producer, Integer orderId) {
        for (int i = 0; i < 100; i++) {
            try {
                Message msg =
                        new Message("TopicTestOrder", "TagA", i + "",
                                (orderId + "").getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, orderId);
                System.out.println("message send,orderId:" + orderId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
