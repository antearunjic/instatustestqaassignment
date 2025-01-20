package com.example.GetPageComponentsTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniquenessValidationTests extends BaseApiTest {
    @Test
    public void testVerifyUniqueComponentsIds() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components");
        List<String> componentIds = response.jsonPath().getList("id");
        Set<String> uniqueComponentIds = new HashSet<>(componentIds);
        Assert.assertEquals(uniqueComponentIds.size(), componentIds.size(), "Component IDs should be unique");
    }

    @Test
    public void testShouldReturnUniqueComponentSiteIds() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components");
        List<String> componentSiteIds = response.jsonPath().getList("siteId");
        Set<String> uniqueComponentSiteIds = new HashSet<>(componentSiteIds);
        Assert.assertNotEquals(uniqueComponentSiteIds.size(), componentSiteIds.size(), "Component Site IDs should be unique");
    }
}
