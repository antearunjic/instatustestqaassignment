package com.example.GetPageComponentTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusCodeTests extends BaseApiTest {
   @Test
    public void testGetPageComponentStatusCode() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components/clghp0xjn276266hnos82h6lodz");  
        Assert.assertEquals(response.statusCode(), 200);
    }  

    @Test
    public void testGetPageComponentAuthenticationFailure() {
        Response response = sendGetRequestWithInvalidToken("/v1/clghp0xiu276238hnoskz6nq9r8/components/clghp0xjn276266hnos82h6lodz");
        Assert.assertEquals(401, response.getStatusCode());
    }

    @Test
    public void testGetPageComponentInvalidEndpoint() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components/clghp0xjn276266hnos82h6lod");
        Assert.assertEquals(500, response.getStatusCode());
    }
}

