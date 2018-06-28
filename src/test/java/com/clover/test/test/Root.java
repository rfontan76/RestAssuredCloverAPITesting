package com.clover.test.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class Root {

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
}
