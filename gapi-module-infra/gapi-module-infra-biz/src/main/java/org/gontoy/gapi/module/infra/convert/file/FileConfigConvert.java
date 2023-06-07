package org.gontoy.gapi.module.infra.convert.file;

import org.gontoy.gapi.framework.common.pojo.PageResult;
import org.gontoy.gapi.module.infra.controller.admin.file.vo.config.FileConfigCreateReqVO;
import org.gontoy.gapi.module.infra.controller.admin.file.vo.config.FileConfigRespVO;
import org.gontoy.gapi.module.infra.controller.admin.file.vo.config.FileConfigUpdateReqVO;
import org.gontoy.gapi.module.infra.dal.dataobject.file.FileConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文件配置 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface FileConfigConvert {

    FileConfigConvert INSTANCE = Mappers.getMapper(FileConfigConvert.class);

    @Mapping(target = "config", ignore = true)
    FileConfigDO convert(FileConfigCreateReqVO bean);

    @Mapping(target = "config", ignore = true)
    FileConfigDO convert(FileConfigUpdateReqVO bean);

    FileConfigRespVO convert(FileConfigDO bean);

    List<FileConfigRespVO> convertList(List<FileConfigDO> list);

    PageResult<FileConfigRespVO> convertPage(PageResult<FileConfigDO> page);

}
