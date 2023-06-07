package org.gontoy.gapi.module.pay.convert.merchant;

import java.util.*;

import org.gontoy.gapi.framework.common.pojo.PageResult;

import org.gontoy.gapi.module.pay.controller.admin.merchant.vo.merchant.PayMerchantCreateReqVO;
import org.gontoy.gapi.module.pay.controller.admin.merchant.vo.merchant.PayMerchantExcelVO;
import org.gontoy.gapi.module.pay.controller.admin.merchant.vo.merchant.PayMerchantRespVO;
import org.gontoy.gapi.module.pay.controller.admin.merchant.vo.merchant.PayMerchantUpdateReqVO;
import org.gontoy.gapi.module.pay.dal.dataobject.merchant.PayMerchantDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayMerchantConvert {

    PayMerchantConvert INSTANCE = Mappers.getMapper(PayMerchantConvert.class);

    PayMerchantDO convert(PayMerchantCreateReqVO bean);

    PayMerchantDO convert(PayMerchantUpdateReqVO bean);

    PayMerchantRespVO convert(PayMerchantDO bean);

    List<PayMerchantRespVO> convertList(List<PayMerchantDO> list);

    PageResult<PayMerchantRespVO> convertPage(PageResult<PayMerchantDO> page);

    List<PayMerchantExcelVO> convertList02(List<PayMerchantDO> list);

}
