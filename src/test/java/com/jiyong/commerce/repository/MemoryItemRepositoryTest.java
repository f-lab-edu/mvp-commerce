package com.jiyong.commerce.repository;

import com.jiyong.commerce.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryItemRepositoryTest {
    MemoryItemRepository repository = new MemoryItemRepository();

    @Test
    public void insertItem() {
        Item item = new Item();
        item.setName("핸드폰");
        repository.insertItem(item);
        Item result = repository.findByItemId(item.getId());
        Assertions.assertThat(result).isEqualTo(item);

    }

    @Test
    public void findByName() {
        Item item1 = new Item();
        item1.setName("핸드폰");
        repository.insertItem(item1);

        Item item2 = new Item();
        item2.setName("냉장고");
        repository.insertItem(item2);

        Item item3 = new Item();
        item3.setName("폴더핸드폰");
        repository.insertItem(item3);

        List<Item> list = repository.findByItemName("핸드폰");
        Assertions.assertThat(list.size()).isEqualTo(2);

    }
}
