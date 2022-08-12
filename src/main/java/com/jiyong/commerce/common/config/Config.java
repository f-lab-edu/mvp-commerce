package com.jiyong.commerce.common.config;


import com.jiyong.commerce.common.aop.MockDelayAndThrowExceptionAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public MockDelayAndThrowExceptionAspect mockDelayAndThrowExceptionAspect() {
        return new MockDelayAndThrowExceptionAspect(i -> i == 50);
    }
}
