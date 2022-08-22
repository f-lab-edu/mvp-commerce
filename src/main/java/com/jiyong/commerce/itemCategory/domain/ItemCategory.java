package com.jiyong.commerce.itemCategory.domain;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class ItemCategory {
    private Long id;
    private String categoryName;
    private ItemCategory upperCategory;

    public void setMockId(Long categoryId) {
        this.id = categoryId;
    }

}
