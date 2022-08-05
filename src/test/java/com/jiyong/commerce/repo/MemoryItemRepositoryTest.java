package com.jiyong.commerce.repo;

import com.jiyong.commerce.model.Item;
import com.jiyong.commerce.model.ItemCategory;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemoryItemRepositoryTest {

    /**
     * 카테고리 가정
     */
    final ItemCategory 가전 = new ItemCategory(001, "가전", null);
    final ItemCategory 가구 = new ItemCategory(002, "가구", null);
    final ItemCategory 식품 = new ItemCategory(003, "식품", null);
    final ItemCategory 축산물 = new ItemCategory(004, "축산물", 가전);
    final ItemCategory TV = new ItemCategory(005, "TV", 식품);
    final ItemCategory 식탁 = new ItemCategory(005, "TV", 가구);
    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    public void setUp() {
        Item 삼성QLED48인치TV = Item.builder().itemId(1L).itemCategory(TV).itemName("삼성QLED48인치TV").price(BigDecimal.valueOf(2_000_000)).build();
        Item 한돈삼겹살2kg = Item.builder().itemId(2L).itemCategory(축산물).itemName("한돈삼겹살2kg").price(BigDecimal.valueOf(50_000)).build();
        Item 한돈항정살2kg = Item.builder().itemId(3L).itemCategory(축산물).itemName("한돈항정살2kg").price(BigDecimal.valueOf(70_000)).build();
        itemRepository.saveItem(삼성QLED48인치TV);
        itemRepository.saveItem(한돈삼겹살2kg);
        itemRepository.saveItem(한돈항정살2kg);
    }

    @AfterEach
    public void after() {
        itemRepository.deleteAll();
    }

    @Test
    @DisplayName("한돈 키워드를 조회하는 테스트")
    public void findByItemName() throws Exception {
        //given
        //when
        List<Item> findItemList = itemRepository.findByItemName("한돈");
        //then
        assertThat(findItemList.size()).isEqualTo(2);
        assertThat(findItemList).extracting("itemName")
                .containsExactly("한돈삼겹살2kg", "한돈항정살2kg");
    }

    @Test
    @DisplayName("아이템 저장 테스트")
    public void saveItem() throws Exception {
        //given
        itemRepository.deleteAll();
        Item testItem1 = Item.builder().itemId(1L).itemCategory(TV).itemName("item1").price(BigDecimal.valueOf(1_000_000)).build();
        Item testItem2 = Item.builder().itemId(2L).itemCategory(축산물).itemName("item2").price(BigDecimal.valueOf(20_000)).build();
        //when
        itemRepository.saveItem(testItem1);
        itemRepository.saveItem(testItem2);
        //then
        List<Item> all = itemRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        Tuple item1 = new Tuple("item1", BigDecimal.valueOf(1_000_000));
        Tuple item2 = new Tuple("item2", BigDecimal.valueOf(20_000));
        assertThat(all).extracting("itemName", "price")
                .containsExactly(item1, item2);
    }

}