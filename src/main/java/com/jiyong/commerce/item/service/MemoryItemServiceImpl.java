package com.jiyong.commerce.item.service;

import com.jiyong.commerce.item.domain.Item;
import com.jiyong.commerce.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoryItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Autowired
    public MemoryItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


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
