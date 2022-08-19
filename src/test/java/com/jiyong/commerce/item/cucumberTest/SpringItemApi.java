package com.jiyong.commerce.item.cucumberTest;

import com.jiyong.commerce.item.dto.ItemCategoryDto;
import com.jiyong.commerce.item.dto.ItemDto;
import io.cucumber.messages.internal.com.google.gson.Gson;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;


@CucumberContextConfiguration
@SpringBootTest
@Slf4j
public class SpringItemApi {

    @Autowired
    WebApplicationContext context;
    MockMvc mvc;


    @BeforeEach
    public void initMockMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void itemSaveApiTest(String itemName, BigDecimal itemPrice, Long stock, String firstCategoryName, Long firstCategoryId, String secondCategoryName, Long secondCategoryId) throws Exception {
        //given
        log.info("itemName = {} ", itemName);
        ItemDto.Request request = new ItemDto.Request(new ItemCategoryDto.Request(firstCategoryId, firstCategoryName, new ItemCategoryDto.Request(secondCategoryId, secondCategoryName, null)), itemName, itemPrice, stock);
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders
                .put("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(request));
        //when
        mvc.perform(post)
                .andDo(MockMvcResultHandlers.print())
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$..[?(@.name == '%s')]", itemName).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$..[?(@.price == '%f')]", itemPrice).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$..[?(@.stock == '%d')]", stock).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$..itemCategoryDto[?(@.categoryName == '%s')]", firstCategoryName).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$..itemCategoryDto.upperCategory[?(@.categoryName == '%s')]", secondCategoryName).exists());
    }


}
