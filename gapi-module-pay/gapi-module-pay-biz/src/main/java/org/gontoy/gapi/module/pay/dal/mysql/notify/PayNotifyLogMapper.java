package org.gontoy.gapi.module.pay.dal.mysql.notify;

import org.gontoy.gapi.module.pay.dal.dataobject.notify.PayNotifyLogDO;
import org.gontoy.gapi.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayNotifyLogMapper extends BaseMapperX<PayNotifyLogDO> {
}
