package com.jiyong.commerce.common.config;

import com.jiyong.commerce.common.aop.TimeDelayAndThrowExceptionAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public TimeDelayAndThrowExceptionAop timeDelayAndThrowExceptionAop() {
        return new TimeDelayAndThrowExceptionAop(i -> i == 50);
    }
}
