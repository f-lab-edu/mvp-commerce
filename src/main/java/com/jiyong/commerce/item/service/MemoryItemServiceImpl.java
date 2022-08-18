package com.jiyong.commerce.item.service;

import com.jiyong.commerce.item.domain.Item;
import com.jiyong.commerce.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemoryItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> searchByName(String name) {
        return itemRepository.findByItemName(name);
    }

    @Override
    public Item modifyItem(Long itemId, Item newItem) {
        Item findItem = getItem(itemId);
        findItem.update(newItem);
        return itemRepository.update(findItem);
    }

    @Override
    public Item getItem(Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public void removeItem(Long itemId) {
        itemRepository.delete(itemId);
    }


}
