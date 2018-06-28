package com.clover.test.test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TipRateDefault {

    @Test
    public void great() {

        //Request
        Response response =
                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                        when().
                        get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/properties").
                        then().log().all().
                        extract().response();

        System.out.println(response.contentType());
        System.out.println(response.path("tipRateDefault"));
        System.out.println(response.statusCode());

        int tipRateDefault = response.path("tipRateDefault");

        Assert.assertTrue(tipRateDefault > 0);
        Assert.assertTrue(tipRateDefault == 1500);
        Assert.assertTrue(tipRateDefault < 1600);

    }
}
