package com.clover.test.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;


public class GetResponseAsAList {

    @Test
    public void testGetResponseAsList(){
        String response =

                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                        when().
                        get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments").asString();

        //Add response items to a list
        List<String> list = from(response).getList("elements.result");

        //Size of list is more than 0
        Assert.assertTrue(list.size()>0);



        //For loop
        for(String resultado:list){
            if(resultado.equals("FAIL")){
                System.out.println("Found failures");
            }
            else {
                return;
            }
        }

    }

}