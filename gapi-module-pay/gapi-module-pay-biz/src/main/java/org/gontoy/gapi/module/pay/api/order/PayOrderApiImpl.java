package org.gontoy.gapi.module.pay.api.order;

import org.gontoy.gapi.module.pay.api.order.dto.PayOrderCreateReqDTO;
import org.gontoy.gapi.module.pay.api.order.dto.PayOrderRespDTO;
import org.gontoy.gapi.module.pay.convert.order.PayOrderConvert;
import org.gontoy.gapi.module.pay.dal.dataobject.order.PayOrderDO;
import org.gontoy.gapi.module.pay.service.order.PayOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 支付单 API 实现类
 *
 * @author 芋道源码
 */
@Service
public class PayOrderApiImpl implements PayOrderApi {

    @Resource
    private PayOrderService payOrderService;

    @Override
    public Long createOrder(PayOrderCreateReqDTO reqDTO) {
        return payOrderService.createPayOrder(reqDTO);
    }

    @Override
    public PayOrderRespDTO getOrder(Long id) {
        PayOrderDO order = payOrderService.getOrder(id);
        return PayOrderConvert.INSTANCE.convert2(order);
    }

}
