package com.clover.test.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class SetCookie {

    @Test
    public void setCookie(){

        given().
                cookie("__utmt","1", "2").log().all().
                when().
                get("http://www.webservicex.com/globalweather.asmx?op=GetCitiesByCountry").
                then().
                statusCode(404);


    }

}