package com.jiyong.commerce.item.repository;

import com.jiyong.commerce.common.annotation.Retryable;
import com.jiyong.commerce.item.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@Retryable
public class MemoryItemRepository implements ItemRepository {

    private static Map<Long, Item> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;


    @Override
    public Item save(Item item) {
        item.setMockId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>(store.values());
        return list;
    }

    @Override
    public List<Item> findByItemName(String name) {
        return store.values().stream()
                .filter(item -> item.getName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        store.clear();
    }

    @Override

    public Item update(Item item) {
        delete(item.getId());
        store.put(item.getId(), item);
        return item;


    }

    @Override
    public void delete(Long itemId) {
        Long deleteItemId = store.entrySet().stream().parallel().filter(i -> i.getValue().getId().equals(itemId)).map(Map.Entry::getKey).findFirst().get();
        store.remove(deleteItemId);
    }

    @Override
    public Item findById(Long itemId) {
        Optional<Item> findItem = store.values().stream()
                .filter(item -> item.getId().equals(itemId)).findAny();
        return findItem.orElseThrow(() -> new NoSuchElementException("없는 상품"));
    }
}
