package com.jiyong.commerce.repository;

import com.jiyong.commerce.domain.item.Item;
import com.jiyong.commerce.domain.item.ItemCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryItemRepositoryTest {
    MemoryItemRepository repository = new MemoryItemRepository();

    ItemCategory 가전 = ItemCategory.builder().categoryId(1L).categoryName("가전").upperCategoryId("").build();
    ItemCategory 패션 = ItemCategory.builder().categoryId(2L).categoryName("패션").upperCategoryId("").build();
    ItemCategory 식품 = ItemCategory.builder().categoryId(3L).categoryName("식품").upperCategoryId("").build();

    @AfterEach
    public void deleteAll() {
        repository.deleteAll();
    }

    @Test
    public void insertItem() {
        //given
        Item 아이폰13 = Item.builder().itemCategory(가전).name("아이폰13").price(1300000L).stock(100L).build();
        Item 나이키반팔 = Item.builder().itemCategory(패션).name("나이키반팔").price(50000L).stock(100L).build();
        Item 닭가슴살 = Item.builder().itemCategory(식품).name("닭가슴살").price(1300L).stock(100L).build();

        //when
        repository.insertItem(아이폰13);
        repository.insertItem(나이키반팔);
        repository.insertItem(닭가슴살);

        //then
        assertThat(repository.itemList().size()).isEqualTo(3);
        assertThat(repository.itemList()).contains(아이폰13, 나이키반팔, 닭가슴살);

    }

    @Test
    public void findByName() {
        //given
        Item 아이폰13 = Item.builder().itemCategory(가전).name("아이폰13").price(1300000L).stock(100L).build();
        Item 나이키반팔 = Item.builder().itemCategory(패션).name("나이키반팔").price(50000L).stock(100L).build();
        Item 닭가슴살 = Item.builder().itemCategory(식품).name("닭가슴살").price(1300L).stock(100L).build();
        Item 닭안심 = Item.builder().itemCategory(식품).name("닭안심").price(1300L).stock(100L).build();
        repository.insertItem(아이폰13);
        repository.insertItem(나이키반팔);
        repository.insertItem(닭가슴살);
        repository.insertItem(닭안심);

        //when
        List<Item> list = repository.findByItemName("닭");

        //then
        assertThat(list.size()).isEqualTo(2);
        assertThat(list).contains(닭가슴살, 닭안심);

    }
}
