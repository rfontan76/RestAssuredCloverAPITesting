package com.clover.test.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class validatePaymentWithRoot {


    @Test
    public void validatePaymentWithRoot() {


        given().
                auth().
                oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                log().all().
                when().
                get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments/09P296GHAP1H4").
                then().
                //with XPath
                        body("tender.id",equalTo("JGFYBHRVXPAEJ")).
                body("employee.id",equalTo("1FB0M6MNGTV8W")).
                log().all();
    }
  
}