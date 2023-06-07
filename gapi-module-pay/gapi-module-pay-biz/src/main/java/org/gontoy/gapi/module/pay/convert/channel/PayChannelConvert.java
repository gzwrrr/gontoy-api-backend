package org.gontoy.gapi.module.pay.convert.channel;

import java.util.*;

import org.gontoy.gapi.framework.common.pojo.PageResult;

import org.gontoy.gapi.module.pay.controller.admin.merchant.vo.channel.PayChannelCreateReqVO;
import org.gontoy.gapi.module.pay.controller.admin.merchant.vo.channel.PayChannelExcelVO;
import org.gontoy.gapi.module.pay.controller.admin.merchant.vo.channel.PayChannelRespVO;
import org.gontoy.gapi.module.pay.controller.admin.merchant.vo.channel.PayChannelUpdateReqVO;
import org.gontoy.gapi.module.pay.dal.dataobject.merchant.PayChannelDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayChannelConvert {

    PayChannelConvert INSTANCE = Mappers.getMapper(PayChannelConvert.class);

    @Mapping(target = "config",ignore = true)
    PayChannelDO convert(PayChannelCreateReqVO bean);

    @Mapping(target = "config",ignore = true)
    PayChannelDO convert(PayChannelUpdateReqVO bean);

    @Mapping(target = "config",expression = "java(org.gontoy.gapi.framework.common.util.json.JsonUtils.toJsonString(bean.getConfig()))")
    PayChannelRespVO convert(PayChannelDO bean);

    List<PayChannelRespVO> convertList(List<PayChannelDO> list);

    PageResult<PayChannelRespVO> convertPage(PageResult<PayChannelDO> page);

    List<PayChannelExcelVO> convertList02(List<PayChannelDO> list);



}
