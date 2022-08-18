package com.jiyong.commerce.item.repository;

import com.jiyong.commerce.common.exception.RetryLimitExceededException;
import com.jiyong.commerce.item.config.TestConfig;
import com.jiyong.commerce.item.domain.Item;
import com.jiyong.commerce.item.domain.ItemCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Import(TestConfig.class)
public class MemoryItemRepositoryAopTest {

    @Autowired
    ItemRepository repository;

    @Test
    public void exceptionTest() {
        //given
        //when

        //then
        Assertions.assertThatThrownBy(() -> {
            repository.itemList();
        }).isInstanceOf(RetryLimitExceededException.class);
    };
}
