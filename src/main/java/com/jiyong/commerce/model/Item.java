package com.jiyong.commerce.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
    Long itemId;
    String itemName;
    ItemCategory itemCategory;
    BigDecimal price;
}
