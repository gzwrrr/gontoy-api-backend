package org.gontoy.gapi.module.mp.dal.mysql.account;

import org.gontoy.gapi.framework.common.pojo.PageResult;
import org.gontoy.gapi.framework.mybatis.core.mapper.BaseMapperX;
import org.gontoy.gapi.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.gontoy.gapi.module.mp.controller.admin.account.vo.MpAccountPageReqVO;
import org.gontoy.gapi.module.mp.dal.dataobject.account.MpAccountDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MpAccountMapper extends BaseMapperX<MpAccountDO> {

    default PageResult<MpAccountDO> selectPage(MpAccountPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MpAccountDO>()
                .likeIfPresent(MpAccountDO::getName, reqVO.getName())
                .likeIfPresent(MpAccountDO::getAccount, reqVO.getAccount())
                .likeIfPresent(MpAccountDO::getAppId, reqVO.getAppId())
                .orderByDesc(MpAccountDO::getId));
    }

    default MpAccountDO selectByAppId(String appId) {
        return selectOne(MpAccountDO::getAppId, appId);
    }

}
