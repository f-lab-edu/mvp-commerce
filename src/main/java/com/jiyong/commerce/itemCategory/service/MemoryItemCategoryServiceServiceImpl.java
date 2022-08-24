package com.jiyong.commerce.itemCategory.service;

import com.jiyong.commerce.itemCategory.domain.ItemCategory;
import com.jiyong.commerce.itemCategory.repository.ItemCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemoryItemCategoryServiceServiceImpl implements ItemCategoryService {

    private final ItemCategoryRepository itemCategoryRepository;


    @Override
    public ItemCategory addItemCategory(ItemCategory itemCategory) {
        return itemCategoryRepository.save(itemCategory);
    }
}