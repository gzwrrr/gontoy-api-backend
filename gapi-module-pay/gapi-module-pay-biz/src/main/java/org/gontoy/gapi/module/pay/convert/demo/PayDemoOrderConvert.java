package org.gontoy.gapi.module.pay.convert.demo;

import org.gontoy.gapi.framework.common.pojo.PageResult;
import org.gontoy.gapi.module.pay.controller.admin.demo.vo.PayDemoOrderCreateReqVO;
import org.gontoy.gapi.module.pay.controller.admin.demo.vo.PayDemoOrderRespVO;
import org.gontoy.gapi.module.pay.dal.dataobject.demo.PayDemoOrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 示例订单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface PayDemoOrderConvert {

    PayDemoOrderConvert INSTANCE = Mappers.getMapper(PayDemoOrderConvert.class);

    PayDemoOrderDO convert(PayDemoOrderCreateReqVO bean);

    PayDemoOrderRespVO convert(PayDemoOrderDO bean);

    PageResult<PayDemoOrderRespVO> convertPage(PageResult<PayDemoOrderDO> page);

}
