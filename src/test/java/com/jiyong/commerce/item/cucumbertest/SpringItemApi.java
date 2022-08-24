package com.jiyong.commerce.item.cucumbertest;

import com.jiyong.commerce.item.dto.ItemDto;
import io.cucumber.messages.internal.com.google.gson.Gson;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
    @Rollback(false)
    public ItemDto.Response itemSaveApiTest(ItemDto.Request dto) throws Exception {
        //given
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders
                .put("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(dto));
        //when
        ResultActions resultActions = mvc.perform(post)
                .andDo(print())
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..[?(@.name == '%s')]", dto.getName()).exists())
                .andExpect(jsonPath("$..[?(@.price == '%f')]", dto.getPrice()).exists())
                .andExpect(jsonPath("$..[?(@.stock == '%d')]", dto.getStock()).exists());
        ItemDto.Response response = new Gson().fromJson(resultActions.andReturn().getResponse().getContentAsString(), ItemDto.Response.class);
        return response;
    }

    @Test
    @Rollback(false)
    public ItemDto.Response itemUpdateApiTest(Long itemId, ItemDto.Request dto) throws Exception {
        //given
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders
                .post("/items/" + itemId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(dto));
        //when
        ResultActions resultActions = mvc.perform(post)
                .andDo(print())
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..[?(@.name == '%s')]", dto.getName()).exists())
                .andExpect(jsonPath("$..[?(@.id == '%d')]", itemId).exists())
                .andExpect(jsonPath("$..[?(@.price == '%f')]", dto.getPrice()).exists())
                .andExpect(jsonPath("$..[?(@.stock == '%d')]", dto.getStock()).exists());

        ItemDto.Response response = new Gson().fromJson(resultActions.andReturn().getResponse().getContentAsString(), ItemDto.Response.class);
        return response;
    }

    @Test
    @Rollback(false)
    public void itemUpdateApiExceptionTest(Long itemId, ItemDto.Request dto) throws Exception {
        //given
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders
                .post("/items/" + itemId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(dto));
        //when
        ResultActions resultActions = mvc.perform(post)
                .andDo(print())
                //then
                .andExpect(status().isServiceUnavailable());
    }


    @Test
    public ItemDto.Response itemDetailSearchApiTest(Long itemId) throws Exception {

        //given
        MockHttpServletRequestBuilder get = MockMvcRequestBuilders
                .get("/items/" + itemId)
                .accept(MediaType.APPLICATION_JSON);
        //when
        ResultActions resultActions = mvc.perform(get)
                .andDo(print())
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..[?(@.id == '%d')]", itemId).exists());
        //then
        ItemDto.Response response = new Gson().fromJson(resultActions.andReturn().getResponse().getContentAsString(), ItemDto.Response.class);
        return response;
    }


}
