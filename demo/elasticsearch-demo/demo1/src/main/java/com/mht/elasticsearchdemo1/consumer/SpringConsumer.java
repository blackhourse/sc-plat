package com.mht.elasticsearchdemo1.consumer;

import com.alibaba.fastjson.JSON;
import com.mht.elasticsearchdemo1.bean.CanalBean;
import com.mht.elasticsearchdemo1.model.CmsArticle;
import com.mht.elasticsearchdemo1.model.EsCmsArticle;
import com.mht.elasticsearchdemo1.repository.CmsArticleRepository;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RocketMQMessageListener(
        topic = "cms_article",
        consumerGroup = "cms-article",
        selectorExpression = "*",
        consumeMode = ConsumeMode.ORDERLY,
        messageModel = MessageModel.CLUSTERING,
        consumeThreadMax = 1
)
public class SpringConsumer implements RocketMQListener<String> {
    private Logger logger = LoggerFactory.getLogger(SpringConsumer.class.getSimpleName());

    @Autowired
    CmsArticleRepository cmsArticleRepository;


    /**
     * 实现方式很简单吧，但是你也看见了代码中就没有消息能够消费是否成功后的确认方式，因为实现的onMessage()方法是个void的，还好看过原始的rocketmq的消费者实现方式，也就是rocketmq-client.jar的实现，它是MessageListener.java类来实现消息监听接收的，而它有2个继承接口类MessageListenerConcurrently.java和MessageListenerOrderly.java，这样就好找了，直接收一下这2个接口的实现类，乖乖，果然找到了在rocket-spring-boot的jar里面，就是DefaultRocketMQListenerContainer.java这个类，看下其中一个实现
     *
     * @param msg
     */
    @Override
    public void onMessage(String msg) {
        System.out.println("接收到消息 -> " + msg);
        CanalBean canalBean = JSON.parseObject(msg, CanalBean.class);
        String table = canalBean.getTable();
        System.out.println(table.toString());
        String type = canalBean.getType();
        System.out.println(type);
        List<CmsArticle> data = canalBean.getData();
        data.stream().forEach(tbTest -> {
            EsCmsArticle esCmsArticle = new EsCmsArticle();
            System.out.println(tbTest.toString());
            if ("UPDATE".equals(type) && "cms_article".equals(table)) {
                Optional<EsCmsArticle> article = cmsArticleRepository.findById(tbTest.getCourseId());
                if (article.isPresent()) {
                    EsCmsArticle cmsArticle = article.get();
                    BeanUtils.copyProperties(tbTest, cmsArticle);
                    cmsArticleRepository.save(cmsArticle);
                    logger.info("id = {} 编辑es成功", cmsArticle.getCourseId());
                } else {
                    BeanUtils.copyProperties(tbTest, esCmsArticle);
                    cmsArticleRepository.save(esCmsArticle);
                    logger.info("id = {} 添加es成功", esCmsArticle.getCourseId());
                }
            } else if ("INSERT".equals(type) && "cms_article".equals(table)) {
                BeanUtils.copyProperties(tbTest, esCmsArticle);
                cmsArticleRepository.save(esCmsArticle);
                logger.info("id = {} 添加es成功", esCmsArticle.getCourseId());
            }
        });


    }
}
