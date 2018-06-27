package com.clover.framework;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;


public class RestAssuredConfiguration {


    //@BeforeTest(alwaysRun = true)
    public void configure() {
        RestAssured.baseURI = "https://apidev7.dev.clover.com";
        RestAssured.port = 443;
        RestAssured.basePath = "/v3";
    }

    public RequestSpecification getRequestSpecification() {
        return RestAssured
                .given()
                   .contentType(ContentType.JSON);

    }

    public RequestSpecification postRequestSpecification() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON);

    }

    public Response getResponse(RequestSpecification requestSpecification,String endpoint, int
            status){
        Response response = requestSpecification.get(endpoint);
        Assert.assertEquals(response.getStatusCode(),status);
        response.then().log().all();
        return response;
    }

}