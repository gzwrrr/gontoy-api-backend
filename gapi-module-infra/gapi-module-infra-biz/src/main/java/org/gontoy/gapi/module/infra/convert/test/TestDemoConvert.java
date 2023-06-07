package org.gontoy.gapi.module.infra.convert.test;

import org.gontoy.gapi.framework.common.pojo.PageResult;
import org.gontoy.gapi.module.infra.controller.admin.test.vo.TestDemoCreateReqVO;
import org.gontoy.gapi.module.infra.controller.admin.test.vo.TestDemoExcelVO;
import org.gontoy.gapi.module.infra.controller.admin.test.vo.TestDemoRespVO;
import org.gontoy.gapi.module.infra.controller.admin.test.vo.TestDemoUpdateReqVO;
import org.gontoy.gapi.module.infra.dal.dataobject.test.TestDemoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典类型 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface TestDemoConvert {

    TestDemoConvert INSTANCE = Mappers.getMapper(TestDemoConvert.class);

    TestDemoDO convert(TestDemoCreateReqVO bean);

    TestDemoDO convert(TestDemoUpdateReqVO bean);

    TestDemoRespVO convert(TestDemoDO bean);

    List<TestDemoRespVO> convertList(List<TestDemoDO> list);

    PageResult<TestDemoRespVO> convertPage(PageResult<TestDemoDO> page);

    List<TestDemoExcelVO> convertList02(List<TestDemoDO> list);

}
