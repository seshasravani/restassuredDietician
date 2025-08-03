package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.testng.Assert;

import com.CalorieHackers_POJO.TestDataPOJO;
import com.CalorieHackers_Utilities.ConfigReader;
import com.CalorieHackers_Utilities.JsonDataReader;
import com.CalorieHackers_Utilities.LoggerLoad;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetMorbiditybyTestName {
	
	private TestDataPOJO currentTestData;
	private static final String JSON_DATA_PATH = "src/test/resources/TestData/TestData.json";
	private RequestSpecification request; 
	private Response response;
	  private String adminToken;
	    private String dieticianEmail;
	    private String dieticianPassword;
	    private String dieticianToken;
	    private String patientToken;
	
	    private void prepareRequest(String scenarioName) {
	        System.out.println("Loading scenario: '" + scenarioName + "'");
	        try {
	            currentTestData = JsonDataReader.getAllTestCase(JSON_DATA_PATH, scenarioName);
	            if (currentTestData == null) {
	                throw new RuntimeException("No test data found for scenario: " + scenarioName);
	            }
	        } catch (Exception e) {
	            System.err.println("Error loading test data for scenario '" + scenarioName + "': " + e.getMessage());
	            throw e;
	        }
	        LoggerLoad.info("Preparing request for scenario: " + scenarioName);

	        request = given().baseUri(ConfigReader.getKeyValues("BASE_URL"));

	        String authType = currentTestData.getAuthType();
	        if (authType != null) {
	            switch(authType) {
	                case "Admin Token":
	                    request.header("Authorization", "Bearer " + adminToken);
	                    break;
	                case "Dietician Token":
	                    request.header("Authorization", "Bearer " + dieticianToken);
	                    break;
	                case "Patient Token":
	                    request.header("Authorization", "Bearer " + patientToken);
	                    break;
	                case "No Auth":
	                    // no Authorization header
	                    break;
	                default:
	                    throw new RuntimeException("Unsupported auth type: " + authType);
	            }
	        }
	        LoggerLoad.info("✅ Request prepared for: " + scenarioName);
	    }
	 
	  @Given("Admin logs in with valid credentials")
	    public void admin_logs_in() {
	        // Build request directly with login details
	    	  response = given()
	            .baseUri(ConfigReader.getKeyValues("BASE_URL"))
	            .contentType(ContentType.JSON)
	            .body("{ \"userLoginEmail\": \"Team401@gmail.com\", \"password\": \"test\" }")
	            .post("/login");

	        // Verify login success (200 OK)
	        response.then().statusCode(200);

	     // Save to class variable
	        adminToken = response.jsonPath().getString("token");

	        System.out.println("Admin token: " + adminToken);
	    }
	    

	    @When("Admin creates a new dietician with valid details")
	    public void admin_creates_dietician() {
	        // Generate unique values
	        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String uniqueEmail = "dietician_" + timestamp + "@example.com";
	        String uniqueContact = "9" + (100000000 + new Random().nextInt(899999999));
	        String uniqueDob = "1990-01-05T00:00:00.000Z"; // Or generate random if needed

	        String dieticianJson = "{\n" +
	                "  \"ContactNumber\": \"" + uniqueContact + "\",\n" +
	                "  \"DateOfBirth\": \"" + uniqueDob + "\",\n" +
	                "  \"Education\": \"MD\",\n" +
	                "  \"Email\": \"" + uniqueEmail + "\",\n" +
	                "  \"Firstname\": \"AmyE\",\n" +
	                "  \"HospitalCity\": \"AnotherCity\",\n" +
	                "  \"HospitalName\": \"Wellness Hospital\",\n" +
	                "  \"HospitalPincode\": \"654321\",\n" +
	                "  \"HospitalStreet\": \"Second Street\",\n" +
	                "  \"Lastname\": \"ShunE\"\n" +
	                "}";

	        response = given()
	                .baseUri(ConfigReader.getKeyValues("BASE_URL"))
	                .header("Authorization", "Bearer " + adminToken)
	                .contentType(ContentType.JSON)
	                .body(dieticianJson)
	                .when()
	                .post("/dietician");  // ✅ Dietician endpoint

	        System.out.println("Response: " + response.asPrettyString());

	        dieticianEmail = response.jsonPath().getString("Email");
	        dieticianPassword = response.jsonPath().getString("loginPassword"); // Adjust keys if needed
	    }

	    @Then("Admin receives dietician credentials in the response")
	    public void verify_dietician_credentials() {
	    	int statusCode = response.getStatusCode();
	        if (statusCode == 200 || statusCode == 201) {
	            String email = response.jsonPath().getString("Email");
	            Assert.assertNotNull(email, "Dietician email is null");
	            // other assertions...
	        } else {
	            // For error responses, no need to check email
	            LoggerLoad.warn("Response status " + statusCode + ", no dietician email expected.");
	        }
	    }

	    @Given("Dietician has email and password from previous step")
	    public void dietician_has_email_and_password_from_previous_step() {
	        assertNotNull(dieticianEmail, "Dietician email is null from previous step");
	        assertNotNull(dieticianPassword, "Dietician password is null from previous step");
	    }

	    @When("Dietician send Post request with email and password")
	    public void dietician_send_post_request_with_email_and_password() {
	    	 response = given()
	                .baseUri(ConfigReader.getKeyValues("BASE_URL"))
	                .contentType(ContentType.JSON)
	                .body("{ \"userLoginEmail\": \"" + dieticianEmail + "\", \"password\": \"" + dieticianPassword + "\" }")
	                .when()
	                .post("/login");

	        System.out.println("Dietician login response: " + response.asPrettyString());
	    }

	    @Then("daitician recieved dietician token in the response creation is successful with valid response")
	    public void dietician_recieved_token() {
	    	response.then().statusCode(200);
	        dieticianToken = response.jsonPath().getString("token");
	        assertNotNull(dieticianToken, "Dietician token is null");
	        System.out.println("Dietician token: " + dieticianToken);

	    }
	    
	    //Scenario
	    @Given("Patient create GET request to retrieve morbididty by name")
	    public void patient_create_get_request_to_retrieve_morbididty_by_name() {
	        prepareRequest("Set Patient Token Check patient is able to retrieve morbidity by name");
	    }

	    @When("Patient send GET http request with endpoint  to retrieve morbididty by name")
	    public void patient_send_get_http_request_with_endpoint_to_retrieve_morbididty_by_name() {
	        response = request.when().get(currentTestData.getEndpoint());
	    }

	    @Then("Dietician recieve unauthorized")
	    public void dietician_receives_unauthorized() {
	        response.then().statusCode(currentTestData.getExpectedStatusCode());
	        assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
	        assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	    }

	    @Then("Patient recieve Forbidden")
	    public void patient_recieve_forbidden() {
	        response.then().statusCode(currentTestData.getExpectedStatusCode());
	        assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
	        assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	    }

	    @Given("admin create GET request to retrieve morbidity byname")
	    public void admin_create_get_request_to_retrieve_morbidity_byname() {
	        prepareRequest("Set Admin Token Check admin able to retrieve morbidity by name");
	    }

	    @When("admin send GET http request with endpoint  to retrieve morbididty by name")
	    public void admin_send_get_http_request_with_endpoint_to_retrieve_morbididty_by_name() {
	        response = request.when().get(currentTestData.getEndpoint());
	    }

	    @Then("admin recieve ok with details of the patient id")
	    public void admin_recieve_ok_with_details_of_the_patient_id() {
	        response.then().statusCode(currentTestData.getExpectedStatusCode());
	        assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
	        assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	        response.then().log().body();
	    }

	    @When("admin send POST http request with endpoint  to retrieve morbididty by name")
	    public void admin_send_post_http_request_with_endpoint_to_retrieve_morbididty_by_name() {
	        prepareRequest("Set Admin Token Check admin able to retrieve morbidity by name with invalid method");
	        response = request.when().post(currentTestData.getEndpoint());
	    }

	    @Then("admin recieve method not allowed")
	    public void admin_recieve_method_not_allowed() {
	        response.then().statusCode(currentTestData.getExpectedStatusCode());
	        assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
	        assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	    }

	    @Then("admin recieve  not found")
	    public void admin_recieve_not_found() {
	        response.then().statusCode(currentTestData.getExpectedStatusCode());
	        assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
	        assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	    }

	    @When("admin send GET http request with invalid endpoint  to retrieve morbididty by name")
	    public void admin_send_get_http_request_with_invalid_endpoint_to_retrieve_morbididty_by_name() {
	        prepareRequest("Set Admin Token Check admin able to retrieve morbidity by name with invalid endpoint");
	        response = request.when().get(currentTestData.getEndpoint());
	    }

	    @Given("Dietician create GET request to retrieve morbidityby name")
	    public void dietician_create_get_request_to_retrieve_morbidityby_name() {
	        prepareRequest("Set Dietician Token Check dietician able to retrieve morbidity by name");
	    }

	    @When("Dietician send GET http request with endpoint  to retrieve morbididty by name")
	    public void dietician_send_get_http_request_with_endpoint_to_retrieve_morbididty_by_name() {
	        response = request.when().get(currentTestData.getEndpoint());
	    }

	    @Then("Dietician recieve  ok with details of the patient id")
	    public void dietician_recieve_ok_with_details_of_the_patient_id() {
	        response.then().statusCode(currentTestData.getExpectedStatusCode());
	        assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
	        assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	        response.then().log().body();
	    }

	    @When("Dietician send POST http request with endpoint  to retrieve morbididty by name")
	    public void dietician_send_post_http_request_with_endpoint_to_retrieve_morbididty_by_name() {
	        prepareRequest("Set Dietician Token Check dietician able to retrieve morbidity by name with invalid method");
	        response = request.when().post(currentTestData.getEndpoint());
	    }

	    @Then("Dietician recieve method not allowed")
	    public void dietician_recieve_method_not_allowed() {
	        response.then().statusCode(currentTestData.getExpectedStatusCode());
	        assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
	        assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	    }

	    @Then("Dietician recieve  not found")
	    public void dietician_recieve_not_found() {
	        response.then().statusCode(currentTestData.getExpectedStatusCode());
	        assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
	        assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	    }

	    @When("Dietician send GET http request with invalid endpoint  to retrieve morbididty by name")
	    public void dietician_send_get_http_request_with_invalid_endpoint_to_retrieve_morbididty_by_name() {
	        prepareRequest("Set Dietician Token Check dietician able to retrieve morbidity by name with invalid endpoint");
	        response = request.when().get(currentTestData.getEndpoint());
	    }
}