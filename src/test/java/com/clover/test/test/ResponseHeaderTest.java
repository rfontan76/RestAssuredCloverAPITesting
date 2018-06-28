package com.clover.test.test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class ResponseHeaderTest {

    @Test
    public void responseHeadersTest(){

        Response response =

                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                        when().
                        get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments");

        String contentEncoding = response.getHeader("Content-Encoding");
        System.out.println("Found Content Encoding:"+contentEncoding);

        Headers headers = response.getHeaders();
        for(Header h: headers){
            System.out.println(h.getName()+":"+h.getValue());


        }

        Assert.assertTrue(headers.hasHeaderWithName("X-Robots-Tag"));

    }

}