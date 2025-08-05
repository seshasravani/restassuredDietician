package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.CalorieHackers_Utilities.JsonDataReader;

import com.CalorieHackers_POJO.DieticianPOJO;
import com.CalorieHackers_POJO.TestDataPOJO;
import com.CalorieHackers_Utilities.ConfigReader;
import com.CalorieHackers_Utilities.LoggerLoad;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DieticianGetById {

	private Response response;
	private final String baseUri = ConfigReader.getKeyValues("BASE_URL");
	String adminToken = userLogin_POST_SD.adminToken;
	private static final String jsondatapath = ConfigReader.getKeyValues("JSON_PATH");
	private TestDataPOJO currentTestData;
	int dieticianID = createDietician_SD.dieticianID;

	@Given("Admin has a valid auth token")
	public void admin_has_a_valid_auth_token() {
		LoggerLoad.info("Using Auth Token: " + adminToken);
	}

	@Given("Admin create GET request")
	public void admin_create_get_request() {

		LoggerLoad.info("Preparing GET request");

	}

	@When("Admin send GET http request with endpoint")
	public void admin_send_get_http_request_with_endpoint() {
		String endpoint = ConfigReader.getKeyValues("get.dietician.by.id.endpoint") + "/" + dieticianID;

		response = given().baseUri(baseUri).header("Authorization", adminToken).when().get(endpoint);

		LoggerLoad.info("Response Body: \n" + response.prettyPrint());

	}

	@Then("Admin receives 200 ok with details of the dietician id")
	public void admin_receives_200_ok_with_details_of_the_dietician_id() {
		assertEquals(200, response.getStatusCode());
		DieticianPOJO dietician = response.as(DieticianPOJO.class);
		LoggerLoad.info("Verified Dietician ID: " + dietician.getId());

	}

	@Given("Admin creates POST request")
	public void admin_creates_post_request() {

		LoggerLoad.info("Preparing POST request");

	}

	@When("Admin sends POST http request with endpoint for dietician")
	public void admin_sends_post_http_request_with_endpoint() {

		String endpoint = ConfigReader.getKeyValues("get.dietician.by.id.endpoint") + "/" + dieticianID;

		response = given().baseUri(baseUri).header("Authorization", adminToken).when().post(endpoint);

		LoggerLoad.info("Response Body: \n" + response.prettyPrint());
	}

	@Then("Admin receives 405 method not allowed")
	public void admin_receives_method_not_allowed() {

		assertEquals(405, response.getStatusCode());

	}

	// invalid dietId

	@Given("Admin create GET request for invalid Id")
	public void admin_create_get_request_for_invalid_Id() {
		// Load test data for invalid ID scenario from JSON
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath,
				"Check admin able to retrieve dietician by invalid id");
		LoggerLoad.info("Preparing GET request for invalid dietician ID: " + currentTestData.getInvalidDieticianId());
	}

	@When("Admin send GET http request with invalidId endpoint")
	public void admin_send_get_http_request_with_invalidId_endpoint() {
		// Build the endpoint with invalid ID
		String endpointWithInvalidId = currentTestData.getEndpoint() + "/" + currentTestData.getInvalidDieticianId();

		// Print final URL (including base URI if set)
		LoggerLoad.info("Calling URL: " + RestAssured.baseURI + endpointWithInvalidId);

		// Send GET request
		response = given().header("Authorization", adminToken).when().get(endpointWithInvalidId);

		// Log response
		LoggerLoad.info("Response Body: \n" + response.prettyPrint());
	}

	@Then("Admin receives {int} not found")
	public void admin_receives_not_found(Integer expectedStatusCode) {
		// Compare actual response code with expectedStatusCode
		assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
	}

	@Given("Admin create GET request for invalid endpoint")
	public void admin_create_get_request_for_invalid_endpoint() {

		// Load test data for invalid endpoint from JSON
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath,
				"Check admin able to retrieve dietician by id with invalid endpoint");

	}

	@When("Admin send GET http request with invalid endpoint")
	public void admin_send_get_http_request_with_invalid_endpoint() {

		// Build invalid endpoint
		String endpointInvalid = currentTestData.getEndpoint() + "/" + dieticianID;

		// Print final URL (including base URI if set)
		LoggerLoad.info("Calling URL: " + RestAssured.baseURI + endpointInvalid);

		// Send GET request
		response = given().header("Authorization", adminToken).when().get(endpointInvalid);

		// Log response
		LoggerLoad.info("Response Body: \n" + response.prettyPrint());

	}

	@Then("Admin recieves {int} not found")
	public void admin_recieves_not_found(Integer expectedStatusCode) {

		// Compare actual response code with expectedStatusCode
		assertEquals(expectedStatusCode.intValue(), response.getStatusCode());

	}

	@Given("No Authentication is set")
	public void no_authentication_is_set() {
		LoggerLoad.info("No Authentication is set");
	}

	@When("Admin sends GET http request without auth")
	public void admin_sends_get_http_request_without_auth() {
		String endpoint = ConfigReader.getKeyValues("get.dietician.by.id.endpoint") + "/" + dieticianID;

		response = given().baseUri(baseUri)
				// No Authorization header
				.when().get(endpoint);

		LoggerLoad.info("Response Body: \n" + response.prettyPrint());
	}

	@Then("Admin recieves {int} unauthorized")
	public void admin_recieves_unauthorized(Integer statusCode) {
		response.then().statusCode(401);
		LoggerLoad
				.info("Expected Status Code " + statusCode + " Actual Status code " + response.then().statusCode(401));

	}

	@Then("Admin receives {int} unauthorized")
	public void admin_receives_unauthorized(int statusCode) {
		assertEquals(statusCode, response.getStatusCode());
		assertTrue(response.getStatusLine().contains("Unauthorized"));
	}

	@Then("Admin receives 401 unauthorized for noauth")
	public void admin_receives_401_unauthorized() {
		int expectedStatusCode = 401;
		int actualStatusCode = response.getStatusCode();
		assertEquals(expectedStatusCode, actualStatusCode);
	}

}
