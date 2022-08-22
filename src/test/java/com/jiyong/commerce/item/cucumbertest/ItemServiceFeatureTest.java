package com.jiyong.commerce.item.cucumbertest;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import java.math.BigDecimal;

public class ItemServiceFeatureTest extends SpringItemApi {

    @Before
    public void before() {
        initMockMvc();
    }

    @And("{string}이 존재")
    public void 유저확인(String arg0) {
    }

    @And("{string}이 {string}의 재고 {int}개 등록")
    public void 재고등록(String arg0, String arg1, int arg2) {
    }

    @When("{string}가 {string} {int}개 구매")
    public void 아이템구매(String arg0, String arg1, int arg2) {
    }

    @And("{string}이 {string} {long} 카테고리의 {string} {long} 에 속하는 {string} {long} 개를 개당 {bigdecimal} 원에 등록")
    public void 아이템등록(String username, String firstCategoryName, Long firstCategoryId, String secondCategoryName, Long secondCategoryId, String itemName, Long stock, BigDecimal itemPrice) throws Exception {
        itemSaveApiTest(itemName, itemPrice, stock, firstCategoryName, firstCategoryId, secondCategoryName, secondCategoryId);
    }
}
