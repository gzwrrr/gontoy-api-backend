package org.gontoy.gapi.framework.operatelog.config;

import org.gontoy.gapi.framework.operatelog.core.aop.OperateLogAspect;
import org.gontoy.gapi.framework.operatelog.core.service.OperateLogFrameworkService;
import org.gontoy.gapi.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import org.gontoy.gapi.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class GapiOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
