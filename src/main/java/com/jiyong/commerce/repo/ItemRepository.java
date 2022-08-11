package com.jiyong.commerce.repo;

import com.jiyong.commerce.model.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> findByItemName(String itemName);

    Item saveItem(Item item);

    void deleteAll();

    List<Item> findAll();
}
