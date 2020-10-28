package demo;

import com.mht.example.ExampleApplication;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-10-28 18:38
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExampleApplication.class)
public class ConsumerTest {

    @Test
    public void test() throws MQClientException {

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

/*
    public static void main(String[] args) throws InterruptedException, MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name_4");

        consumer.setNamesrvAddr("111.231.110.149:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe("TopicTestjjj", "*");
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
    }*/
}
