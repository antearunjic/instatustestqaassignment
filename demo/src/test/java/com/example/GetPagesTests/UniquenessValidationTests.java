package com.example.GetPagesTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashSet;
import java.util.List;

public class UniquenessValidationTests extends BaseApiTest {
    @Test
    public void testRetrieveAllIdsAreUnique() {
        Response response = sendGetRequest("/v1/pages");
        List<String> ids = response.jsonPath().getList("id");
        Assert.assertEquals(ids.size(), new HashSet<>(ids).size(),
                "All IDs should be unique");
    }

    @Test
    public void testRetrieveAllWorkspaceIdsAreUnique() {
        Response response = sendGetRequest("/v1/pages");
        List<String> workspaceIds = response.jsonPath().getList("workspaceId");
        Assert.assertEquals(workspaceIds.size(), new HashSet<>(workspaceIds).size(),
                "All workspace IDs should be unique");
    }
}
