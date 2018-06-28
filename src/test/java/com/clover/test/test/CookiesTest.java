package com.clover.test.test;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class CookiesTest {

    @Test
    public void cookiesTest(){


        Response response =

                given().get("http://jsonplaceholder.typicode.com/photos/1");

        Map<String, String> cookies = response.getCookies();

        for ( Map.Entry<String, String>  entry : cookies.entrySet()){

            System.out.println((entry.getKey()+":"+entry.getValue()));
        }

    }

}