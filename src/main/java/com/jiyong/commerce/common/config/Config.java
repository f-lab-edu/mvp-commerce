package com.jiyong.commerce.common.config;

import com.jiyong.commerce.common.aop.AspectConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Predicate;

@Configuration
public class Config {
    @Bean
    public AspectConfig aspectConfig() {
        Predicate<Integer> predicate = (i)->{
            if (i == 50) {
                return true;
            }else {
                return false;
            }
        };
        return new AspectConfig(100,0,predicate);
    }
}
