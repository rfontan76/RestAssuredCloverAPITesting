package com.clover.test.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;


public class HasItem {


    @Test
    public void hasItemsMore() {

        given().

                get("https://dev7.dev.clover.com/v2/countries").
                then().
                //with XPath
                        body("countryCode", hasItem("AR")).
                        body("countryCode", hasItem("BR")).
                        body("countryCode", hasItem("CA")).
                        body("countryCode", hasItem("DE")).
                        body("countryCode", hasItem("GB")).
                        body("countryCode", hasItem("IE")).
                        body("countryCode", hasItem("PL")).
                log().all();
    }

}