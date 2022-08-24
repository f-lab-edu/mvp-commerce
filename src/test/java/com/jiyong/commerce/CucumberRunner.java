package com.jiyong.commerce;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = {"com/jiyong/commerce/itemcategory/cucumbertest"})
public class CucumberRunner {
}
