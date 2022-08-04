package com.jiyong.commerce.service;

import com.jiyong.commerce.domain.item.Item;
import com.jiyong.commerce.repository.ItemRepository;
import com.jiyong.commerce.repository.MemoryItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository = new MemoryItemRepository();

    @Override
    public Item saveItem(Item item) {
        return itemRepository.insertItem(item);
    }

    @Override
    public List<Item> itemList(Item item) {
        return itemRepository.itemList();
    }

    @Override
    public List<Item> findByItemName(String name) {
        return itemRepository.findByItemName(name);
    }
}
