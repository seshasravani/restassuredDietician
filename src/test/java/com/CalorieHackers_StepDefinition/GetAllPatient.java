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

public class GetAllPatient {

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
		LoggerLoad.info(" Request prepared for: " + scenarioName);
	}


	@Given("Dietician creates GET request without Authorization header")
	public void dietician_creates_get_request_without_authorization_header() {
		prepareRequest("Set no auth Check dietician able to retrieve all patients");
	}

	@Given("Admin creates GET request with Authorization token")
	public void admin_creates_get_request_with_authorization_token() {
		prepareRequest("Set admin token Check admin able to retrieve all patients");
	}

	@When("Admin sends GET HTTP request with valid endpoint")
	public void admin_sends_get_http_request_with_valid_endpoint() {
		response = request.when().get(currentTestData.getEndpoint());
	}

	@Then("the Dietician receives Unauthorized")
	public void the_dietician_receives_401_unauthorized() {
		response.then().statusCode(currentTestData.getExpectedStatusCode());
		assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
		assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	}

	@Given("Patient creates GET request with Authorization token")
	public void patient_creates_get_request_with_authorization_token() {
		prepareRequest("Set patient token Check patient able to retrieve all patients");
	}

	@When("Patient sends GET HTTP request with valid endpoint")
	public void patient_sends_get_http_request_with_valid_endpoint() {
		response = request.when().get(currentTestData.getEndpoint());
	}

	@When("Dietician sends GET HTTP request with valid endpoint")
	public void dietician_sends_get_http_request_with_valid_endpoint() {
		response = request.when().get(currentTestData.getEndpoint());
	}

	@Then("the Dietician receives OK with response body")
	public void the_dietician_receives_ok_with_response_body() {
		response.then().statusCode(currentTestData.getExpectedStatusCode());
		assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
		assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
		response.then().log().body(); // optional: print response
	}

	@When("Dietician sends PUT HTTP request with valid endpoint")
	public void dietician_sends_put_http_request_with_valid_endpoint() {
		response = request.when().put(currentTestData.getEndpoint());
	}

	@Then("the Dietician receives Method Not Allowed")
	public void the_dietician_receives_method_not_allowed() {
		response.then().statusCode(currentTestData.getExpectedStatusCode());
		assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
		assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	}

	@Then("the Admin receives Forbidden")
	public void the_admin_receives_forbidden() {
		response.then().statusCode(currentTestData.getExpectedStatusCode());
		assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());

	}

	@Then("the Patient receives Forbidden")
	public void the_patient_receives_forbidden() {
		response.then().statusCode(currentTestData.getExpectedStatusCode());
		assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());

	}

	@Given("Dietician creates GET request with Authorization token")
	public void dietician_creates_get_request_with_authorization_token() {
		prepareRequest("Set dietician token Check dietician able to retrieve all patients");
	}

	@Given("Dietician creates PUT request with Authorization token")
	public void dietician_creates_put_request_with_authorization_token() {
		prepareRequest("Check dietician able to retrieve all patient with invalid method PUT");
	}

	@When("Dietician sends GET HTTP request with invalid endpoint")
	public void dietician_sends_get_http_request_with_invalid_endpoint() {
		response = request.when().get(currentTestData.getEndpoint());
	}

	@Then("the Dietician receives Not Found")
	public void the_dietician_receives_not_found() {
		response.then().statusCode(currentTestData.getExpectedStatusCode());
		assertEquals(currentTestData.getExpectedStatusLine(), response.getStatusLine());
		assertEquals(currentTestData.getExpectedContentType(), response.getContentType());
	}

}
