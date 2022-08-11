package com.jiyong.commerce.item.service;

import com.jiyong.commerce.item.domain.Item;

import java.util.List;

public interface ItemService {

    Item saveItem(Item item);

    List<Item> itemList(Item item);

    List<Item> findByItemName(String name);
}
