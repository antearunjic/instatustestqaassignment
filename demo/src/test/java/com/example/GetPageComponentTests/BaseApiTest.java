package com.example.GetPageComponentTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public abstract class BaseApiTest {
    protected static final String BASE_URL = "https://api.instatus.com";
    protected static final String BEARER_TOKEN = "8d16333936e72e705980878e18c95976";

    protected Response sendGetRequest(String endpoint) {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .accept(ContentType.JSON)
                .get(endpoint);
    }

    protected Response sendGetRequestWithInvalidToken(String endpoint) {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + "8d16333936e72e705980878e18c959ee")  // Invalid token
                .accept(ContentType.JSON)
                .get(endpoint);
    }
}
