package com.jiyong.commerce.item.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jiyong.commerce.item.domain.Item;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;


public class ItemDto {


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Getter
    public static class Request {
        @NotBlank
        private Long id;
        @NotBlank
        private ItemCategoryDto.Request itemCategoryDto;

        @Length(max = 30, min = 1)
        @NotBlank
        private String name;

        @DecimalMin("1")
        @DecimalMax("999999999999")
        @NotBlank
        private BigDecimal price;

        @Max(999999999)
        @Min(1)
        @NotBlank
        private Long stock;

        public Item toEntity() {
            return Item.builder()
                    .itemCategory(itemCategoryDto.toEntity())
                    .price(price)
                    .stock(stock)
                    .name(name)
                    .id(id)
                    .build();
        }
    }


    @ToString(callSuper = true)
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response extends SimpleResponse {
        private ItemCategoryDto.Response itemCategoryDto;
        private Long stock;

        public Response(Item item) {
            super(item.getId(), item.getName(), item.getPrice());
            this.stock = item.getStock();
            this.itemCategoryDto = new ItemCategoryDto.Response(item.getItemCategory());
        }
    }

    @ToString
    @Getter
    public static class SimpleResponse {
        private Long id;
        private ItemCategoryDto.SimpleResponse simpleItemCategoryDto;
        private String name;
        private BigDecimal price;

        public SimpleResponse(Long id, String name, BigDecimal price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public SimpleResponse(Item item) {
            this.id = item.getId();
            this.name = item.getName();
            this.price = item.getPrice();
            this.simpleItemCategoryDto = new ItemCategoryDto.SimpleResponse(item.getItemCategory());
        }
    }


}
