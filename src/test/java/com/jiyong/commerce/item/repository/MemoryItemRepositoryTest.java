package com.jiyong.commerce.item.repository;

import com.jiyong.commerce.item.domain.Item;
import com.jiyong.commerce.itemCategory.domain.ItemCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Slf4j
class MemoryItemRepositoryTest {

    @Autowired
    ItemRepository repository;

    ItemCategory 가전 = ItemCategory.builder().id(1L).categoryName("가전").upperCategory(null).build();
    ItemCategory 패션 = ItemCategory.builder().id(2L).categoryName("패션").upperCategory(null).build();
    ItemCategory 식품 = ItemCategory.builder().id(3L).categoryName("식품").upperCategory(null).build();
    Item mockItem = Item.builder().itemCategory(가전).name("test").price(BigDecimal.valueOf(100)).stock(100L).build();


    @AfterEach
    public void deleteAll() {
        repository.deleteAll();
    }

    @Test
    public void insertItem() {
        //given
        Item 아이폰13 = Item.builder().itemCategory(가전).name("아이폰13").price(BigDecimal.valueOf(1300000L)).stock(100L).build();
        Item 나이키반팔 = Item.builder().itemCategory(패션).name("나이키반팔").price(BigDecimal.valueOf(50000L)).stock(100L).build();
        Item 닭가슴살 = Item.builder().itemCategory(식품).name("닭가슴살").price(BigDecimal.valueOf(1300L)).stock(100L).build();

        //when
        repository.save(아이폰13);
        repository.save(나이키반팔);
        repository.save(닭가슴살);

        //then
        assertThat(repository.findAll().size()).isEqualTo(3);
        assertThat(repository.findAll()).contains(아이폰13, 나이키반팔, 닭가슴살);

    }

    @Test
    public void findByName() {
        //given
        Item 아이폰13 = Item.builder().itemCategory(가전).name("아이폰13").price(BigDecimal.valueOf(1300000L)).stock(100L).build();
        Item 나이키반팔 = Item.builder().itemCategory(패션).name("나이키반팔").price(BigDecimal.valueOf(50000L)).stock(100L).build();
        Item 닭가슴살 = Item.builder().itemCategory(식품).name("닭가슴살").price(BigDecimal.valueOf(1300L)).stock(100L).build();
        Item 닭안심 = Item.builder().itemCategory(식품).name("닭안심").price(BigDecimal.valueOf(1300L)).stock(100L).build();
        repository.save(아이폰13);
        repository.save(나이키반팔);
        repository.save(닭가슴살);
        repository.save(닭안심);

        //when
        List<Item> list = repository.findByItemName("닭");

        //then
        assertThat(list.size()).isEqualTo(2);
        assertThat(list).contains(닭가슴살, 닭안심);
    }


    //ToDo delay test

    @Test
    @DisplayName("Aop 프록시 객체로 사용되는지 테스트")
    public void mockRepositoryAopConvertTest() {
        //given
        //when
        log.info("repository = {} ", repository.getClass());
        boolean jdkDynamicProxy = AopUtils.isJdkDynamicProxy(repository);
        log.info("jdkDynamicProxy = {} ", jdkDynamicProxy);
        boolean aopProxy = AopUtils.isAopProxy(repository);
        log.info("aopProxy = {} ", aopProxy);
        boolean cglibProxy = AopUtils.isCglibProxy(repository);
        log.info("cglibProxy = {} ", cglibProxy);
        //then
        assertThat(jdkDynamicProxy).isFalse();
        assertThat(aopProxy).isTrue();
        assertThat(cglibProxy).isTrue();
    }

    @Test
    @DisplayName("mockRepository Delay테스트")
    public void mockRepositoryDelayTest() throws Exception {
        //given
        //when
        //then
        repository.save(mockItem);
        repository.findAll();
        repository.deleteAll();
        repository.findByItemName("test");
    }


}