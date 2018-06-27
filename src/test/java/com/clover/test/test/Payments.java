package com.clover.test.test;

import com.clover.framework.RestAssuredConfiguration;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;



public class Payments {

    //
    @Test
    public void validatePayments_200() {

        given().
                auth().
                oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                log().all().
        when().
                get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments").
        then().
                statusCode(200).
                log().all();
    }

    @Test
    public void hasItemsMore() {

        given().

                get("https://dev7.dev.clover.com/v2/countries").
                then().
                //with XPath
                        body("countryCode", hasItem("AR")).
                        body("countryCode", hasItem("BR")).
                        body("countryCode", hasItem("CA")).
                        body("countryCode", hasItem("DE")).
                        body("countryCode", hasItem("GB")).
                        body("countryCode", hasItem("IE")).
                        body("countryCode", hasItem("PL")).
                log().all();
    }


    @Test
    public void validatePayments_200_New() {

        given().
                auth().
                oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                log().all().
        when().
                get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments").
        then().
                statusCode(200).
                log().all();
    }

    @Test
    public void validatePayment() {


       given().
                auth().
                oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                log().all().
       when().
                get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments/09P296GHAP1H4").
       then().
                statusCode(200).
                log().all();
    }

    @Test
    public void validatePaymentWithRoot() {


        given().
                auth().
                oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                log().all().
        when().
                get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments/09P296GHAP1H4").
        then().
                //with XPath
                body("tender.id",equalTo("JGFYBHRVXPAEJ")).
                body("employee.id",equalTo("1FB0M6MNGTV8W")).
                log().all();
    }

    @Test
    public void hasItems() {


        given().
                auth().
                oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                log().all().
        when().
                get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments").
        then().
                //with XPath
                        body("elements.device.id", hasItem("7cab9663-2f77-1dc4-2848-8eaef2bcd135")).
                log().all();
    }


    @Test
    public void Arrays() {

        Response response =
                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                when().
                        get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments");
                        response.then().
                        //with XPath
                                body("elements.device.id", hasItem("7cab9663-2f77-1dc4-2848-8eaef2bcd135")).
                        log().all();

                //response.body().
    }




    //Example for post with Body parameters
    @Test
    public void createAnOrder() {

        String accessToken = "59f40dae-90ca-c336-95f0-492527e1ff77";

        RequestSpecification request = new RestAssuredConfiguration().getRequestSpecification()
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());

        given().
                auth().preemptive().
                oauth2(accessToken);


        JSONObject requestParams = new JSONObject();
        requestParams.put("state", "open");
        request.body(requestParams.toString());

    }

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



    //Inline validation
    @Test
    public void Response_Ver() {

        String currency = "ARS";

        //Request
        Response response =
        given().
                auth().
                oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                log().all().
                when().
                get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/properties");

        //Inline Validation ******************************************************************************************
        //Hard Assertion: 1 Fail all Fail
        response.then().body("defaultCurrency", equalTo(currency)).body("orderTitleMax", equalTo(99)).log().all();
        //Soft Assertion: 1 Fail rest are verify
        //response.then().body("defaultCurrency", equalTo(currency),"orderTitle", equalTo("NONE"));

        //Path Validation  ******************************************************************************************
        //Hard Validation
        Assert.assertEquals(response.path("defaultCurrency"), currency);
        //Soft Assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.path("defaultCurrency"), currency);
        softAssert.assertEquals(response.path("orderTitle"), "NONE");
        softAssert.assertAll();
    }

    //
    @Test
    public void root() {

        given().
                auth().
                oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                log().all().

        when().
                get("https://apidev7.dev.clover.com/v3/merchants/GQS1H99GXGSHW/orders/X8ACBYNTB3EGR?expand=lineItems,discounts").
        then().
                statusCode(200).
                root("lineItems.elements").
                body("name", hasItem("Hot Dog")).
                log().all();
    }
    //
    @Test
    public void rootDetach() {

        given().
                auth().
                oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                log().all().
        when().
                get("https://apidev7.dev.clover.com/v3/merchants/GQS1H99GXGSHW/orders/X8ACBYNTB3EGR?expand=lineItems,discounts").
        then().
                statusCode(200).
                root("lineItems.elements").
                body("name", hasItem("Hot Dog")).
                detachRoot("lineItems.elements").
                body("id", is("X8ACBYNTB3EGR")).
                log().all();
    }


    //
    @Test
    public void post() {

        given().
                header("Authorization","Bearer 59f40dae-90ca-c336-95f0-492527e1ff77").
                body("    {\"state\": \"open\" } ").
                log().all().

        when().
                contentType(ContentType.JSON).
                post("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/orders").

        then().
                statusCode(200).
                log().all();
    }

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
    @Test
    public void path() {

        //Request
        String href =
                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                        when().
                        get("http://jsonplaceholder.typicode.com/photos/1").path("thumbnailUrl");

        //Verify
        when().get(href).then().statusCode(200);
    }

    @Test
    public void jsonPath() {

        //Request
        String href =
                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                        when().
                        get("http://jsonplaceholder.typicode.com/photos/1").
                        andReturn().jsonPath().getString("thumbnailUrl");

        //Verify
        when().get(href).then().statusCode(200);
    }

    @Test
    public void response() {

        //Request
        Response response =
                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                when().
                        get("http://jsonplaceholder.typicode.com/photos/1").
                then().
                        extract().response();

        System.out.println(response.contentType());
        System.out.println(response.path("url"));
        System.out.println(response.statusCode());

        Assert.assertTrue(response.path("url") != "");
        Assert.assertNotNull(response.path("id"));

        String responseUrl = response.path("url");
        Assert.assertTrue(responseUrl.contains("http://"));
    }

    //
    @Test
    public void schema() {

        given().
                auth().
                oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                log().all().
                when().
                get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments").
                then().
                assertThat().body(matchesJsonSchemaInClasspath("schema.json"));
    }


    @Test
    public void great() {

        //Request
        Response response =
                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                when().
                        get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/properties").
                then().log().all().
                        extract().response();

        System.out.println(response.contentType());
        System.out.println(response.path("tipRateDefault"));
        System.out.println(response.statusCode());

        int tipRateDefault = response.path("tipRateDefault");

        Assert.assertTrue(tipRateDefault > 0);
        Assert.assertTrue(tipRateDefault == 1500);
        Assert.assertTrue(tipRateDefault < 1600);



    }

   // @Test
    public void greaterThanInline (){

                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                when().
                        get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/properties").
                then().
                        body("tipRateDefault*.length().sum()", greaterThan(10)).log().all();


    }


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


    //Make list of items matching IT criteria
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

    //Headers
    @Test
    public void responseHeadersTest(){

        Response response =

                given().
                        auth().
                        oauth2("59f40dae-90ca-c336-95f0-492527e1ff77").
                        log().all().
                        when().
                        get("https://apidev7.dev.clover.com:443/v3/merchants/GQS1H99GXGSHW/payments");

        String contentEncoding = response.getHeader("Content-Encoding");
        System.out.println("Found Content Encoding:"+contentEncoding);

        Headers headers = response.getHeaders();
        for(Header h: headers){
            System.out.println(h.getName()+":"+h.getValue());


        }

        Assert.assertTrue(headers.hasHeaderWithName("X-Robots-Tag"));

    }

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