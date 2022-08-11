package com.jiyong.commerce.item.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
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
}
