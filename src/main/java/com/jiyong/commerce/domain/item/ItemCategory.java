package com.jiyong.commerce.domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ItemCategory {
    private String categoryId;
    private String categoryName;
    private String upperCategoryId;
}
