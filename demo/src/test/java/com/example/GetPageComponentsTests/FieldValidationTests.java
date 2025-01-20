package com.example.GetPageComponentsTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class FieldValidationTests extends BaseApiTest {
    @Test
    public void verifyComponentsNameFieldsAreValid() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components");
        List<String> nameList = response.jsonPath().getList("name");
        nameList.forEach(name -> {
            Assert.assertNotNull(name, "Name field should not be null");
            Assert.assertFalse(name.trim().isEmpty(), "Name field should not be empty");
        });
    }

    @Test
    public void testVerifyEmailFormatForUniqueEmails() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components");
        List<Map<String, Object>> itemList = response.jsonPath().getList("$");
        String emailRegex = "^fortuna2-.*@automation.instatus.com$";
        itemList.forEach(item -> {
            String email = (String) item.get("uniqueEmail");
            Assert.assertNotNull(email, "Email field should not be null");
            Assert.assertTrue(email.matches(emailRegex), "Invalid email format: " + email);
        });
    }

    @Test
    public void shouldEnsureIsCollapsedIsFalseForAllComponents() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components");
        List<Boolean> isCollapsedList = response.jsonPath().getList("isCollapsed", Boolean.class);
        isCollapsedList.forEach(item -> {
            Assert.assertFalse(item, "'isCollapsed' should be false");
        });
    }

    @Test
    public void shouldEnsureThirdPartyMonitorIdIsNullForAllComponents() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components");
        List<Object> thirdPartyMonitorIdList = response.jsonPath().getList("thirdPartyMonitorId");
        thirdPartyMonitorIdList.forEach(item -> {
            Assert.assertNull(item, "'thirdPartyMonitorId' should be null");
        });
    }

    @Test
    public void shouldValidateTranslationFieldsAreValid() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components");
        List<Map<String, Object>> translationsList = response.jsonPath().getList("translations");
        Assert.assertNotNull(translationsList, "Translations list should not be null");
        Assert.assertFalse(translationsList.isEmpty(), "Translations list should not be empty");
        translationsList.forEach(translation -> {
            Object name = translation.get("name");
            Assert.assertTrue(name == null || name instanceof String, "'name' should be string or null");
            Object description = translation.get("description");
            Assert.assertTrue(description == null || description instanceof String, "'description' should be string or null");
        });
    }
}
