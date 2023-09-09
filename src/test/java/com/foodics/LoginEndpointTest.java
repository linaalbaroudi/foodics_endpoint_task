package com.foodics;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginEndpointTest {
    @Before
    public void setup() {
        RestAssured.baseURI = "https://pay2.foodics.dev/cp_internal";
    }

    @Test
    public void validLoginTest() {
        Response response = given()
            .contentType(ContentType.JSON)
            .body("{\"email\":\"merchant@foodics.com\",\"password\":\"123456\",\"token\":\"Lyz22cfYKMetFhKQybx5HAmVimF1i0xO\"}")
            .when()
            .post("/login")
            .then()
            .statusCode(200)
            .body("token", notNullValue())
            .extract()
            .response();

            
            String token = response.jsonPath().getString("token");
    //         System.out.println("validLoginTest token: " + token);
            
    //          System.out.println();
    //         System.out.println("followUpResponse header: " + response.headers().toString());
    //         System.out.println();
    //         System.out.println("followUpResponse Body: " + response.getBody().asString());
    }

    @Test
    public void invalidLoginTest() {
        Response response = given()
            .contentType(ContentType.JSON)
            .body("{\"email\":\"invalid@example.com\",\"password\":\"invalidpassword\",\"token\":\"invalidtoken\"}")
            .when()
            .post("/login")
            .then()
            .extract()
            .response();

        if (response.getStatusCode() == 301 || response.getStatusCode() == 302) {

            String newLocation = response.getHeader("Location");

            assertTrue(!newLocation.isEmpty());
            assertThat(newLocation, equalTo("https://pay2.foodics.dev"));

            Response followUpResponse = given()
                .when()
                .get(newLocation)
                .then()
                .statusCode(200)
                .extract()
                .response();
        }
    }
}
