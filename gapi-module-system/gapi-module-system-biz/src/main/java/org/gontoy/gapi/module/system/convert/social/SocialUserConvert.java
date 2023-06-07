package org.gontoy.gapi.module.system.convert.social;

import org.gontoy.gapi.module.system.api.social.dto.SocialUserBindReqDTO;
import org.gontoy.gapi.module.system.api.social.dto.SocialUserUnbindReqDTO;
import org.gontoy.gapi.module.system.controller.admin.socail.vo.SocialUserBindReqVO;
import org.gontoy.gapi.module.system.controller.admin.socail.vo.SocialUserUnbindReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SocialUserConvert {

    SocialUserConvert INSTANCE = Mappers.getMapper(SocialUserConvert.class);

    SocialUserBindReqDTO convert(Long userId, Integer userType, SocialUserBindReqVO reqVO);

    SocialUserUnbindReqDTO convert(Long userId, Integer userType, SocialUserUnbindReqVO reqVO);

}
