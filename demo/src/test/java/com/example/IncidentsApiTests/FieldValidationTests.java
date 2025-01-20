package com.example.IncidentsApiTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class FieldValidationTests extends BaseApiTest {
    @Test
    public void testCheckupdatedsField() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<Map<String, Object>> updatesValue = response.jsonPath().getList("[0].updates");
        Assert.assertNotNull("Updates field should not be null");
        Assert.assertFalse(updatesValue.isEmpty());
    }

    @Test
    public void testVerifyComponentSiteField() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents");
        Map<String, Object> componentSiteValue = response.jsonPath().getMap("[0].components[0].site");
        Assert.assertNotNull("Component site values should not be null");
        Assert.assertFalse(componentSiteValue.isEmpty());
    }

    @Test
    public void testVerifyUpdatedsIdField() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<Map<String, Object>> updatesValue = response.jsonPath().getList("[0].updates");
        Map<String, Object> firstUpdate = updatesValue.get(0);
        String updatesId = (String) firstUpdate.get("id");
        Assert.assertFalse(updatesId.isEmpty());     
    }

    @Test
    public void testVerifyUpdatedsMessageField() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<Map<String, Object>> updatesValue = response.jsonPath().getList("[0].updates");
        Map<String, Object> firstUpdate = updatesValue.get(0);
        String updatesMessage = (String) firstUpdate.get("message");
        Assert.assertFalse(updatesMessage.isEmpty());    
    }

    @Test
    public void testVerifyUpdatesMessageHtmlField() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<Map<String, Object>> updatesValue = response.jsonPath().getList("[0].updates");
        Map<String, Object> firstUpdate = updatesValue.get(0);
        String updatesMessage = (String) firstUpdate.get("messageHtml");
        Assert.assertFalse(updatesMessage.isEmpty());   
    }

    @Test
    public void testVerifyComponentsField() {
        Response response =sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<Map<String, Object>> componentsValue = response.jsonPath().getList("[0].components");
        Assert.assertFalse(componentsValue.isEmpty());
    }

    @Test
    public void testVerifyUpdatesTranslationsField() {
        Response response = sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        Map<String, Object> updatesTranslations = response.jsonPath().getMap("[0].updates[0].translations");
        if (updatesTranslations != null) {
            String name = (String) updatesTranslations.get("name");
            String description = (String) updatesTranslations.get("description");
            Assert.assertFalse(name != null && description != null);
            Assert.assertFalse(name instanceof String && description instanceof String);
        }       
    }

    @Test
    public void testVerifyComponentsIdField() {
        Response response =sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<Map<String, Object>> componentsValue = response.jsonPath().getList("[0].components");
        Map<String, Object> firstComponent = componentsValue.get(0);
        String componentsId = (String) firstComponent.get("id");
        Assert.assertFalse(componentsId.isEmpty()); 
    }

    @Test
    public void testVerifyIncidentNameFields() {
        Response response =sendGetRequest("v1/cl2pxld6h45000iln8lwv81ai3/incidents"); 
        List<String> incidentNameList = response.jsonPath().getList("name");
        Assert.assertFalse(incidentNameList.isEmpty());
    }
}