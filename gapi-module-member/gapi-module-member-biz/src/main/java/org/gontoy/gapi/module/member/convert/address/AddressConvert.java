package org.gontoy.gapi.module.member.convert.address;

import org.gontoy.gapi.framework.common.pojo.PageResult;
import org.gontoy.gapi.module.member.api.address.dto.AddressRespDTO;
import org.gontoy.gapi.module.member.controller.app.address.vo.AppAddressCreateReqVO;
import org.gontoy.gapi.module.member.controller.app.address.vo.AppAddressRespVO;
import org.gontoy.gapi.module.member.controller.app.address.vo.AppAddressUpdateReqVO;
import org.gontoy.gapi.module.member.dal.dataobject.address.AddressDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户收件地址 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface AddressConvert {

    AddressConvert INSTANCE = Mappers.getMapper(AddressConvert.class);

    AddressDO convert(AppAddressCreateReqVO bean);

    AddressDO convert(AppAddressUpdateReqVO bean);

    AppAddressRespVO convert(AddressDO bean);

    List<AppAddressRespVO> convertList(List<AddressDO> list);

    PageResult<AppAddressRespVO> convertPage(PageResult<AddressDO> page);

    AddressRespDTO convert02(AddressDO bean);

}
