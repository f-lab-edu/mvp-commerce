package com.jiyong.commerce.domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Item {
    private Long id;
    private Long categoryId;
    private String name;
    private Long price;
    private Long stock;
}
