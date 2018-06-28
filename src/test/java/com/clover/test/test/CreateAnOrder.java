package com.clover.test.test;

import com.clover.framework.RestAssuredConfiguration;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;


public class CreateAnOrder {

    //Example for post with Body parameters
    @Test
    public void createAnOrder() {

        //Setting Token
        String accessToken = "59f40dae-90ca-c336-95f0-492527e1ff77";

        //Setting request
        RequestSpecification request = new RestAssuredConfiguration().getRequestSpecification()
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        //Starting to build request with preemptive and with token
        given().
                auth().preemptive().
                oauth2(accessToken).log().all();

        //Generate body request
        JSONObject requestParams = new JSONObject();
        requestParams.put("state", "open");

        //Generate request
        request.body(requestParams.toString());

    }

}