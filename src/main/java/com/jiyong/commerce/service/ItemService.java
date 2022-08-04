package com.jiyong.commerce.service;

import com.jiyong.commerce.domain.item.Item;

import java.util.List;

public interface ItemService {

    Item saveItem(Item item);

    List<Item> itemList(Item item);

    List<Item> findByItemName(String name);
}
