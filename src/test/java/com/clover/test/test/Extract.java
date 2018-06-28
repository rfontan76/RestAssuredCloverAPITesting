package com.clover.test.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class Extract {

    @Test
    public void extract() {

        String href =
                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                        when().
                        get("http://jsonplaceholder.typicode.com/photos/1").
                        then().
                        statusCode(200).
                        body("albumId", equalTo(1)).
                        extract().path("url");

        when().get(href).then().statusCode(200);
    }

}
