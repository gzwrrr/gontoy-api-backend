package org.gontoy.gapi.module.infra.dal.mysql.file;

import org.gontoy.gapi.framework.common.pojo.PageResult;
import org.gontoy.gapi.framework.mybatis.core.mapper.BaseMapperX;
import org.gontoy.gapi.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.gontoy.gapi.module.infra.controller.admin.file.vo.config.FileConfigPageReqVO;
import org.gontoy.gapi.module.infra.dal.dataobject.file.FileConfigDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileConfigMapper extends BaseMapperX<FileConfigDO> {

    default PageResult<FileConfigDO> selectPage(FileConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FileConfigDO>()
                .likeIfPresent(FileConfigDO::getName, reqVO.getName())
                .eqIfPresent(FileConfigDO::getStorage, reqVO.getStorage())
                .betweenIfPresent(FileConfigDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FileConfigDO::getId));
    }

}
