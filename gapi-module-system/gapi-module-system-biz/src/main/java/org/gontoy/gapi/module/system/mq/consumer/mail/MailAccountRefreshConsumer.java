package org.gontoy.gapi.module.system.mq.consumer.mail;

import org.gontoy.gapi.framework.mq.core.pubsub.AbstractChannelMessageListener;
import org.gontoy.gapi.module.system.mq.message.mail.MailAccountRefreshMessage;
import org.gontoy.gapi.module.system.mq.message.mail.MailTemplateRefreshMessage;
import org.gontoy.gapi.module.system.service.mail.MailAccountService;
import org.gontoy.gapi.module.system.service.mail.MailTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link MailAccountRefreshMessage} 的消费者
 *
 * @author wangjingyi
 */
@Component
@Slf4j
public class MailAccountRefreshConsumer extends AbstractChannelMessageListener<MailAccountRefreshMessage> {

    @Resource
    private MailAccountService mailAccountService;

    @Override
    public void onMessage(MailAccountRefreshMessage message) {
        log.info("[onMessage][收到 Mail Account 刷新信息]");
        mailAccountService.initLocalCache();
    }

}
