package com.jiyong.commerce.item.repository;

import com.jiyong.commerce.item.domain.Item;

import java.util.List;

public interface ItemRepository {
    Item insertItem(Item item);

    List<Item> itemList();

    List<Item> findByItemName(String name);

    void deleteAll();
}
