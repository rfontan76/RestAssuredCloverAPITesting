package com.clover.test.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class validatePayment {


    @Test
    public void validatePayment() {


        given().
                auth().
                oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                log().all().
                when().
                get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments/09P296GHAP1H4").
                then().
                statusCode(200).
                log().all();
    }
  
}