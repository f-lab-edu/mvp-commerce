package com.jiyong.commerce.itemCategory.repository;

import com.jiyong.commerce.common.annotation.Retryable;
import com.jiyong.commerce.itemCategory.domain.ItemCategory;
import com.jiyong.commerce.itemCategory.exception.NoSuchParentCategoryException;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Retryable
public class MemoryItemCategoryRepository implements ItemCategoryRepository {

    private static Map<Long, ItemCategory> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;


    @Override
    public ItemCategory save(ItemCategory itemCategory) throws NoSuchParentCategoryException {
        ItemCategory prevCategory = itemCategory;
        if (prevCategory.getUpperCategory() != null) {
            prevCategory = prevCategory.getUpperCategory();
            while (prevCategory != null) {
                if (!store.containsKey(prevCategory.getId())) {
                    throw new NoSuchParentCategoryException("해당 상위 카테고리가 없습니다.");
                }
                prevCategory = prevCategory.getUpperCategory();
            }
        }

        itemCategory.setMockId(++sequence);
        store.put(itemCategory.getId(), itemCategory);
        return itemCategory;
    }


}
