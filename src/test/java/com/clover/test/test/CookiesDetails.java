package com.clover.test.test;

import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class CookiesDetails {

    @Test
    public void cookiesDetail(){

        Response response =

                given().
                        // auth().
                        // oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        //  log().all().
                        //   when().
                                get("http://jsonplaceholder.typicode.com/photos/1");

        Cookie a = response.getDetailedCookie("__cfduid");

        System.out.println("Has Expired:"+a.hasExpiryDate());
        System.out.println("Expired Date:"+a.getExpiryDate());
        System.out.println("Has Value:"+a.hasValue());
        System.out.println("Path:"+a.getPath());
        System.out.println("Is Secure:"+a.isSecured());



    }

}