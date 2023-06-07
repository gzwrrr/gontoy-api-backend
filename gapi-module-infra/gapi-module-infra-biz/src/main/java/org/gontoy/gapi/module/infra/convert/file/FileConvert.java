package org.gontoy.gapi.module.infra.convert.file;

import org.gontoy.gapi.framework.common.pojo.PageResult;
import org.gontoy.gapi.module.infra.controller.admin.file.vo.file.FileRespVO;
import org.gontoy.gapi.module.infra.dal.dataobject.file.FileDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileConvert {

    FileConvert INSTANCE = Mappers.getMapper(FileConvert.class);

    FileRespVO convert(FileDO bean);

    PageResult<FileRespVO> convertPage(PageResult<FileDO> page);

}
