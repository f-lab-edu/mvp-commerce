package com.jiyong.commerce.itemcategory.cucumbertest;

import com.jiyong.commerce.itemCategory.dto.ItemCategoryDto;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class ItemCategoryServiceFeatureTest extends SpringItemCategoryApi {
    @Before
    public void setUp() {
        initMockMvc();
    }

    @Given("카테고리 저장하기")
    public void 카테고리저장하기(List<ItemCategoryDto.Request> itemCategory) throws Exception {
        for (ItemCategoryDto.Request category : itemCategory) {
            itemCategorySaveApiTest(category);
        }
    }

    @DataTableType
    public ItemCategoryDto.Request itemCategory(Map<String, String> entry) {
        ItemCategoryDto.Request itemCategory = null;
        if (entry.get("id") != null) {
            itemCategory = new ItemCategoryDto.Request(Long.valueOf(entry.get("id")), entry.get("categoryName"), null);
        }
        itemCategory = new ItemCategoryDto.Request(null, entry.get("categoryName"), null);

        String field = entry.get("upperCategory");
        if (field != null) {
            String[] upperCategories = field.split(" ");
            ItemCategoryDto.Request firstCategory = null;
            ItemCategoryDto.Request prevCategory = null;
            for (int j = 0; j < upperCategories.length; j++) {
                String[] split = upperCategories[j].split(",");
                if (firstCategory == null) {
                    prevCategory = new ItemCategoryDto.Request(Long.valueOf(split[1]), split[0], null);
                    itemCategory.addUpperCategory(prevCategory);
                    firstCategory = prevCategory;
                } else {
                    ItemCategoryDto.Request temp = new ItemCategoryDto.Request(Long.valueOf(split[1]), split[0], null);
                    prevCategory.addUpperCategory(temp);
                    prevCategory = temp;
                }
            }
            itemCategory.addUpperCategory(firstCategory);
        }

        return itemCategory;
    }


}
