package com.CalorieHackers_StepDefinition;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

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

public class CreatePatient_Post {
	 private TestDataPOJO currentTestData;
	    private static final String JSON_DATA_PATH = "src/test/resources/TestData/TestData.json";
	    private RequestSpecification request; 
	    private Response response;
	    private String adminToken;
	    private String dieticianEmail;
	    private String dieticianPassword;
	    private String dieticianToken;
	    private String patientToken;

	    // Prepare request with base URI and authorization header as per scenario
	    private void prepareRequest(String scenarioName) {
	        System.out.println("Looking for scenario: [" + scenarioName + "]");
	        currentTestData = JsonDataReader.getAllTestCase(JSON_DATA_PATH, scenarioName);
	        LoggerLoad.info("Preparing request for scenario: " + scenarioName);
	        request = given()
	                    .baseUri(ConfigReader.getKeyValues("BASE_URL"));

	        // Add Authorization header based on authType
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
	                    // No header added
	                    break;
	                default:
	                    throw new RuntimeException("Unsupported auth type: " + authType);
	            }
	        }
	    }

	    // ========== LOGIN STEPS ==========

	    @Given("Admin logs in with valid credentials")
	    public void admin_logs_in() {
	        response = given()
	                .baseUri(ConfigReader.getKeyValues("BASE_URL"))
	                .contentType(ContentType.JSON)
	                .body("{ \"userLoginEmail\": \"Team401@gmail.com\", \"password\": \"test\" }")
	                .post("/login");

	        response.then().statusCode(200);
	        adminToken = response.jsonPath().getString("token");
	        System.out.println("Admin token: " + adminToken);
	    }

	    @When("Admin creates a new dietician with valid details")
	    public void admin_creates_dietician() {
	        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String uniqueEmail = "dietician_" + timestamp + "@example.com";
	        String uniqueContact = "9" + (100000000 + new Random().nextInt(899999999));
	        String uniqueDob = "1990-01-05T00:00:00.000Z";

	        String dieticianJson = String.format("""
	        {
	          "ContactNumber": "%s",
	          "DateOfBirth": "%s",
	          "Education": "MD",
	          "Email": "%s",
	          "Firstname": "AmyE",
	          "HospitalCity": "AnotherCity",
	          "HospitalName": "Wellness Hospital",
	          "HospitalPincode": "654321",
	          "HospitalStreet": "Second Street",
	          "Lastname": "ShunE"
	        }
	        """, uniqueContact, uniqueDob, uniqueEmail);

	        response = given()
	                .baseUri(ConfigReader.getKeyValues("BASE_URL"))
	                .header("Authorization", "Bearer " + adminToken)
	                .contentType(ContentType.JSON)
	                .body(dieticianJson)
	                .post("/dietician");

	        System.out.println("Response: " + response.asPrettyString());

	        dieticianEmail = response.jsonPath().getString("Email");
	        dieticianPassword = response.jsonPath().getString("loginPassword"); // Adjust if key differs
	    }

	    @Then("Admin receives dietician credentials in the response")
	    public void verify_dietician_credentials() {
	        int statusCode = response.getStatusCode();
	        if (statusCode == 200 || statusCode == 201) {
	            String email = response.jsonPath().getString("Email");
	            Assert.assertNotNull(email, "Dietician email is null");
	        } else {
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

	    // ========== PATIENT CREATION STEPS WITH patientInfo JSON multipart ==========

	    @Given("Dietician creates POST request by entering valid data into the patientInfo JSON field")
	    public void dietician_creates_post_request_by_entering_valid_data_into_the_patientInfo_JSON_field() {
	        prepareRequest("Set dietician bearer token Check dietician able to create patient with valid data and token");
	    }

	    @When("Dietician sends POST http request with patientInfo JSON")
	    public void dietician_sends_post_http_request_with_patientInfo_JSON() {
	        // Build patientInfo JSON string from currentTestData
	        String patientInfoJson = String.format("""
	            {
	              "FirstName": "%s",
	              "LastName": "%s",
	              "ContactNumber": "%s",
	              "Email": "%s",
	              "Allergy": "%s",
	              "FoodPreference": "%s",
	              "CuisineCategory": "%s",
	              "DateOfBirth": "%s"
	            }
	            """,
	            currentTestData.getFirstName(),
	            currentTestData.getLastName(),
	            currentTestData.getContactNumber(),
	            currentTestData.getEmail(),
	            currentTestData.getAllergy(),
	            currentTestData.getFoodPreference(),
	            currentTestData.getCuisineCategory(),
	            currentTestData.getDateOfBirth());
				
		  // File fileToUpload = new File("src/test/resources/sample_file.pdf"); // Replace with your file path

	        response = request
	                .multiPart("patientInfo", patientInfoJson, "application/json")
					.multiPart("file", patientInfoJson, "application/json")
	                .request(currentTestData.getMethod(), currentTestData.getEndpoint());
					
					
				//.multiPart("patientInfo", patientJson, "application/json")
         //.multiPart("file", fileToUpload)
         //.contentType(ContentType.MULTIPART)	

	        LoggerLoad.info("Response status: " + response.getStatusLine());
	        LoggerLoad.info("Sent patientInfo JSON: " + patientInfoJson);
	    }

	    @Then("Dietician receives success message with response body containing generated ID and password")
	    public void dietician_receives_success_message_with_response_body_containing_generated_id_and_password() {
	        assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode());
	        assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine());
	        assertEquals(response.getContentType(), currentTestData.getExpectedContentType());

	        Integer patientId = response.jsonPath().getInt("patientId");
	        Assert.assertNotNull(patientId, "Patient ID should not be null");
	    }

	    @Then("Dietician receives Bad request")
	    public void dietician_receives_bad_request() {
	        assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode());
	        assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine());
	        assertEquals(response.getContentType(), currentTestData.getExpectedContentType());
	    }

	    // ========== NO AUTHORIZATION SCENARIO ==========

	    @Given("Dietician creates POST request by entering valid data into the form-data key and value fields")
	    public void dietician_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() {
	        prepareRequest("Set no auth Check dietician able to create patient with valid data");
	    }

	    @When("Dietician sends POST http request with endpoint")
	    public void dietician_sends_post_http_request_with_endpoint() {
	        response = request
	                .multiPart("FirstName", currentTestData.getFirstName())
	                .multiPart("LastName", currentTestData.getLastName())
	                .multiPart("ContactNumber", currentTestData.getContactNumber())
	                .multiPart("Email", currentTestData.getEmail())
	                .multiPart("Allergy", currentTestData.getAllergy())
	                .multiPart("FoodPreference", currentTestData.getFoodPreference())
	                .multiPart("CuisineCategory", currentTestData.getCuisineCategory())
	                .multiPart("DateOfBirth", currentTestData.getDateOfBirth())
	                .request(currentTestData.getMethod(), currentTestData.getEndpoint());

	        LoggerLoad.info("Response status: " + response.getStatusLine());
	        LoggerLoad.info("Sending form-data with values: FirstName=" + currentTestData.getFirstName() + ", Email=" + currentTestData.getEmail());
	    }

	    @Then("Dietician receives unauthorized")
	    public void dietician_receives_unauthorized() {
	        LoggerLoad.info(" Expected Status Code: " + currentTestData.getExpectedStatusCode());
	        LoggerLoad.info(" Actual Status Code: " + response.getStatusCode());
	        LoggerLoad.info(" Expected Status Line: " + currentTestData.getExpectedStatusLine());
	        LoggerLoad.info(" Actual Status Line: " + response.getStatusLine());
	        LoggerLoad.info(" Expected Content-Type: " + currentTestData.getExpectedContentType());
	        LoggerLoad.info(" Actual Content-Type: " + response.getContentType());

	        assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode(), "Status code mismatch");
	        assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine(), " Status line mismatch");
	        assertEquals(response.getContentType(), currentTestData.getExpectedContentType(), " Content-Type mismatch");
	    }

	    // ========== ADMIN ROLE SCENARIO ==========

	    @Given("Admin creates POST request by entering valid data into the form-data key and value fields")
	    public void admin_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() {
	        prepareRequest("Set admin bearer token Check admin able to create patient with valid data and admin token");
	    }

	    @When("Admin sends POST http request with endpoint")
	    public void admin_sends_post_http_request_with_endpoint() {
	        response = request
	                .multiPart("FirstName", currentTestData.getFirstName())
	                .multiPart("LastName", currentTestData.getLastName())
	                .multiPart("ContactNumber", currentTestData.getContactNumber())
	                .multiPart("Email", currentTestData.getEmail())
	                .multiPart("Allergy", currentTestData.getAllergy())
	                .multiPart("FoodPreference", currentTestData.getFoodPreference())
	                .multiPart("CuisineCategory", currentTestData.getCuisineCategory())
	                .multiPart("DateOfBirth", currentTestData.getDateOfBirth())
	                .request(currentTestData.getMethod(), currentTestData.getEndpoint());

	        LoggerLoad.info("Response status: " + response.getStatusLine());
	    }

	    @Then("Admin receives forbidden")
	    public void admin_receives_forbidden() {
	        assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode());
	        assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine());
	        assertEquals(response.getContentType(), currentTestData.getExpectedContentType());
	    }

	    // ========== PATIENT ROLE SCENARIO ==========

	    @Given("Patient creates POST request by entering valid data into the form-data key and value fields")
	    public void patient_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() {
	        prepareRequest("Set patient bearer token Check patient able to create patient with valid data and patient token");
	    }

	    @When("Patient sends POST http request with endpoint")
	    public void patient_sends_post_http_request_with_endpoint() {
	        response = request
	                .multiPart("FirstName", currentTestData.getFirstName())
	                .multiPart("LastName", currentTestData.getLastName())
	                .multiPart("ContactNumber", currentTestData.getContactNumber())
	                .multiPart("Email", currentTestData.getEmail())
	                .multiPart("Allergy", currentTestData.getAllergy())
	                .multiPart("FoodPreference", currentTestData.getFoodPreference())
	                .multiPart("CuisineCategory", currentTestData.getCuisineCategory())
	                .multiPart("DateOfBirth", currentTestData.getDateOfBirth())
	                .request(currentTestData.getMethod(), currentTestData.getEndpoint());

	        LoggerLoad.info("Response status: " + response.getStatusLine());
	    }

	    @Then("Patient receives forbidden")
	    public void patient_receives_forbidden() {
	        assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode());
	        assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine());
	        assertEquals(response.getContentType(), currentTestData.getExpectedContentType());
	    }

	    // ========== OTHER SCENARIOS: invalid mandatory/additional data, method not allowed, invalid endpoint, unsupported media type, etc. ==========

	    @Given("Dietician creates POST request with only valid mandatory details in form-data")
	    public void dietician_creates_post_request_with_only_valid_mandatory_details_in_form_data() {
	        prepareRequest("Check dietician able to create patient only with valid mandatory details");
	    }

	    @Given("Dietician creates POST request with only valid additional details in form-data")
	    public void dietician_creates_post_request_with_only_valid_additional_details_in_form_data() {
	        prepareRequest("Check dietician able to create patient only with valid additional details");
	    }

	    @Given("Dietician creates POST request with invalid mandatory details in form-data")
	    public void dietician_creates_post_request_with_invalid_mandatory_details_in_form_data() {
	        prepareRequest("Check dietician able to create patient with invalid data (mandatory details)");
	    }

	    @Given("Dietician creates POST request with valid mandatory and invalid additional details in form-data")
	    public void dietician_creates_post_request_with_valid_mandatory_and_invalid_additional_details_in_form_data() {
	        prepareRequest("Check dietician able to create patient with valid mandatory fields and invalid data (additional details)");
	    }

	    @Given("Dietician creates PUT request with valid data in form-data")
	    public void dietician_creates_put_request_with_valid_data_in_form_data() {
	        prepareRequest("Check dietician able to create patient with valid data and invalid method");
	    }

	    @When("Dietician sends PUT http request with endpoint")
	    public void dietician_sends_put_http_request_with_endpoint() {
	        response = request
	                .multiPart("FirstName", currentTestData.getFirstName())
	                .multiPart("LastName", currentTestData.getLastName())
	                .multiPart("ContactNumber", currentTestData.getContactNumber())
	                .multiPart("Email", currentTestData.getEmail())
	                .multiPart("Allergy", currentTestData.getAllergy())
	                .multiPart("FoodPreference", currentTestData.getFoodPreference())
	                .multiPart("CuisineCategory", currentTestData.getCuisineCategory())
	                .multiPart("DateOfBirth", currentTestData.getDateOfBirth())
	                .request(currentTestData.getMethod(), currentTestData.getEndpoint());

	        LoggerLoad.info("Response status: " + response.getStatusLine());
	    }

	    @Then("Dietician receives method not allowed")
	    public void dietician_receives_method_not_allowed() {
	        assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode());
	        assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine());
	        assertEquals(response.getContentType(), currentTestData.getExpectedContentType());
	    }

	    @Given("Dietician creates POST request with valid data in form-data")
	    public void dietician_creates_post_request_with_valid_data_in_form_data() {
	        prepareRequest("Check dietician able to create patient with valid data and invalid endpoint");
	    }

	    @When("Dietician sends POST http request with invalid endpoint")
	    public void dietician_sends_post_http_request_with_invalid_endpoint() {
	        response = request
	                .multiPart("FirstName", currentTestData.getFirstName())
	                .multiPart("LastName", currentTestData.getLastName())
	                .multiPart("ContactNumber", currentTestData.getContactNumber())
	                .multiPart("Email", currentTestData.getEmail())
	                .multiPart("Allergy", currentTestData.getAllergy())
	                .multiPart("FoodPreference", currentTestData.getFoodPreference())
	                .multiPart("CuisineCategory", currentTestData.getCuisineCategory())
	                .multiPart("DateOfBirth", currentTestData.getDateOfBirth())
	                .request(currentTestData.getMethod(), currentTestData.getEndpoint());

	        LoggerLoad.info("Response status: " + response.getStatusLine());
	    }

	    @Then("Dietician receives not found")
	    public void dietician_receives_not_found() {
	        assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode());
	        assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine());
	        assertEquals(response.getContentType(), currentTestData.getExpectedContentType());
	    }

	    @Given("Dietician creates POST request with valid data and invalid content type in form-data")
	    public void dietician_creates_post_request_with_valid_data_and_invalid_content_type_in_form_data() {
	        prepareRequest("Check dietician able to create patient with valid data and invalid content type");
	        request = given()
	                 .baseUri(ConfigReader.getKeyValues("BASE_URL"))
	                 .header("Content-Type", "text/plain");
	    }

	    @Then("Dietician receives unsupported media type")
	    public void dietician_receives_unsupported_media_type() {
	    	assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode());
		    assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine());
		    assertEquals(response.getContentType(), currentTestData.getExpectedContentType());
		}
			
	    @Given("Dietician creates POST request by entering valid mandatory and additional data into the form-data fields")
	    public void dietician_creates_post_request_by_entering_valid_mandatory_and_additional_data_into_the_form_data_fields() {
	        prepareRequest("Set dietician bearer token Check dietician able to create patient with valid data and token");
	    }
	    
}

