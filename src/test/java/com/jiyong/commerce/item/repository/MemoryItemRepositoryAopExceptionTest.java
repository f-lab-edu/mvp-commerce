package com.jiyong.commerce.item.repository;

import com.jiyong.commerce.item.config.TestConfig;
import com.jiyong.commerce.item.domain.Item;
import com.jiyong.commerce.item.domain.ItemCategory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Import({TestConfig.class})
@Slf4j
class MemoryItemRepositoryAopExceptionTest {

    @Autowired
    ItemRepository repository;

    ItemCategory 가전 = ItemCategory.builder().categoryId(1L).categoryName("가전").upperCategoryId("").build();

    Item mockItem = Item.builder().itemCategory(가전).name("test").price(BigDecimal.valueOf(100)).stock(100L).build();


    @Test
    @DisplayName("mockRepository Exception Test ")
    public void mockRepositoryExceptionTest() {
        //given
        //when
        //then
        Assertions.assertThatThrownBy(() ->{
            repository.insertItem(mockItem);
        }).isInstanceOf(Exception.class);

    }








}