package com.jiyong.commerce.itemcategory.cucumbertest;

import com.google.common.base.Strings;
import com.jiyong.commerce.itemCategory.dto.ItemCategoryDto;
import io.cucumber.messages.internal.com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@Slf4j
public class SpringItemCategoryApi {

    @Autowired
    WebApplicationContext context;
    MockMvc mvc;


    @BeforeEach
    public void initMockMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    @DisplayName(" 카테고리 추가 api test")
    public void itemCategorySaveApiTest(ItemCategoryDto.Request itemCategory) throws Exception {
        //given
        String expression1 = "$.";
        String expression2 = ".upperCategory";
        String expression3 = "[?(@.categoryName == '%s')]";

        MockHttpServletRequestBuilder post = MockMvcRequestBuilders
                .put("/itemCategories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(itemCategory));
        //when
        ResultActions resultActions = mvc.perform(post)
                .andDo(MockMvcResultHandlers.print())
                //then
                .andExpect(MockMvcResultMatchers.jsonPath(expression3, itemCategory.getCategoryName()).exists());

        ItemCategoryDto.Request holder = itemCategory;
        int count = 0;
        while (holder.getUpperCategory() != null) {
            resultActions.andExpect(MockMvcResultMatchers.jsonPath(expression1 + Strings.repeat(expression2, count) + expression3, holder.getCategoryName()).exists());
            holder = holder.getUpperCategory();
            count++;
        }
    }


}
