package org.gontoy.gapi.module.member.api.address;

import org.gontoy.gapi.module.member.api.address.dto.AddressRespDTO;
import org.gontoy.gapi.module.member.convert.address.AddressConvert;
import org.gontoy.gapi.module.member.service.address.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 用户收件地址 API 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AddressApiImpl implements AddressApi {

    @Resource
    private AddressService addressService;

    @Override
    public AddressRespDTO getAddress(Long id, Long userId) {
        return AddressConvert.INSTANCE.convert02(addressService.getAddress(userId, id));
    }

}
