package com.jiyong.commerce.item.config;

import com.jiyong.commerce.common.aop.AspectConfig;
import com.jiyong.commerce.item.domain.Item;
import com.jiyong.commerce.item.repository.ItemRepository;
import com.jiyong.commerce.item.repository.MemoryItemRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.function.Predicate;

@TestConfiguration
public class TestConfig {
    @Bean
    public AspectConfig testAspect() {
        Predicate<Integer> predicate = (i)->true;
        return new AspectConfig(100,0,predicate);
    }
}
