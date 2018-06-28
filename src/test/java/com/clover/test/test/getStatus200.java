package com.clover.test.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class getStatus200 {


    //
    @Test
    public void validatePayments_200() {

        given().
                auth().
                oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                log().all().
                when().
                get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments").
                then().
                statusCode(200).
                log().all();
    }

}