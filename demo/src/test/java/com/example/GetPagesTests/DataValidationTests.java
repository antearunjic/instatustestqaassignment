package com.example.GetPagesTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class DataValidationTests extends BaseApiTest {
    @Test
    public void testUpdatedAtFormat() {
        Response response = sendGetRequest("/v1/pages");
        List<String> updatedAtList = response.jsonPath().getList("updatedAt");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        for (String updatedAt : updatedAtList) {
            try {
                Assert.assertNotNull(dateFormat.parse(updatedAt), "UpdatedAt should have a valid format");
            } catch (ParseException e) {
                Assert.fail("Invalid 'updatedAt' format: " + updatedAt);
            }
        }
    }

    @Test
    public void testGetPagesStatusIsUpOrHasIssues() {
        Response response = sendGetRequest("/v1/pages");
        List<String> statuses = response.jsonPath().getList("status");
        for (String status : statuses) {
            Assert.assertTrue(status.equals("UP") || status.equals("HASISSUES"),
                    "Status should be either 'UP' or 'HASISSUES'");
        }
    }

    @Test
    public void testVerifyLanguageIsEnOrNull() {
        Response response = sendGetRequest("/v1/pages");
        List<String> languages = response.jsonPath().getList("language");
        for (String language : languages) {
            Assert.assertTrue(language == null || language.equals("en"),
                    "Language should be 'en' or null");
        }
    }

    @Test
    public void testUptimeDaysDisplayValuesAreBars() {
        Response response = sendGetRequest("/v1/pages"); 
        List<String> uptimeDaysDisplay = response.jsonPath().getList("uptimeDaysDisplay");
        Assert.assertFalse(uptimeDaysDisplay.isEmpty(), "The uptimeDaysDisplay list should not be empty");
        uptimeDaysDisplay.forEach(display -> Assert.assertTrue(display.equals("BARS") ));
    }
    
    @Test
    public void testRetrieveAllSubdomains() {
        Response response = sendGetRequest("/v1/pages");  
        List<String> subdomains = response.jsonPath().getList("subdomain");
        Assert.assertFalse(subdomains.isEmpty());
    }
}
