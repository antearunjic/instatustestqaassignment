package com.example.GetPagesTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class ColorValidationTests extends BaseApiTest {
    @Test
    public void testVerifyOkColorNotEmpty() {
        Response response = sendGetRequest("/v1/pages");
        List<String> okColors = response.jsonPath().getList("okColor");
        Assert.assertFalse(okColors.isEmpty(), "okColor list should not be empty");
    }

    @Test
    public void testVerifyDisruptedColorsConsistency() {
        Response response = sendGetRequest("/v1/pages");
        List<String> disruptedColors = response.jsonPath().getList("disruptedColor");
        Assert.assertFalse(disruptedColors.isEmpty(), "disruptedColor list should not be empty");
        String firstColor = disruptedColors.get(0);
        disruptedColors.forEach(color -> Assert.assertEquals(color, firstColor,
                "All disruptedColors should be consistent"));
    }
}
