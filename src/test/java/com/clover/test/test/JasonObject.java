package com.clover.test.test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JasonObject {

    @Test
    public void response() {

        //Request
        Response response =
                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                        when().
                        get("http://jsonplaceholder.typicode.com/photos/1").
                        then().
                        extract().response();

        System.out.println(response.contentType());
        System.out.println(response.path("url"));
        System.out.println(response.statusCode());

        Assert.assertTrue(response.path("url") != "");
        Assert.assertNotNull(response.path("id"));

        String responseUrl = response.path("url");
        Assert.assertTrue(responseUrl.contains("http://"));
    }
}
