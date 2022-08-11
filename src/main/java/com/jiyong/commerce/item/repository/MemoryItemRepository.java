package com.jiyong.commerce.item.repository;

import com.jiyong.commerce.item.domain.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MemoryItemRepository implements ItemRepository{

    private static Map<Long, Item> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    //ToDo
    /**
     * 성공률 99% (1% = IOException, SocketException, TimeoutException - RuntimeException의 하위, Retryable exponentially 인지 아닌지) AuthorizationException
     * (Math.random() 0 ~ 1) * 100 < 1
     * */

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
