package com.example.IncidentsApiTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusCodeTests extends BaseApiTest {
    @Test
    public void testGetIncidentsStatusCode() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");  
        Assert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
    }
 
    @Test
    public void testGetIncidentsAuthenticationFailure() {
        Response response = sendGetRequestWithInvalidToken("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Assert.assertEquals(401, response.getStatusCode());
    }

    @Test
    public void testGetIncidentsInvalidEndpoint() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incident");
        Assert.assertEquals(404, response.getStatusCode());
    }
}
