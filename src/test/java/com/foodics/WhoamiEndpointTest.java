package com.foodics;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static io.restassured.RestAssured.given;

public class WhoamiEndpointTest {
    @Before
    public void setup() {
        RestAssured.baseURI = "https://pay2.foodics.dev/cp_internal";
    }

    @Test
    public void validTokenTest() {
        // String authToken = "Lyz22cfYKMetFhKQybx5HAmVimF1i0xO";

        Response response = given()
            .contentType(ContentType.JSON)
            // .header("Authorization", "Bearer " + authToken)
            // .body(String.format("{\"email\":\"merchant@foodics.com\",\"password\":\"123456\",\"token\":\"%s\"}", authToken))
            .when()
            .get("/whoami")
            .then()
            .statusCode(200)
            .extract()
            .response();
           // .body("email", equalTo("merchant@foodics.com"));
    
        //   System.out.println("validLoginTest token: " + token);
            
             System.out.println();
            System.out.println("followUpResponse header: " + response.headers().toString());
            System.out.println();
            System.out.println("followUpResponse Body: " + response.getBody().asString());
        }

    // @Test
    // public void invalidTokenTest() {
    //     //  String authToken = "";

    //     Response response = given()
    //         .contentType(ContentType.JSON)
    //         // .header("Authorization", "Bearer " + authToken)
    //         // .body(String.format("{\"email\":\"merchant@foodics.com\",\"password\":\"123456\",\"token\":\"%s\"}", authToken))
    //         .when()
    //         .get("/whoami")
    //         .then()
    //         .statusCode(200)
    //         .extract()
    //         .response();
            
    //          System.out.println();
    //         System.out.println("followUpResponse header: " + response.headers().toString());
    //         System.out.println();
    //         System.out.println("followUpResponse Body: " + response.getBody().asString());

            
    //          System.out.println();
    //         // assertTrue(response.getBody().asString().contains("Incorrect credentials"));
    // }
}
