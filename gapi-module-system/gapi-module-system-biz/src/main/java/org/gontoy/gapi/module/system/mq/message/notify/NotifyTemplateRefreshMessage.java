package org.gontoy.gapi.module.system.mq.message.notify;

import org.gontoy.gapi.framework.mq.core.pubsub.AbstractChannelMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 站内信模板的数据刷新 Message
 *
 * @author xrcoder
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NotifyTemplateRefreshMessage extends AbstractChannelMessage {

    @Override
    public String getChannel() {
        return "system.notify-template.refresh";
    }

}
