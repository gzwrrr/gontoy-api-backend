package org.gontoy.gapi.module.system.convert.sms;

import org.gontoy.gapi.module.system.controller.admin.sms.vo.channel.SmsChannelCreateReqVO;
import org.gontoy.gapi.module.system.controller.admin.sms.vo.channel.SmsChannelRespVO;
import org.gontoy.gapi.module.system.controller.admin.sms.vo.channel.SmsChannelSimpleRespVO;
import org.gontoy.gapi.module.system.controller.admin.sms.vo.channel.SmsChannelUpdateReqVO;
import org.gontoy.gapi.module.system.dal.dataobject.sms.SmsChannelDO;
import org.gontoy.gapi.framework.common.pojo.PageResult;
import org.gontoy.gapi.framework.sms.core.property.SmsChannelProperties;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 短信渠道 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface SmsChannelConvert {

    SmsChannelConvert INSTANCE = Mappers.getMapper(SmsChannelConvert.class);

    SmsChannelDO convert(SmsChannelCreateReqVO bean);

    SmsChannelDO convert(SmsChannelUpdateReqVO bean);

    SmsChannelRespVO convert(SmsChannelDO bean);

    List<SmsChannelRespVO> convertList(List<SmsChannelDO> list);

    PageResult<SmsChannelRespVO> convertPage(PageResult<SmsChannelDO> page);

    List<SmsChannelProperties> convertList02(List<SmsChannelDO> list);

    List<SmsChannelSimpleRespVO> convertList03(List<SmsChannelDO> list);

}
