package com.example.GetPagesTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StatusCodeTests extends BaseApiTest {
    @Test
    public void testGetPagesStatusCode() {
        Response response = sendGetRequest("/v1/pages");
        Assert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
    }
    
    @Test
    public void testGetPagesAuthenticationFailure() {
        String invalidToken = "8d16333936e72e705980878e18c959ee";
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + invalidToken)
                .accept(ContentType.JSON)
                .get("/v1/pages");
        Assert.assertEquals(response.statusCode(), 401, "Expected status code to be 401 for invalid token");
    }

    @Test
    public void testGetPagesInvalidEndpoint() {
        Response response = sendGetRequest("/v1//page");
        Assert.assertEquals(response.statusCode(), 404, "Expected status code to be 404 for invalid endpoint");
    }
}
