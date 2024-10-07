package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

import utils.ConfigReader;

public class UserAPITests {
    static ConfigReader configReader;

    @BeforeAll
    public static void setup() {
        configReader = new ConfigReader();
        RestAssured.baseURI = configReader.getBaseUrl();
    }

    @Test
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
