package com.example.IncidentsApiTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


public class DataValidationTests extends BaseApiTest {
    @Test
    public void testVerifyComponentsName() {
        Response response =sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<Map<String, Object>> componentsValue = response.jsonPath().getList("[0].components");
        Map<String, Object> firstComponent = componentsValue.get(0);
        String componentsName = (String) firstComponent.get("name");
        String expectedName = "Buggy Component";
        Assert.assertEquals(expectedName, componentsName);
    }
    
    @Test
    public void testVerifyComponentsStatuses() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");     
        List<Map<String, Object>> componentsValue = response.jsonPath().getList("[0].components");
        Map<String, Object> firstComponent = componentsValue.get(0);
        String componentsStatus = (String) firstComponent.get("status");
        String componentsInternalStatus = (String) firstComponent.get("internalStatus");
        Assert.assertEquals(componentsStatus, componentsInternalStatus);
    }

    @Test
    public void testVerifyUpdatedsCreatedAtDateFormat() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<Map<String, Object>> updatesValue = response.jsonPath().getList("[0].updates");
        Map<String, Object> firstUpdate = updatesValue.get(0);
        String createdAtDate = (String) firstUpdate.get("createdAt");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            java.util.Date date =  dateFormat.parse(createdAtDate); 
            Assert.assertNotNull(date);
        } catch (ParseException e) {
            Assert.fail("Invalid 'createdAt' date format: " + createdAtDate);
        }
    }    

    @Test
    public void testVerifyStartedDateFieldHasValidDateFormat() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        Map<String, Object> firstArrayResponse = response.jsonPath().getMap("[0]");
        String startedDate = (String) firstArrayResponse.get("started");  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            java.util.Date date =  dateFormat.parse(startedDate); 
            Assert.assertNotNull(date, "The 'startedDate' field should be a valid date");
        } catch (ParseException e) {
            Assert.fail("Invalid 'startedDate' date format: " + startedDate);
        }
    }

    @Test
    public void testVerifyStartedAndCreatedAtNotEqual() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<Map<String, Object>> updatesValue = response.jsonPath().getList("[0].updates");
        Map<String, Object> firstUpdate = updatesValue.get(0);
        String updatesStartedDate = (String) firstUpdate.get("started");
        String createdAtDate = (String) firstUpdate.get("createdAt");
        Assert.assertNotEquals(updatesStartedDate, createdAtDate);
    }

    @Test
    public void testVerifyVisibilityStateField() {
        Response response =sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        Map<String, Object> firstArrayResponse = response.jsonPath().getMap("[0]");
        String visibilityStateValue = (String) firstArrayResponse.get("visibilityState");
        Assert.assertEquals(visibilityStateValue, "PUBLISHED");
    }

    @Test
    public void testVerifyUpdatedsStatusValue() {
        Response response =sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<Map<String, Object>> updatesValue = response.jsonPath().getList("[0].updates");
        Map<String, Object> firstUpdate = updatesValue.get(0);
        String updatesStatus = (String) firstUpdate.get("status");
        Assert.assertEquals("INVESTIGATING", updatesStatus);      
    }

    @Test
    public void testVerifyFirstArrayStatusIsInvestigating() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");   
        Map<String, Object> firstArrayResponse = response.jsonPath().getMap("[0]");
        String status = (String) firstArrayResponse.get("status");
        Assert.assertTrue(status.equals("INVESTIGATING"));
        System.out.println(firstArrayResponse);
        System.out.println(status);
    }

    @Test
    public void testVerifyImapctMessageField() {
        Response response =sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        Map<String, Object> firstArrayResponse = response.jsonPath().getMap("[0]");
        String impactMessage = (String) firstArrayResponse.get("impact");
        Assert.assertNotNull(impactMessage);
    }

    @Test
    public void testVerifyComponentSiteMainStatusValue() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        String componentSiteMainStatus = (String) componentSiteValue.get("mainStatus");
        Assert.assertEquals(componentSiteMainStatus, "ONEMAJOROUTAGE");
    }
    
    @Test
    public void testVerifyComponentSiteNameValue() {
        Response response =sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        String componentSiteName = (String) componentSiteValue.get("name");
        Assert.assertEquals(componentSiteName, "Fortuna1"); 
    }

    @Test
    public void testVerifyComponentSiteStatusValue() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        String componentSiteStatus = (String) componentSiteValue.get("status");
        Assert.assertEquals(componentSiteStatus, "HASISSUES");
    }

    @Test
    public void testVerifyComponentsSiteWorkspaceIdField() {
        Response response =sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        String componentSiteWorkspaceId = (String) componentSiteValue.get("workspaceId");
        Assert.assertNotNull(componentSiteWorkspaceId);
    }

    @Test
    public void testVerifyComponentSiteLanuageaAreEqual() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        String componentSiteLang = (String) componentSiteValue.get("language");
        String componentSiteSuppLang = (String) componentSiteValue.get("supportedLanguages");
        Assert.assertEquals(componentSiteLang, componentSiteSuppLang);
    }

    @Test
    public void testCheckComponentSiteLanguageURL() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        String componentSiteLanguageURL = (String) componentSiteValue.get("screenshot");
        Assert.assertNotNull(componentSiteLanguageURL);
    }

    @Test
    public void testCheckComponentSitePrvateFieldValue() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        Boolean componentSitePrivateField = (Boolean) componentSiteValue.get("private");
        Assert.assertFalse(componentSitePrivateField);
    }

    @Test
    public void testVerifyComponentSiteSearchEngineVisibilityValue() {
        Response response =sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        String componentSiteSearchEngineVisibility = (String) componentSiteValue.get("searchEngineVisibility");
        Assert.assertNotNull(componentSiteSearchEngineVisibility);
    }

    @Test
    public void testVerifyComponentSiteSecureLinkToken() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        String componentSiteSecureLinkToken = (String) componentSiteValue.get("secureLinkToken");
        Assert.assertNotNull(componentSiteSecureLinkToken);
    }

    @Test
    public void testVerifyComponentsOrderNumberEqualsThree() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<Map<String, Object>> componentsValue = response.jsonPath().getList("[0].components");
        Map<String, Object> firstComponent = componentsValue.get(0);
        Integer componentsOrder = (Integer) firstComponent.get("order");
        Assert.assertEquals(componentsOrder, Integer.valueOf(3));
    }

    @Test
    public void testVerifyComponentsShowUptimeIsTrue() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<Map<String, Object>> componentsValue = response.jsonPath().getList("[0].components");
        Map<String, Object> firstComponent = componentsValue.get(0);
        Boolean componentsUptime = (Boolean) firstComponent.get("showUptime");
        Assert.assertTrue(componentsUptime);
    }

    @Test
    public void testVerifyComponentsSiteCacheNameField() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        String componentSiteCacheName = (String) componentSiteValue.get("cacheName");
        Assert.assertNotNull(componentSiteCacheName);
    }

    @Test
    public void testVerifycomponentSiteNewRelicServerField() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        String componentSiteNewRelicServer = (String) componentSiteValue.get("newRelicServer");
        Assert.assertNull(componentSiteNewRelicServer);
    }

    @Test
    public void testVerifyComponentsSitePageTypeValue() {
        Response response =sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        String componentsSitePageTypeValue = (String) componentSiteValue.get("pageType");
        Assert.assertEquals(componentsSitePageTypeValue, "PUBLIC");
    }

     @Test
    public void testVerifyComponentsSiteSecretToBypassPrivacyField() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        String componentSiteSecretToBypassPrivacy = (String) componentSiteValue.get("secretToBypassPrivacy");
        Assert.assertNotNull(componentSiteSecretToBypassPrivacy);
    }

    @Test
    public void testVerifyComponentSiteTimezone() {
        Response response =sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        String componentSiteTimezone = (String) componentSiteValue.get("timezone");
        Assert.assertEquals( componentSiteTimezone, "(UTC) Coordinated Universal Time");
    }

    @Test
    public void testVerifyIsThirdPartyFalse() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<Map<String, Object>> componentsValue = response.jsonPath().getList("[0].components");
        Map<String, Object> firstComponent = componentsValue.get(0);
        Boolean componentsThirdPartyValue = (Boolean) firstComponent.get("isThirdParty");
        Assert.assertFalse(componentsThirdPartyValue);
    }
}
