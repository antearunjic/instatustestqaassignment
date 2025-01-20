package com.example.GetPageComponentTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

public class FieldValidationTests extends BaseApiTest {
    @Test
    public void verifyComponentNameFieldIsValid() {
        Response response = sendGetRequest("v1/clghp0xiu276238hnoskz6nq9r8/components/clghp0xjn276266hnos82h6lodz");  
        String name = response.jsonPath().getString("name");
        Assert.assertNotNull(name, "Field name should not be null");
    }

    @Test
    public void testVerifySiteIdIsNonNullAndNonEmpty() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components/clghp0xjn276266hnos82h6lodz"); 
        String componentSiteId = response.jsonPath().getString("siteId");
        Assert.assertNotNull(componentSiteId);
        Assert.assertFalse(componentSiteId.isEmpty());
    }

    @Test
    public void testVerifyIsCollapsedNullOrFalse() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components/clghp0xjn276266hnos82h6lodz");
        Boolean isCollapsed = response.jsonPath().getBoolean("isCollapsed");
        Assert.assertNotNull(isCollapsed);
        Assert.assertFalse(isCollapsed);  
    }

    @Test
    public void testVerifyThirdPartyMonitorIdIsNonEmptyOrNull() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components/clghp0xjn276266hnos82h6lodz"); 
        String thirdPartyMonitorId = response.jsonPath().getString("thirdPartyMonitorId");
        if (thirdPartyMonitorId == null) {
            Assert.assertNull(thirdPartyMonitorId);
        } else {
            Assert.assertFalse(thirdPartyMonitorId.isEmpty());
        }
    }

    @Test
    public void shouldValidateOneTranslationFieldsAreEmptyOrReturnType() {
        Response response =sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components/clghp0xjn276266hnos82h6lodz");
        Map<String, Object> translations = response.jsonPath().getMap("translations");  
        Assert.assertNotNull(translations);     
        Object name = translations.get("name");
        Assert.assertTrue(name == null || name.toString().isEmpty(), "'name' should be empty or null.");
        Object description = translations.get("description");
        Assert.assertTrue(description == null || description.toString().isEmpty(), "'description' should be empty or null.");
    }    
}