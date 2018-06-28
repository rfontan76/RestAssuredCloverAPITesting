package com.clover.test.test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class ResponseVar {

    //Inline validation
    @Test
    public void Response_Ver() {

        String currency = "ARS";

        //Request
        Response response =
                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                        when().
                        get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/properties");

        //Inline Validation ******************************************************************************************
        //Hard Assertion: 1 Fail all Fail
        response.then().body("defaultCurrency", equalTo(currency)).body("orderTitleMax", equalTo(99)).log().all();
        //Soft Assertion: 1 Fail rest are verify
        //response.then().body("defaultCurrency", equalTo(currency),"orderTitle", equalTo("NONE"));

        //Path Validation  ******************************************************************************************
        //Hard Validation
        Assert.assertEquals(response.path("defaultCurrency"), currency);
        //Soft Assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.path("defaultCurrency"), currency);
        softAssert.assertEquals(response.path("orderTitle"), "NONE");
        softAssert.assertAll();
    }

}