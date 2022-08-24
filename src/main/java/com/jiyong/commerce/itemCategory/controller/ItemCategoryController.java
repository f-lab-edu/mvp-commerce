package com.jiyong.commerce.itemCategory.controller;

import com.jiyong.commerce.itemCategory.domain.ItemCategory;
import com.jiyong.commerce.itemCategory.dto.ItemCategoryDto;
import com.jiyong.commerce.itemCategory.service.ItemCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemCategories")
@RequiredArgsConstructor
@Slf4j
public class ItemCategoryController {

    private final ItemCategoryService itemCategoryService;

    @PutMapping
    public ResponseEntity<ItemCategoryDto.Response> itemAdd(@RequestBody ItemCategoryDto.Request itemCategoryDto) {


        log.info(" test itemCategoryDto = {} ", itemCategoryDto);
        ItemCategory saveItemCategory = itemCategoryService.addItemCategory(itemCategoryDto.toEntity());
        log.info("saveItemCategory = {} ", saveItemCategory);
        return ResponseEntity.ok(new ItemCategoryDto.Response(saveItemCategory));
    }


}
