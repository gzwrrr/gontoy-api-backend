package org.gontoy.gapi.module.system.convert.notify;

import java.util.*;

import org.gontoy.gapi.framework.common.pojo.PageResult;

import org.gontoy.gapi.module.system.controller.admin.notify.vo.template.NotifyTemplateCreateReqVO;
import org.gontoy.gapi.module.system.controller.admin.notify.vo.template.NotifyTemplateRespVO;
import org.gontoy.gapi.module.system.controller.admin.notify.vo.template.NotifyTemplateUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.gontoy.gapi.module.system.dal.dataobject.notify.NotifyTemplateDO;

/**
 * 站内信模版 Convert
 *
 * @author xrcoder
 */
@Mapper
public interface NotifyTemplateConvert {

    NotifyTemplateConvert INSTANCE = Mappers.getMapper(NotifyTemplateConvert.class);

    NotifyTemplateDO convert(NotifyTemplateCreateReqVO bean);

    NotifyTemplateDO convert(NotifyTemplateUpdateReqVO bean);

    NotifyTemplateRespVO convert(NotifyTemplateDO bean);

    List<NotifyTemplateRespVO> convertList(List<NotifyTemplateDO> list);

    PageResult<NotifyTemplateRespVO> convertPage(PageResult<NotifyTemplateDO> page);

}
