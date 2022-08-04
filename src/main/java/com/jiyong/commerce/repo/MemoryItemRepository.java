package com.jiyong.commerce.repo;

import com.jiyong.commerce.model.Item;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryItemRepository implements ItemRepository {
    static long index = 0;
    Map<Long, Item> repo = new HashMap<>();

    @Override
    public List<Item> findByItemName(String itemName) {
        Collection<Item> values = repo.values();
        return values.parallelStream().filter(i -> i.getItemName().contains(itemName)).collect(Collectors.toList());
    }

    @Override
    public Item saveItem(Item item) {
        return repo.put(index++, item);
    }

    @Override
    public void deleteAll() {
        repo.clear();
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(repo.values());
    }
}
