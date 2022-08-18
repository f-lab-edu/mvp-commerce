package com.jiyong.commerce.common.config;


import com.jiyong.commerce.common.aop.MockDelayAndThrowExceptionAspect;
import com.jiyong.commerce.common.util.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public MockDelayAndThrowExceptionAspect mockDelayAndThrowExceptionAspect(LogTrace logtrace) {
        return new MockDelayAndThrowExceptionAspect(logtrace, i -> i == 50);
    }
}
