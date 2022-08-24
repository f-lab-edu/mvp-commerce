package com.jiyong.commerce.item.cucumbertest;

import com.jiyong.commerce.item.dto.ItemDto;
import com.jiyong.commerce.itemCategory.dto.ItemCategoryDto;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class ItemServiceFeatureSteps extends SpringItemApi {

    private Map<String, ItemDto.Response> itemList = new HashMap<>();
    private Map<String, ItemCategoryDto.Request> itemCategoryList = new HashMap<>();
    private Map<String, Exception> exceptionHolder = new HashMap<>();


    @Before
    public void before() {
        ItemCategoryDto.Request 식품 = ItemCategoryDto.Request.builder().categoryName("식품").id(1L).build();
        itemCategoryList.put("식품", 식품);
        ItemCategoryDto.Request 축산물 = ItemCategoryDto.Request.builder().categoryName("축산물").id(2L).upperCategory(식품).build();
        itemCategoryList.put("축산물", 축산물);
        ItemCategoryDto.Request 소고기 = ItemCategoryDto.Request.builder().categoryName("소고기").id(3L).upperCategory(축산물).build();
        itemCategoryList.put("소고기", 소고기);
        initMockMvc();
    }

    @And("{string}이 존재")
    public void userConfirmation(String arg0) {
    }


    @When("{string}가 {string}을 {long}개 구매")
    public void updateItemStock(String userName, String itemName, Long stock) throws Exception {
        ItemDto.Response response = searchItem(itemName);
        ItemDto.Request requestData = updateItemStock(response.getStock() - stock);
        ItemDto.Response updateResponse = itemUpdateApiTest(response.getId(), requestData);
        itemList.put(updateResponse.getName(), updateResponse);
    }


    @And("{string}이 {string}을 {long}개 등록")
    public void saveItem(String userName, String itemName, Long stock) throws Exception {
        ItemCategoryDto.Request 소고기 = itemCategoryList.get("소고기");
        ItemDto.Request requestData = new ItemDto.Request(소고기, "맛있는등심", BigDecimal.valueOf(5000), 2L);
        ItemDto.Response response = itemSaveApiTest(requestData);
        itemList.put("맛있는등심", response);
    }


    @Then("{string}의 재고는 {long}개")
    public void stockCheck(String itemName, Long stock) throws Exception {
        ItemDto.Response response = searchItem(itemName);
        assertThat(response.getStock()).isEqualTo(stock);
    }


    @Then("{string}가 {string}을 {long}개  구매 불가 사유 : 재고 부족")
    public void updateItemStockException(String userName, String itemName, Long stock) throws Exception {
        ItemDto.Response response = searchItem(itemName);
        ItemDto.Request requestData = updateItemStock(response.getStock() - stock);
        itemUpdateApiExceptionTest(response.getId(), requestData);
    }

    @When("{string}이 {string}의 재고 {long}개 추가")
    public void 이의재고개등록(String userName, String itemName, Long stock) throws Exception {
        ItemDto.Response response = searchItem(itemName);
        ItemDto.Request requestData = updateItemStock(response.getStock() + stock);
        ItemDto.Response updateResponse = itemUpdateApiTest(response.getId(), requestData);
        itemList.put(updateResponse.getName(), updateResponse);
    }

    private ItemDto.Request updateItemStock(long stock) {
        Long updateStock = stock;
        ItemCategoryDto.Request 소고기 = itemCategoryList.get("소고기");
        ItemDto.Request requestData = new ItemDto.Request(소고기, "맛있는등심", BigDecimal.valueOf(5000), updateStock);
        return requestData;
    }

    private ItemDto.Response searchItem(String itemName) throws Exception {
        Long itemId = Optional.of(itemList.get(itemName)).orElseThrow(() -> new NoSuchElementException("찾는 상품이 존재하지않음")).getId();
        return itemDetailSearchApiTest(itemId);
    }

    @And("{string}는 {string} {int}개 구매함")
    public void 는개구매함(String arg0, String arg1, int arg2) {

    }
}
