package com.example.GetPagesTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class FeatureValidationTests extends BaseApiTest {
    @Test
    public void testVerifySubscribeBySmsIsFalse() {
        Response response = sendGetRequest("/v1/pages");
        List<Boolean> smsList = response.jsonPath().getList("subscribeBySms", Boolean.class);
        smsList.forEach(sms -> Assert.assertFalse(sms, "subscribeBySms should be false"));
    }

    @Test
    public void testVerifySendSmsNotificationsIsTrue() {
        Response response = sendGetRequest("/v1/pages");
        List<Boolean> notificationList = response.jsonPath().getList("sendSmsNotifications", Boolean.class);
        notificationList.forEach(notification -> Assert.assertTrue(notification,
                "sendSmsNotifications should be true"));
    }
}
