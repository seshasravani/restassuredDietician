package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import com.CalorieHackers_POJO.DieticianPOJO;
import com.CalorieHackers_Utilities.ConfigReader;
import com.CalorieHackers_Utilities.LoggerLoad;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class DieticianGetById {

    private Response response;
    private final String baseUri = ConfigReader.getKeyValues("BASE_URL");
    private static String authToken;

    static {
        LoggerLoad.info("This is a test log message");
    }

    // === Background step ===
    @Given("Admin has a valid auth token")
    public void admin_has_a_valid_auth_token() {
        // Hardcoded token for now
        authToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNDAxQGdtYWlsLmNvbSIsImlhdCI6MTc1NDA3ODUwNCwiZXhwIjoxNzU0MTA3MzA0fQ.h86WWLVpfoVzyTkcpZDR4hX66XebdndLZmb6I1-etGh6ioFA-yuh6xAob0jvLvmHxKyAew1qXEC2nLXFmtfahg";
        LoggerLoad.info("Using hardcoded Auth Token: " + authToken);
    }

    @Given("Admin create GET request")
    public void admin_create_get_request() {
        // No setup needed for GET
    }

    @When("Admin send GET http request with endpoint")
    public void admin_send_get_http_request_with_endpoint() {
        String dieticianId = "123";  // Hardcoded for now
        String endpoint = ConfigReader.getKeyValues("get.dietician.by.id.endpoint") + "/" + dieticianId;

        response = given()
                .baseUri(baseUri)
                .header("Authorization", authToken)
                .when()
                .get(endpoint);

        LoggerLoad.info("Response Body: \n" + response.prettyPrint());
        //LoggerLoad.info("Response Body: \n" + response.asString());


    }

    @Then("Admin receives 200 ok with details of the dietician id")
    public void admin_receives_200_ok_with_details_of_the_dietician_id() {
        assertEquals(200, response.getStatusCode());
        DieticianPOJO dietician = response.as(DieticianPOJO.class);
        LoggerLoad.info("Verified Dietician ID: " + dietician.getId());
        assertEquals(123, dietician.getId());
       // LoggerLoad.info("Verified Dietician ID: " + dietician.getId());
    }
}
