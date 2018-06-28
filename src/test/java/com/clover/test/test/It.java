package com.clover.test.test;

import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;


public class It {

    @Test
    public void it(){
        String response =

                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                        when().
                        get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments").asString();

        //Verify first item on list using IT
        List<String> list = from(response).getList("elements.findAll {it.id.length() > 10}.id") ;



        System.out.println(list);




    }

}