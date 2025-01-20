package com.example.GetPagesTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;


public class JsonStructureTests extends BaseApiTest {
    @Test
    public void testGetPagesJsonStructure() {
        Response response = sendGetRequest("/v1/pages");
        Assert.assertNotNull(response.jsonPath().getString("[0].id"), "ID should not be null");
        Assert.assertNotNull(response.jsonPath().getString("[0].name"), "Name should not be null");
        Assert.assertNotNull(response.jsonPath().getString("[0].status"), "Status should not be null");
    }

    @Test
    public void testGetPagesEmptyResponse() {
        Response response = sendGetRequest("/v1/pages");
        Assert.assertTrue(response.jsonPath().getList("$").isEmpty());
    }

    @Test
    public void testGetPagesNonEmptyResponse() {
        Response response = sendGetRequest("/v1/pages");
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0);
    }
}
