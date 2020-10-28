package com.mht.example.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-10-28 19:13
 **/
public class Consumer {

    // 1. 消费是无顺序的
    public static void main(String[] args) throws MQClientException {

//        noOrder();

        order();
    }

    /**
     * 无顺序消费
     */
    private static void noOrder() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("testMqConsumer");

        consumer.setNamesrvAddr("127.0.0.1:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe("TopicTestOrder", "*");
        //单个消费者中多线程并行消费
        consumer.setConsumeThreadMin(3);
        consumer.setConsumeThreadMin(6);

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
//                    System.out.println("收到消息," + new String(msg.getBody()));
                    System.out.println("queueId:" + msg.getQueueId() + ",orderId:" + new String(msg.getBody()) + ",i:" + msg.getKeys());
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

        System.out.printf("Consumer Started.%n");
    }

    private static void order() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("testMqConsumer");

        consumer.setNamesrvAddr("127.0.0.1:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe("TopicTestOrder", "*");
        //单个消费者中多线程并行消费
        consumer.setConsumeThreadMin(3);
        consumer.setConsumeThreadMin(6);

        consumer.registerMessageListener(new MessageListenerOrderly() {


            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt msg : msgs) {
//                    System.out.println("收到消息," + new String(msg.getBody()));
                    System.out.println("queueId:" + msg.getQueueId() + ",orderId:" + new String(msg.getBody()) + ",i:" + msg.getKeys());

                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();

        System.out.printf("Consumer Started.%n");
    }


}
