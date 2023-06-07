package org.gontoy.gapi.module.system.convert.notice;

import org.gontoy.gapi.framework.common.pojo.PageResult;
import org.gontoy.gapi.module.system.controller.admin.notice.vo.NoticeCreateReqVO;
import org.gontoy.gapi.module.system.controller.admin.notice.vo.NoticeRespVO;
import org.gontoy.gapi.module.system.controller.admin.notice.vo.NoticeUpdateReqVO;
import org.gontoy.gapi.module.system.dal.dataobject.notice.NoticeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoticeConvert {

    NoticeConvert INSTANCE = Mappers.getMapper(NoticeConvert.class);

    PageResult<NoticeRespVO> convertPage(PageResult<NoticeDO> page);

    NoticeRespVO convert(NoticeDO bean);

    NoticeDO convert(NoticeUpdateReqVO bean);

    NoticeDO convert(NoticeCreateReqVO bean);

}
