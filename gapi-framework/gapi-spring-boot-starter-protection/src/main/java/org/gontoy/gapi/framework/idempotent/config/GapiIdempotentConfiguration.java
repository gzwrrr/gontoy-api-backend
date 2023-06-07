package org.gontoy.gapi.framework.idempotent.config;

import org.gontoy.gapi.framework.idempotent.core.aop.IdempotentAspect;
import org.gontoy.gapi.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import org.gontoy.gapi.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import org.gontoy.gapi.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import org.gontoy.gapi.framework.idempotent.core.redis.IdempotentRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.gontoy.gapi.framework.redis.config.GapiRedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@AutoConfiguration(after = GapiRedisAutoConfiguration.class)
public class GapiIdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
