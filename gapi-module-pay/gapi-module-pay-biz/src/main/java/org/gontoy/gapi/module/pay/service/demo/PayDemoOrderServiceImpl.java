package org.gontoy.gapi.module.pay.service.demo;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import org.gontoy.gapi.framework.common.pojo.PageParam;
import org.gontoy.gapi.framework.common.pojo.PageResult;
import org.gontoy.gapi.framework.common.util.json.JsonUtils;
import org.gontoy.gapi.module.pay.api.order.PayOrderApi;
import org.gontoy.gapi.module.pay.api.order.dto.PayOrderCreateReqDTO;
import org.gontoy.gapi.module.pay.api.order.dto.PayOrderRespDTO;
import org.gontoy.gapi.module.pay.api.refund.PayRefundApi;
import org.gontoy.gapi.module.pay.api.refund.dto.PayRefundCreateReqDTO;
import org.gontoy.gapi.module.pay.api.refund.dto.PayRefundRespDTO;
import org.gontoy.gapi.module.pay.controller.admin.demo.vo.PayDemoOrderCreateReqVO;
import org.gontoy.gapi.module.pay.dal.dataobject.demo.PayDemoOrderDO;
import org.gontoy.gapi.module.pay.dal.mysql.demo.PayDemoOrderMapper;
import org.gontoy.gapi.module.pay.enums.order.PayOrderStatusEnum;
import org.gontoy.gapi.module.pay.enums.refund.PayRefundStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static cn.hutool.core.util.ObjectUtil.*;
import static org.gontoy.gapi.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.gontoy.gapi.framework.common.util.date.LocalDateTimeUtils.addTime;
import static org.gontoy.gapi.framework.common.util.json.JsonUtils.toJsonString;
import static org.gontoy.gapi.framework.common.util.servlet.ServletUtils.getClientIP;
import static org.gontoy.gapi.module.pay.enums.ErrorCodeConstants.*;

/**
 * 示例订单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class PayDemoOrderServiceImpl implements PayDemoOrderService {

    /**
     * 接入的实力应用编号
     *
     * 从 [支付管理 -> 应用信息] 里添加
     */
    private static final Long PAY_APP_ID = 7L;

    /**
     * 商品信息 Map
     *
     * key：商品编号
     * value：[商品名、商品价格]
     */
    private final Map<Long, Object[]> spuNames = new HashMap<>();

    @Resource
    private PayOrderApi payOrderApi;
    @Resource
    private PayRefundApi payRefundApi;

    @Resource
    private PayDemoOrderMapper payDemoOrderMapper;

    public PayDemoOrderServiceImpl() {
        spuNames.put(1L, new Object[]{"华为手机", 1});
        spuNames.put(2L, new Object[]{"小米电视", 10});
        spuNames.put(3L, new Object[]{"苹果手表", 100});
        spuNames.put(4L, new Object[]{"华硕笔记本", 1000});
        spuNames.put(5L, new Object[]{"蔚来汽车", 200000});
    }

    @Override
    public Long createDemoOrder(Long userId, PayDemoOrderCreateReqVO createReqVO) {
        // 1.1 获得商品
        Object[] spu = spuNames.get(createReqVO.getSpuId());
        Assert.notNull(spu, "商品({}) 不存在", createReqVO.getSpuId());
        String spuName = (String) spu[0];
        Integer price = (Integer) spu[1];
        // 1.2 插入 demo 订单
        PayDemoOrderDO demoOrder = new PayDemoOrderDO().setUserId(userId)
                .setSpuId(createReqVO.getSpuId()).setSpuName(spuName)
                .setPrice(price).setPayed(false).setRefundPrice(0);
        payDemoOrderMapper.insert(demoOrder);

        // 2.1 创建支付单
        Long payOrderId = payOrderApi.createOrder(new PayOrderCreateReqDTO()
                .setAppId(PAY_APP_ID).setUserIp(getClientIP()) // 支付应用
                .setMerchantOrderId(demoOrder.getId().toString()) // 业务的订单编号
                .setSubject(spuName).setBody("").setAmount(price) // 价格信息
                .setExpireTime(addTime(Duration.ofHours(2L)))); // 支付的过期时间
        // 2.2 更新支付单到 demo 订单
        payDemoOrderMapper.updateById(new PayDemoOrderDO().setId(demoOrder.getId())
                .setPayOrderId(payOrderId));
        // 返回
        return demoOrder.getId();
    }

//    private void validateDemoOrderExists(Long id) {
//        if (demoOrderMapper.selectById(id) == null) {
//            throw exception(DEMO_ORDER_NOT_EXISTS);
//        }
//    }

    @Override
    public PayDemoOrderDO getDemoOrder(Long id) {
        return payDemoOrderMapper.selectById(id);
    }

    @Override
    public PageResult<PayDemoOrderDO> getDemoOrderPage(PageParam pageReqVO) {
        return payDemoOrderMapper.selectPage(pageReqVO);
    }

    @Override
    public void updateDemoOrderPaid(Long id, Long payOrderId) {
        // 校验并获得支付订单（可支付）
        PayOrderRespDTO payOrder = validateDemoOrderCanPaid(id, payOrderId);

        // 更新 PayDemoOrderDO 状态为已支付
        int updateCount = payDemoOrderMapper.updateByIdAndPayed(id, false,
                new PayDemoOrderDO().setPayed(true).setPayTime(LocalDateTime.now())
                        .setPayChannelCode(payOrder.getChannelCode()));
        if (updateCount == 0) {
            throw exception(PAY_DEMO_ORDER_UPDATE_PAID_STATUS_NOT_UNPAID);
        }
    }

    /**
     * 校验交易订单满足被支付的条件
     *
     * 1. 交易订单未支付
     * 2. 支付单已支付
     *
     * @param id 交易订单编号
     * @param payOrderId 支付订单编号
     * @return 交易订单
     */
    private PayOrderRespDTO validateDemoOrderCanPaid(Long id, Long payOrderId) {
        // 1.1 校验订单是否存在
        PayDemoOrderDO order = payDemoOrderMapper.selectById(id);
        if (order == null) {
            throw exception(PAY_DEMO_ORDER_NOT_FOUND);
        }
        // 1.2 校验订单未支付
        if (order.getPayed()) {
            log.error("[validateDemoOrderCanPaid][order({}) 不处于待支付状态，请进行处理！order 数据是：{}]",
                    id, toJsonString(order));
            throw exception(PAY_DEMO_ORDER_UPDATE_PAID_STATUS_NOT_UNPAID);
        }
        // 1.3 校验支付订单匹配
        if (notEqual(order.getPayOrderId(), payOrderId)) { // 支付单号
            log.error("[validateDemoOrderCanPaid][order({}) 支付单不匹配({})，请进行处理！order 数据是：{}]",
                    id, payOrderId, toJsonString(order));
            throw exception(PAY_DEMO_ORDER_UPDATE_PAID_FAIL_PAY_ORDER_ID_ERROR);
        }

        // 2.1 校验支付单是否存在
        PayOrderRespDTO payOrder = payOrderApi.getOrder(payOrderId);
        if (payOrder == null) {
            log.error("[validateDemoOrderCanPaid][order({}) payOrder({}) 不存在，请进行处理！]", id, payOrderId);
            throw exception(PAY_ORDER_NOT_FOUND);
        }
        // 2.2 校验支付单已支付
        if (!PayOrderStatusEnum.isSuccess(payOrder.getStatus())) {
            log.error("[validateDemoOrderCanPaid][order({}) payOrder({}) 未支付，请进行处理！payOrder 数据是：{}]",
                    id, payOrderId, toJsonString(payOrder));
            throw exception(PAY_DEMO_ORDER_UPDATE_PAID_FAIL_PAY_ORDER_STATUS_NOT_SUCCESS);
        }
        // 2.3 校验支付金额一致
        if (notEqual(payOrder.getAmount(), order.getPrice())) {
            log.error("[validateDemoOrderCanPaid][order({}) payOrder({}) 支付金额不匹配，请进行处理！order 数据是：{}，payOrder 数据是：{}]",
                    id, payOrderId, toJsonString(order), toJsonString(payOrder));
            throw exception(PAY_DEMO_ORDER_UPDATE_PAID_FAIL_PAY_PRICE_NOT_MATCH);
        }
        // 2.4 校验支付订单匹配（二次）
        if (notEqual(payOrder.getMerchantOrderId(), id.toString())) {
            log.error("[validateDemoOrderCanPaid][order({}) 支付单不匹配({})，请进行处理！payOrder 数据是：{}]",
                    id, payOrderId, toJsonString(payOrder));
            throw exception(PAY_DEMO_ORDER_UPDATE_PAID_FAIL_PAY_ORDER_ID_ERROR);
        }
        return payOrder;
    }

    @Override
    public void refundDemoOrder(Long id, String userIp) {
        // 1. 校验订单是否可以退款
        PayDemoOrderDO order = validateDemoOrderCanRefund(id);

        // 2.1 创建退款单
        Long payRefundId = payRefundApi.createPayRefund(new PayRefundCreateReqDTO()
                .setAppId(PAY_APP_ID).setUserIp(getClientIP()) // 支付应用
                .setPayOrderId(order.getPayOrderId()) // 支付单号
                .setReason("想退钱").setAmount(order.getPrice()));// 价格信息
        // 2.2 更新退款单到 demo 订单
        payDemoOrderMapper.updateById(new PayDemoOrderDO().setId(id)
                .setPayRefundId(payRefundId).setRefundPrice(order.getPrice()));
    }

    private PayDemoOrderDO validateDemoOrderCanRefund(Long id) {
        // 校验订单是否存在
        PayDemoOrderDO order = payDemoOrderMapper.selectById(id);
        if (order == null) {
            throw exception(PAY_DEMO_ORDER_NOT_FOUND);
        }
        // 校验订单是否支付
        if (!order.getPayed()) {
            throw exception(PAY_DEMO_ORDER_REFUND_FAIL_NOT_PAID);
        }
        // 校验订单是否已退款
        if (order.getPayRefundId() != null) {
            throw exception(PAY_DEMO_ORDER_REFUND_FAIL_REFUNDED);
        }
        return order;
    }

    @Override
    public void updateDemoOrderRefunded(Long id, Long payRefundId) {
        // 1. 校验并获得退款订单（可退款）
        PayRefundRespDTO payRefund = validateDemoOrderCanRefunded(id, payRefundId);
        // 2.2 更新退款单到 demo 订单
        payDemoOrderMapper.updateById(new PayDemoOrderDO().setId(id)
                .setRefundTime(payRefund.getSuccessTime()));
    }

    private PayRefundRespDTO validateDemoOrderCanRefunded(Long id, Long payRefundId) {
        // 1.1 校验示例订单
        PayDemoOrderDO order = payDemoOrderMapper.selectById(id);
        if (order == null) {
            throw exception(PAY_DEMO_ORDER_NOT_FOUND);
        }
        // 1.2 校验退款订单匹配
        if (Objects.equals(order.getPayOrderId(), payRefundId)) {
            log.error("[validateDemoOrderCanRefunded][order({}) 退款单不匹配({})，请进行处理！order 数据是：{}]",
                    id, payRefundId, toJsonString(order));
            throw exception(PAY_DEMO_ORDER_REFUND_FAIL_REFUND_ORDER_ID_ERROR);
        }

        // 2.1 校验退款订单
        PayRefundRespDTO payRefund = payRefundApi.getPayRefund(payRefundId);
        if (payRefund == null) {
            throw exception(PAY_DEMO_ORDER_REFUND_FAIL_REFUND_NOT_FOUND);
        }
        // 2.2
        if (!PayRefundStatusEnum.isSuccess(payRefund.getStatus())) {
            throw exception(PAY_DEMO_ORDER_REFUND_FAIL_REFUND_NOT_SUCCESS);
        }
        // 2.3 校验退款金额一致
        if (notEqual(payRefund.getRefundAmount(), order.getPrice())) {
            log.error("[validateDemoOrderCanRefunded][order({}) payRefund({}) 退款金额不匹配，请进行处理！order 数据是：{}，payRefund 数据是：{}]",
                    id, payRefundId, toJsonString(order), toJsonString(payRefund));
            throw exception(PAY_DEMO_ORDER_REFUND_FAIL_REFUND_PRICE_NOT_MATCH);
        }
        // 2.4 校验退款订单匹配（二次）
        if (notEqual(payRefund.getMerchantOrderId(), id.toString())) {
            log.error("[validateDemoOrderCanRefunded][order({}) 退款单不匹配({})，请进行处理！payRefund 数据是：{}]",
                    id, payRefundId, toJsonString(payRefund));
            throw exception(PAY_DEMO_ORDER_REFUND_FAIL_REFUND_ORDER_ID_ERROR);
        }
        return payRefund;
    }

}