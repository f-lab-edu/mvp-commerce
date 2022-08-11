package com.jiyong.commerce.service;

import com.jiyong.commerce.model.Item;
import com.jiyong.commerce.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class itemServiceImpl implements ItemService {

    final private ItemRepository itemRepository;

    @Override
    public Item saveItem(Item item) {
        return itemRepository.saveItem(item);
    }

    @Override
    public List<Item> searchItemByName(String name) {
        return itemRepository.findByItemName(name);
    }
}
