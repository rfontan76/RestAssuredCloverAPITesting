package com.clover.test.test;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SimplePost {

    @Test
    public void post() {

        given().
                header("Authorization","Bearer 59f40dae-90ca-c336-95f0-492527e1ff77").
                body("    {\"state\": \"open\" } ").
                log().all().

                when().
                contentType(ContentType.JSON).
                post("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/orders").

                then().
                statusCode(200).
                log().all();
    }

}
