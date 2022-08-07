package com.jiyong.commerce.repository;

import com.jiyong.commerce.domain.item.Item;

import java.util.List;

public interface ItemRepository {
    Item insertItem(Item item);

    List<Item> itemList();

    List<Item> findByItemName(String name);

    void deleteAll();
}
