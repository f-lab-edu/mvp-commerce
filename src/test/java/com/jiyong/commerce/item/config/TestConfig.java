package com.jiyong.commerce.item.config;

import com.jiyong.commerce.common.aop.TimeDelayAndThrowExceptionAop;
import org.springframework.context.annotation.Bean;

public class TestConfig {
    @Bean
    public TimeDelayAndThrowExceptionAop testTimeDelayAndThrowExceptionAop() {
        return new TimeDelayAndThrowExceptionAop(i -> true);
    }
}
