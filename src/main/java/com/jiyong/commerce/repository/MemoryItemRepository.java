package com.jiyong.commerce.repository;

import com.jiyong.commerce.domain.item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MemoryItemRepository implements ItemRepository{

    private static Map<Long, Item> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    @Override
    public Item insertItem(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public List<Item> itemList() {
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
    public void deleteAll(){
        store.clear();
    }
}
