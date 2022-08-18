package com.jiyong.commerce.item.config;


import com.jiyong.commerce.common.aop.MockDelayAndThrowExceptionAspect;
import com.jiyong.commerce.common.util.logtrace.LogTrace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @Bean
    public MockDelayAndThrowExceptionAspect testMockDelayAndThrowExceptionAspect(LogTrace logtrace) {
        return new MockDelayAndThrowExceptionAspect(logtrace, i -> true);
    }
}
