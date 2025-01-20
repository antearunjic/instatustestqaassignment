package com.example.GetPageComponentsTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JsonStructureTests extends BaseApiTest {
    @Test
    public void testVerifyPageComponentsJsonStructure() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components");
        Assert.assertNotNull(response.jsonPath().getString("$"), "JSON structure should not be null");   
    }

    @Test
    public void testVerifyPageComponentstesJsonStructureNonEmptyList() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components");
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0);
    }
}
