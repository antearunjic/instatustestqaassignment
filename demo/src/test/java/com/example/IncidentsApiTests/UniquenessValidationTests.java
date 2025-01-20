package com.example.IncidentsApiTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UniquenessValidationTests extends BaseApiTest {
    @Test
    public void testVerifyIncidentIds() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<String> incidentIds = response.jsonPath().getList("id");
        Assert.assertFalse(incidentIds.isEmpty());
        Set<String> uniqueIncidentIds = new HashSet<>(incidentIds);
        Assert.assertEquals(uniqueIncidentIds.size(), incidentIds.size());
    }

    @Test
    public void testShouldReturnUniqueSiteIds() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<String> incidentsSiteIds = response.jsonPath().getList("siteId");
        Assert.assertFalse(incidentsSiteIds.isEmpty());
        Set<String> uniqueIncidentsSiteIds = new HashSet<>(incidentsSiteIds);
        Assert.assertNotEquals(uniqueIncidentsSiteIds.size(), incidentsSiteIds.size());
    }

    @Test
    public void testVerifyUniqueEmailFormat() {
        Response response =sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");  
        List<Map<String, Object>> componentsValue = response.jsonPath().getList("[0].components");
        Map<String, Object> firstComponent = componentsValue.get(0);
        String componentsUniqueEmail = (String) firstComponent.get("uniqueEmail");
        String emailRegex = "^fortuna1-.*@automation.instatus.com$";
        Assert.assertTrue(componentsUniqueEmail.matches(emailRegex), "Invalid email format: " + componentsUniqueEmail);
    }
}
