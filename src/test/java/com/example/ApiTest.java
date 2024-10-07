package com.example;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    @Test
    public void getUserDetails() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("name", equalTo("Leanne Graham"));
    }
}
