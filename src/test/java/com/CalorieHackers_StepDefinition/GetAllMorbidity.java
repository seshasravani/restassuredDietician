package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import com.CalorieHackers_POJO.TestDataPOJO;
import com.CalorieHackers_Utilities.ConfigReader;
import com.CalorieHackers_Utilities.JsonDataReader;
import com.CalorieHackers_Utilities.LoggerLoad;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllMorbidity {

	private TestDataPOJO currentTestData;
	private static final String JSON_DATA_PATH = ConfigReader.getKeyValues("JSON_PATH");
	private RequestSpecification request;
	private Response response;
	String adminToken = userLogin_POST_SD.adminToken;
	String dieticianToken = userLogin_POST_SD.dieticianToken;
	String patientToken = userLogin_POST_SD.patientToken;

	private void prepareRequest(String scenarioName) {
		LoggerLoad.info("Loading scenario: '" + scenarioName + "'");
		try {
			currentTestData = JsonDataReader.getAllTestCase(JSON_DATA_PATH, scenarioName);
			if (currentTestData == null) {
				throw new RuntimeException("No test data found for scenario: " + scenarioName);
			}
		} catch (Exception e) {
			LoggerLoad.error("Error loading test data for scenario '" + scenarioName + "': " + e.getMessage());
			throw e;
		}
		LoggerLoad.info("Preparing request for scenario: " + scenarioName);

		request = given().baseUri(ConfigReader.getKeyValues("BASE_URL"));

		String authType = currentTestData.getAuthType();
		if (authType != null) {
			switch (authType) {
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
		LoggerLoad.info("Request prepared for: " + scenarioName);
	}

	@Given("Dietician creates GET request to retrieve Morbidity without Authorization header")
	public void dietician_creates_get_request_to_retrieve_morbidity_without_authorization_header() {
		prepareRequest("Set No Auth Check dietician able to retrieve all morbidities details");
	}

	@When("Dietician sends GET HTTP request with valid endpoint to retrieve Morbidity")
	public void dietician_sends_get_http_request_with_valid_endpoint_to_retrieve_morbidity() {
		response = request.when().get(currentTestData.getEndpoint());
	}

	@Then("Dietician receives Unauthorized message")
	public void dietician_receives_unauthorized_message() {
		response.then().statusCode(currentTestData.getExpectedStatusCode());
		assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
		assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	}

	@Given("Patient creates GET request to retrieve Morbidity without Authorization header")
	public void patient_creates_get_request_to_retrieve_morbidity_without_authorization_header() {
		prepareRequest("Set PAtient Token Check pateint is able to retrieve all morbidities details");
	}

	@When("Patient sends GET HTTP request with valid endpoint to retrieve Morbidity")
	public void patient_sends_get_http_request_with_valid_endpoint_to_retrieve_morbidity() {
		response = request.when().get(currentTestData.getEndpoint());
	}

	@Then("Patient receives Forbidden Message")
	public void patient_receives_forbidden_message() {
		response.then().statusCode(currentTestData.getExpectedStatusCode());
		assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
	}

	@Given("Admin creates GET request to retrieve Morbidity without Authorization header")
	public void admin_creates_get_request_to_retrieve_morbidity_without_authorization_header() {
		prepareRequest("Set Admin Token Check admin able to retrieve all morbidities details");
	}

	@When("Admin sends GET HTTP request with valid endpoint to retrieve Morbidity")
	public void admin_sends_get_http_request_with_valid_endpoint_to_retrieve_morbidity() {
		response = request.when().get(currentTestData.getEndpoint());
	}

	@Then("Dietician receives OK message with response body")
	public void dietician_receives_ok_message_with_response_body() {
		response.then().statusCode(currentTestData.getExpectedStatusCode());
		assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
		assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
		response.then().log().body(); // Optional logging
	}

	@Given("Admin creates POST request with Authorization token to retieve Morbididty")
	public void admin_creates_post_request_with_authorization_token_to_retieve_morbididty() {
		prepareRequest("Set Admin Token Check admin able to retrieve all morbidities details with invalid method");
	}

	@When("Admin sends POST HTTP request with valid endpoint to retieve Morbididty")
	public void admin_sends_post_http_request_with_valid_endpoint_to_retieve_morbididty() {
		response = request.when().post(currentTestData.getEndpoint());
	}

	@Then("Admin receives Method Not Allowed message")
	public void admin_receives_method_not_allowed_message() {
		response.then().statusCode(currentTestData.getExpectedStatusCode());
		assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
		assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	}

	@Given("Admin creates GET request with Authorization token to retieve Morbididty")
	public void admin_creates_get_request_with_authorization_token_to_retieve_morbididty() {
		prepareRequest("Set Admin Token Check admin able to retrieve all morbidities details with invalid endpoint");
	}

	@When("Admin sends GET HTTP request with invalid endpoint to retieve Morbididty")
	public void admin_sends_get_http_request_with_invalid_endpoint_to_retieve_morbididty() {
		response = request.when().get(currentTestData.getEndpoint());
	}

	@Then("Dietician receives Not Found message")
	public void dietician_receives_not_found_message() {
		response.then().statusCode(currentTestData.getExpectedStatusCode());
		assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
		assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	}

	@Given("Dietician creates GET request with Authorization token to retieve Morbididty")
	public void dietician_creates_get_request_with_authorization_token_to_retieve_morbididty() {
		prepareRequest("Set Dietician Token Check dietician able to retrieve all morbidities details");
	}

	@When("Dietician sends GET HTTP request with valid endpoint to retieve Morbididty")
	public void dietician_sends_get_http_request_with_valid_endpoint_to_retieve_morbididty() {
		response = request.when().get(currentTestData.getEndpoint());
	}

	@Then("Dietician receives OK with response body message")
	public void dietician_receives_ok_with_response_body_message() {
		response.then().statusCode(currentTestData.getExpectedStatusCode());
		assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
		assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
		response.then().log().body();
	}

	@Given("Dietician creates POST request with Authorization token to retieve Morbididty")
	public void dietician_creates_post_request_with_authorization_token_to_retieve_morbididty() {
		prepareRequest(
				"Set Dietician Token Check dietician able to retrieve all morbidities details with invalid method");
	}

	@When("Dietician sends POST HTTP request with endpoint to retieve Morbididty")
	public void dietician_sends_post_http_request_with_endpoint_to_retieve_morbididty() {
		response = request.when().post(currentTestData.getEndpoint());
	}

	@Then("Dietician receives Method Not Allowed message")
	public void dietician_receives_method_not_allowed_message() {
		response.then().statusCode(currentTestData.getExpectedStatusCode());
		assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
		assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	}

	@When("Dietician sends GET HTTP request with invalid endpoint to retieve Morbididty")
	public void dietician_sends_get_http_request_with_invalid_endpoint_to_retieve_morbididty() {
		response = request.when().get(currentTestData.getEndpoint());
	}

}
