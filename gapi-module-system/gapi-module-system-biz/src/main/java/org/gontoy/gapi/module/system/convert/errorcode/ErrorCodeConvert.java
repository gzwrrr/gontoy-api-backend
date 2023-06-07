package org.gontoy.gapi.module.system.convert.errorcode;

import org.gontoy.gapi.framework.common.pojo.PageResult;
import org.gontoy.gapi.module.system.api.errorcode.dto.ErrorCodeAutoGenerateReqDTO;
import org.gontoy.gapi.module.system.api.errorcode.dto.ErrorCodeRespDTO;
import org.gontoy.gapi.module.system.controller.admin.errorcode.vo.ErrorCodeCreateReqVO;
import org.gontoy.gapi.module.system.controller.admin.errorcode.vo.ErrorCodeExcelVO;
import org.gontoy.gapi.module.system.controller.admin.errorcode.vo.ErrorCodeRespVO;
import org.gontoy.gapi.module.system.controller.admin.errorcode.vo.ErrorCodeUpdateReqVO;
import org.gontoy.gapi.module.system.dal.dataobject.errorcode.ErrorCodeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 错误码 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ErrorCodeConvert {

    ErrorCodeConvert INSTANCE = Mappers.getMapper(ErrorCodeConvert.class);

    ErrorCodeDO convert(ErrorCodeCreateReqVO bean);

    ErrorCodeDO convert(ErrorCodeUpdateReqVO bean);

    ErrorCodeRespVO convert(ErrorCodeDO bean);

    List<ErrorCodeRespVO> convertList(List<ErrorCodeDO> list);

    PageResult<ErrorCodeRespVO> convertPage(PageResult<ErrorCodeDO> page);

    List<ErrorCodeExcelVO> convertList02(List<ErrorCodeDO> list);

    ErrorCodeDO convert(ErrorCodeAutoGenerateReqDTO bean);

    List<ErrorCodeRespDTO> convertList03(List<ErrorCodeDO> list);

}
