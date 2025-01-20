package com.example.GetPageComponentsTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusCodeTests extends BaseApiTest {
    @Test
    public void testGetPageComponentsStatusCode() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components");
        Assert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
    }

    @Test
    public void testGetPageComponentsAuthenticationFailure() {
        Response response = sendGetRequestWithInvalidToken("/v1/clghp0xiu276238hnoskz6nq9r8/components");
        Assert.assertEquals(response.statusCode(), 401, "Expected status code to be 401 for invalid token");
    }

    @Test
    public void testGetPageComponentsInvalidEndpoint() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r/components");
        Assert.assertEquals(response.statusCode(), 404, "Expected status code to be 404 for invalid endpoint");
    }
}
