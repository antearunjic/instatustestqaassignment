package com.example.GetPageComponentTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DataValidationTests extends BaseApiTest {
    @Test
    public void testVerifyEmailFormatForUniqueEmail() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components/clghp0xjn276266hnos82h6lodz"); 
        String componentUniqueEmail = response.jsonPath().getString("uniqueEmail");
        String emailRegex = "^fortuna2-.*@automation.instatus.com$";
        Assert.assertTrue(componentUniqueEmail.matches(emailRegex));
    }

    @Test
    public void verifyUpdatedAtFieldHasValidDateFormat() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components/clghp0xjn276266hnos82h6lodz");
        String updatedAt = response.jsonPath().getString("updatedAt");  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Assert.assertNotNull(updatedAt, "'createdAt' field should not be null");
        try {
            java.util.Date date = dateFormat.parse(updatedAt); 
            Assert.assertNotNull(date, "'updateddAt' field should have a valid date format: " + updatedAt);
        } catch (ParseException e) {
            Assert.fail("Invalid 'updatedAt' date format: " + updatedAt);
        }
    } 

    @Test
    public void testGetPageComponentStatusIsOperational() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components/clghp0xjn276266hnos82h6lodz");  
        String status = response.jsonPath().getString("status");
        Assert.assertEquals("OPERATIONAL", status, "Expected status to be OPERATIONAL");
    }    

    @Test
    public void testGetPageComponentInternalStatusIsOperational() {
        Response response = sendGetRequest("/v1/clghp0xiu276238hnoskz6nq9r8/components/clghp0xjn276266hnos82h6lodz");  
        String internalStatus = response.jsonPath().getString("status");
        Assert.assertEquals("OPERATIONAL", internalStatus, "Expected internal status to be OPERATIONAL"); 
    } 
}
