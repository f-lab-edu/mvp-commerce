package com.jiyong.commerce.item.config;


import com.jiyong.commerce.common.aop.MockDelayAndThrowExceptionAspect;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @Bean
    public MockDelayAndThrowExceptionAspect testMockDelayAndThrowExceptionAspect() {
        return new MockDelayAndThrowExceptionAspect(i -> true);
    }

}
