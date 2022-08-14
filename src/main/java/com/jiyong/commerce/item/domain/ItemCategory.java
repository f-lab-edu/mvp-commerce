package com.jiyong.commerce.item.domain;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class ItemCategory {
    private Long categoryId;
    private String categoryName;
    private ItemCategory upperCategory;
}
