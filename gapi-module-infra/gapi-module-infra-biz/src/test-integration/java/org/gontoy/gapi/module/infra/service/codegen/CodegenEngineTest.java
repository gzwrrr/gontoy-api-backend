package org.gontoy.gapi.module.infra.service.codegen;

import org.gontoy.gapi.module.infra.dal.dataobject.codegen.CodegenColumnDO;
import org.gontoy.gapi.module.infra.dal.dataobject.codegen.CodegenTableDO;
import org.gontoy.gapi.module.infra.dal.mysql.codegen.CodegenColumnMapper;
import org.gontoy.gapi.module.infra.dal.mysql.codegen.CodegenTableMapper;
import org.gontoy.gapi.module.infra.service.codegen.inner.CodegenEngine;
import org.gontoy.gapi.module.infra.test.BaseDbUnitTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class CodegenEngineTest extends BaseDbUnitTest {

    @Resource
    private CodegenTableMapper codegenTableMapper;
    @Resource
    private CodegenColumnMapper codegenColumnMapper;

    @Resource
    private CodegenEngine codegenEngine;

    @Test
    public void testExecute() {
        CodegenTableDO table = codegenTableMapper.selectById(20);
        List<CodegenColumnDO> columns = codegenColumnMapper.selectListByTableId(table.getId());
        Map<String, String> result = codegenEngine.execute(table, columns);
        result.forEach((s, s2) -> System.out.println(s2));
//        System.out.println(result.get("vue/views/system/test/index.vue"));
    }

}
