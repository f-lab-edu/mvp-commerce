package com.jiyong.commerce.item.repository;

import com.jiyong.commerce.item.domain.Item;
import com.jiyong.commerce.item.domain.ItemCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemoryItemRepositoryAopTest {

    @Autowired
    ItemRepository repository;

    ItemCategory 가전 = ItemCategory.builder().categoryId(1L).categoryName("가전").upperCategoryId("").build();
    ItemCategory 패션 = ItemCategory.builder().categoryId(2L).categoryName("패션").upperCategoryId("").build();
    ItemCategory 식품 = ItemCategory.builder().categoryId(3L).categoryName("식품").upperCategoryId("").build();

    @Test
    public void exceptionTest() {
        //given

        //when
        int count = 0;
        try {
            while(true) {
                count++;
                repository.itemList();
            }
        } catch (Exception e) {
            //then
            assertThat(count).isLessThan(1000);
        }
    };

    @Test
    public void timeDelayTest() {
        //given

        //when
        long start = System.currentTimeMillis();
        repository.itemList();
        long finish = System.currentTimeMillis();
        long timeMs = finish - start;

        //then
        assertThat(timeMs).isGreaterThanOrEqualTo(100L);
    }
}
