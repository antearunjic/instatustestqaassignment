package com.example.GetPageComponentsTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class DataValidationTests extends BaseApiTest {
    @Test
    public void verifyCreatedAtFieldHasValidDateFormat() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components");
        List<String> createdAtList = response.jsonPath().getList("createdAt");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        for (String createdAt : createdAtList) {
            Assert.assertNotNull(createdAt, "'createdAt' field should not be null");
            try {
                dateFormat.parse(createdAt);
            } catch (ParseException e) {
                Assert.fail("Invalid 'createdAt' date format: " + createdAt);
            }
        }
    }

    @Test
    public void testGetPageComponentsStatusIsOperational() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components");
        List<String> statuses = response.jsonPath().getList("status");
        statuses.forEach(status -> Assert.assertEquals("OPERATIONAL", status, "Status should be 'OPERATIONAL'"));
    }

    @Test
    public void testGetPageComponentsInternalStatusIsOperational() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components");
        List<String> statuses = response.jsonPath().getList("internalStatus");
        statuses.forEach(status -> Assert.assertEquals("OPERATIONAL", status, "Internal status should be 'OPERATIONAL'"));
    }
}
