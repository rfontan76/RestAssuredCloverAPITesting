package com.clover.test.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;


public class hasItems {

    @Test
    public void hasItems() {


        given().
                auth().
                oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                log().all().
                when().
                get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments").
                then().
                //with XPath
                        body("elements.device.id", hasItem("7cab9663-2f77-1dc4-2848-8eaef2bcd135")).
                log().all();
    }
}