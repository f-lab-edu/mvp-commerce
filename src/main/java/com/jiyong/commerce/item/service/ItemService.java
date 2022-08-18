package com.jiyong.commerce.item.service;

import com.jiyong.commerce.item.domain.Item;

import java.util.List;

public interface ItemService {

    Item addItem(Item item);

    List<Item> getItems();

    List<Item> searchByName(String name);

    Item modifyItem(Long itemId, Item newItem);

    Item getItem(Long itemId);

    void removeItem(Long itemId);


}
