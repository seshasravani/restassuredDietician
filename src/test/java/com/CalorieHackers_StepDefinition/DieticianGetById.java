package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import com.CalorieHackers_POJO.DieticianPOJO;
import com.CalorieHackers_Utilities.ConfigReader;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class DieticianGetById {

    private Response response;
    private final String baseUri = ConfigReader.getKeyValues("BASE_URL");
    private final String hardcodedToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNDAxQGdtYWlsLmNvbSIsImlhdCI6MTc1NDA3ODUwNCwiZXhwIjoxNzU0MTA3MzA0fQ.h86WWLVpfoVzyTkcpZDR4hX66XebdndLZmb6I1-etGh6ioFA-yuh6xAob0jvLvmHxKyAew1qXEC2nLXFmtfahg";


    @Given("Admin create GET request")
    public void admin_create_get_request() {
        // No setup needed here for GET
    }

    @When("Admin send GET http request with endpoint")
    public void admin_send_get_http_request_with_endpoint() {
        String dieticianId = "123";  // Hardcoded for now, update later if needed
        String endpoint = ConfigReader.getKeyValues("get.dietician.by.id.endpoint") + "/" + dieticianId;

        response = given()
                .baseUri(baseUri)
                .header("Authorization", hardcodedToken)
                .when()
                .get(endpoint);
    }

    @Then("Admin receives 200 ok with details of the dietician id")
    public void admin_receives_200_ok_with_details_of_the_dietician_id() {
        assertEquals(200, response.getStatusCode());
        DieticianPOJO dietician = response.as(DieticianPOJO.class);
        assertEquals(123, dietician.getId());  // id is int in your POJO
    }
}
