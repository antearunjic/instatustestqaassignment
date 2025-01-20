package com.example.GetPageComponentTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JsonStructureTests extends BaseApiTest {
    @Test
    public void testGetPageComponentJsonStructure() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components/clghp0xjn276266hnos82h6lodz"); 
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertNotNull(response.jsonPath().getString("name"));
        Assert.assertNotNull(response.jsonPath().getString("status"));
    }

    @Test
    public void testVerifyPageComponentJsonStructureNonEmptyList() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components/clghp0xjn276266hnos82h6lodz"); 
        Assert.assertTrue(response.jsonPath().getMap("$").size() > 0);
    }
}
