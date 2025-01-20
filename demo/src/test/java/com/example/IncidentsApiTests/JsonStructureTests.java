package com.example.IncidentsApiTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JsonStructureTests extends BaseApiTest {
    @Test
    public void testGetIncidentsJsonStructure() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        Assert.assertNotNull(response.jsonPath().getString("$"));
    }

    @Test
    public void testGetIndicentsNonEmptyResponse() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Assert.assertTrue(response.getBody().asString().length() > 0);
    }
}
