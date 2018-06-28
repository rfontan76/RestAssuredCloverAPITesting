package com.clover.test.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;


public class ResponseTime {

    @Test
    public void testResponseTime(){

        long t  = given().
                auth().
                oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                log().all().
                when().
                get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments").timeIn(TimeUnit.SECONDS);

        System.out.println("Time:" + t);

        Assert.assertTrue(t < 3);

    }


}