package com.jiyong.commerce.item.domain;

import com.jiyong.commerce.item.exception.OutOfStockException;
import com.jiyong.commerce.itemCategory.domain.ItemCategory;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class Item {
    private Long id;
    private ItemCategory itemCategory;
    private String name;
    private BigDecimal price;
    //재고
    private Long stock;

    public void setMockId(Long id) {
        this.id = id;
    }

    public void update(Item newItem) {
        this.itemCategory = newItem.itemCategory;
        this.name = newItem.name;
        this.price = newItem.price;
        if (newItem.stock < 0) {
            throw new OutOfStockException("상품의 개수는 0개 이하가 될수없습니다");
        }
        this.stock = newItem.stock;
    }
}
