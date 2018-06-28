package com.clover.test.test;

import com.clover.framework.RestAssuredConfiguration;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class LineItemOrder {

    //Example for post with Body parameters
    @Test
    public void lineItemOrder() {

        String accessToken = "59f40dae-90ca-c336-95f0-492527e1ff77";

        RequestSpecification request = new RestAssuredConfiguration().postRequestSpecification()
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());

        given().
                auth().preemptive().
                oauth2(accessToken);

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Hot Dog");
        requestParams.put("price", "50");


        RequestSpecification response =

                request.body(requestParams.toString());
        request.post("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/orders/X8ACBYNTB3EGR/line_items");

    }

}