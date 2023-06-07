package org.gontoy.gapi.framework.sms.core.client.impl.debug;

import org.gontoy.gapi.framework.common.exception.ErrorCode;
import org.gontoy.gapi.framework.common.exception.enums.GlobalErrorCodeConstants;
import org.gontoy.gapi.framework.sms.core.client.SmsCodeMapping;
import org.gontoy.gapi.framework.sms.core.enums.SmsFrameworkErrorCodeConstants;

import java.util.Objects;

/**
 * 钉钉的 SmsCodeMapping 实现类
 *
 * @author 芋道源码
 */
public class DebugDingTalkCodeMapping implements SmsCodeMapping {

    @Override
    public ErrorCode apply(String apiCode) {
        return Objects.equals(apiCode, "0") ? GlobalErrorCodeConstants.SUCCESS : SmsFrameworkErrorCodeConstants.SMS_UNKNOWN;
    }

}
