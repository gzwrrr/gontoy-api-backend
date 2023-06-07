package org.gontoy.gapi.module.system.mq.producer.sensitiveword;

import org.gontoy.gapi.framework.mq.core.RedisMQTemplate;
import org.gontoy.gapi.module.system.mq.message.sensitiveword.SensitiveWordRefreshMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 敏感词相关的 Producer
 */
@Component
public class SensitiveWordProducer {

    @Resource
    private RedisMQTemplate redisMQTemplate;

    /**
     * 发送 {@link SensitiveWordRefreshMessage} 消息
     */
    public void sendSensitiveWordRefreshMessage() {
        SensitiveWordRefreshMessage message = new SensitiveWordRefreshMessage();
        redisMQTemplate.send(message);
    }

}
