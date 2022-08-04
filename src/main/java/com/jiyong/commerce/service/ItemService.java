package com.jiyong.commerce.service;

import com.jiyong.commerce.model.Item;

import java.util.List;

public interface ItemService {
    Item saveItem(Item item);

    List<Item> searchItemByName(String name);
}
