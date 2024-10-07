package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

import utils.ConfigReader;

import io.qameta.allure.Step;
import io.qameta.allure.Description;

public class UserAPITests {
    static ConfigReader configReader;

    @BeforeAll
    public static void setup() {
        configReader = new ConfigReader();
        RestAssured.baseURI = configReader.getBaseUrl();
    }

    @Test
    @Description("Verify that we can get a list of users")
    public void testGetUsers() {
        RestAssured
                .given()
                .when()
                .get(configReader.getUserEndpoint())
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(0))); // Check if response has users
    }

    @Test
    @Description("Verify that we can get a user by ID")
    public void testGetUserById() {
        int userId = 1;
        Response response = RestAssured
                .given()
                .when()
                .get(configReader.getUserEndpoint() + "/" + userId);

        response.then()
                .statusCode(200)
                .body("id", equalTo(userId));
    }
}

