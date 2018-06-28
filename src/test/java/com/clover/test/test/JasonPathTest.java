package com.clover.test.test;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class JasonPathTest {

    //JsonPath
    @Test
    public void jsonPathTest(){

        String response =
                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                        when().
                        get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments").
                        then().
                        extract().asString();

        JsonPath jsonPath = new JsonPath(response);

        List<String> idList = jsonPath.get("elements.id");

        System.out.println(idList);
        System.out.println(idList.size());

    }
}