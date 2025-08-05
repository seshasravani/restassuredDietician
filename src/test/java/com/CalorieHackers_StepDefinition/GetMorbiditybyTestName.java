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

public class GetMorbiditybyTestName {

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