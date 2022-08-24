package com.jiyong.commerce.item.repository;

import com.jiyong.commerce.common.exception.RetryLimitExceededException;
import com.jiyong.commerce.item.config.TestConfig;
import com.jiyong.commerce.item.domain.Item;
import com.jiyong.commerce.itemCategory.domain.ItemCategory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;


@Slf4j
@Import(TestConfig.class)
@SpringBootTest
class MemoryItemRepositoryAopExceptionTest {

    ItemCategory 가전 = ItemCategory.builder().id(1L).categoryName("가전").upperCategory(null).build();
    Item mockItem = Item.builder().itemCategory(가전).name("test").price(BigDecimal.valueOf(100)).stock(100L).build();
    @Autowired
    private ItemRepository mockRepository;

    @Test
    @DisplayName("mockRepository Exception Test ")
    public void mockRepositoryExceptionTest() {
        //given
        //when
        //then
        Assertions.assertThatThrownBy(() -> {
            mockRepository.save(mockItem);
        }).isInstanceOf(RetryLimitExceededException.class);
    }


}