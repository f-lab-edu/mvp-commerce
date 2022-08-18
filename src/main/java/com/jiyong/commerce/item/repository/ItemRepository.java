package com.jiyong.commerce.item.repository;

import com.jiyong.commerce.item.domain.Item;

import java.util.List;

public interface ItemRepository {
    Item save(Item item);

    List<Item> findAll();

    List<Item> findByItemName(String name);

    void deleteAll();

    Item update(Item item);

    void delete(Long itemId);

    Item findById(Long itemId);

}
