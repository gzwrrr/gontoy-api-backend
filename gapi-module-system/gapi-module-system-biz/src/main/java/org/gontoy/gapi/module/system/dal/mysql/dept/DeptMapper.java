package org.gontoy.gapi.module.system.dal.mysql.dept;

import org.gontoy.gapi.framework.mybatis.core.mapper.BaseMapperX;
import org.gontoy.gapi.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.gontoy.gapi.module.system.controller.admin.dept.vo.dept.DeptListReqVO;
import org.gontoy.gapi.module.system.dal.dataobject.dept.DeptDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper extends BaseMapperX<DeptDO> {

    default List<DeptDO> selectList(DeptListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DeptDO>()
                .likeIfPresent(DeptDO::getName, reqVO.getName())
                .eqIfPresent(DeptDO::getStatus, reqVO.getStatus()));
    }

    default DeptDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(DeptDO::getParentId, parentId, DeptDO::getName, name);
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(DeptDO::getParentId, parentId);
    }

}
