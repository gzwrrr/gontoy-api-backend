package org.gontoy.gapi.module.system.convert.logger;

import org.gontoy.gapi.framework.common.pojo.PageResult;
import org.gontoy.gapi.module.system.api.logger.dto.LoginLogCreateReqDTO;
import org.gontoy.gapi.module.system.controller.admin.logger.vo.loginlog.LoginLogExcelVO;
import org.gontoy.gapi.module.system.controller.admin.logger.vo.loginlog.LoginLogRespVO;
import org.gontoy.gapi.module.system.dal.dataobject.logger.LoginLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LoginLogConvert {

    LoginLogConvert INSTANCE = Mappers.getMapper(LoginLogConvert.class);

    PageResult<LoginLogRespVO> convertPage(PageResult<LoginLogDO> page);

    List<LoginLogExcelVO> convertList(List<LoginLogDO> list);

    LoginLogDO convert(LoginLogCreateReqDTO bean);

}
