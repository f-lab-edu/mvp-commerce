package com.jiyong.commerce.item.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jiyong.commerce.item.domain.ItemCategory;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


public class ItemCategoryDto {
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Getter
    public static class Request {
        @NotBlank
        private Long id;
        @NotBlank
        @Length(min = 1, max = 100)
        private String categoryName;

        private ItemCategoryDto.Request upperCategory;

        public ItemCategory toEntity() {
            if (upperCategory == null) {
                return ItemCategory.builder().categoryName(categoryName).upperCategory(null).categoryId(id).build();
            } else {
                return ItemCategory.builder().categoryName(categoryName).upperCategory(upperCategory.toEntity()).categoryId(id).build();
            }
        }

    }


    @ToString(callSuper = true)
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response extends SimpleResponse {
        private ItemCategoryDto.Response upperCategory;

        public Response(ItemCategory itemCategory) {
            super(itemCategory.getCategoryName());
            if (itemCategory.getUpperCategory() == null) {
                this.upperCategory = null;
            } else {
                this.upperCategory = new ItemCategoryDto.Response(itemCategory.getUpperCategory());
            }
        }
    }

    @Getter
    @ToString
    public static class SimpleResponse {
        private String categoryName;

        public SimpleResponse(String categoryName) {
            this.categoryName = categoryName;
        }

        public SimpleResponse(ItemCategory itemCategory) {
            this.categoryName = itemCategory.getCategoryName();
        }
    }

}
