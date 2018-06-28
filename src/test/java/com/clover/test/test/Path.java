package com.clover.test.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Path {

    @Test
    public void path() {

        //Request
        String href =
                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                        when().
                        get("http://jsonplaceholder.typicode.com/photos/1").path("thumbnailUrl");

        //Verify
        when().get(href).then().statusCode(200);
    }
}
