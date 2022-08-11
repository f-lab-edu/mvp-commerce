package com.jiyong.commerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * database Entity
 * enum을 사용하면?
 */
@Data
@AllArgsConstructor
public class ItemCategory {
    Integer categoryId;
    String categoryName;
    ItemCategory parentCategory;
}
