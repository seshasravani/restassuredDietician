package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import org.testng.Assert;
import com.CalorieHackers_POJO.TestDataPOJO;
import com.CalorieHackers_Utilities.ConfigReader;
import com.CalorieHackers_Utilities.JsonDataReader;
import com.CalorieHackers_Utilities.LoggerLoad;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreatePatient_Post {

	private TestDataPOJO currentTestData;
	private static final String JSON_DATA_PATH = ConfigReader.getKeyValues("JSON_PATH");
	private RequestSpecification request;
	private Response response;
	String adminToken = userLogin_POST_SD.adminToken;
	String dieticianToken = userLogin_POST_SD.dieticianToken;
	String patientToken = userLogin_POST_SD.patientToken;
	public static int patientId;
	public static String patientEmail;

	private void prepareRequest(String scenarioName) {
		System.out.println("Loading scenario: " + scenarioName);
		currentTestData = JsonDataReader.getAllTestCase(JSON_DATA_PATH, scenarioName);
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
				break;
			default:
				throw new RuntimeException("Unsupported auth type: " + authType);
			}
		}
	}

	@Given("Dietician creates POST request by entering valid data into the form-data key and value fields")
	public void dietician_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() {
		prepareRequest("Set no auth Check dietician able to create patient with valid data");
	}

	@When("Dietician sends POST http request with endpoint")
	public void dietician_sends_post_http_request_with_endpoint() {
		try {
			ObjectMapper mapper = new ObjectMapper();

			String patientInfoJson = mapper.writeValueAsString(currentTestData.getPatientinfo());

			System.out.println("Serialized patientInfo JSON:");
			System.out.println(patientInfoJson);

			response = request.multiPart("patientInfo", patientInfoJson, "application/json")

					.request(currentTestData.getMethod(), currentTestData.getEndpoint());

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to serialize patientInfo or send request: " + e.getMessage());
		}
	}

	@Then("Dietician receives unauthorized")
	public void dietician_receives_unauthorized() {
		// Log actual vs expected values
		LoggerLoad.info(" Expected Status Code: " + currentTestData.getExpectedStatusCode());
		LoggerLoad.info(" Actual Status Code: " + response.getStatusCode());
		LoggerLoad.info(" Expected Status Line: " + currentTestData.getExpectedStatusLine());
		LoggerLoad.info(" Actual Status Line: " + response.getStatusLine());
		LoggerLoad.info(" Expected Content-Type: " + currentTestData.getExpectedContentType());
		LoggerLoad.info(" Actual Content-Type: " + response.getContentType());

		// Validate all fields
		assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode(), "Status code mismatch");
		assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine(), " Status line mismatch");
		assertEquals(response.getContentType(), currentTestData.getExpectedContentType(), " Content-Type mismatch");
	}

	// Admin steps

	@Given("Admin creates POST request by entering valid data into the form-data key and value fields")
	public void admin_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() {
		prepareRequest("Set admin bearer token Check admin able to create patient with valid data and admin token");
	}

	@When("Admin sends POST http request with endpoint")
	public void admin_sends_post_http_request_with_endpoint() {
		try {
			// 1. Create ObjectMapper
			ObjectMapper mapper = new ObjectMapper();

			// 2. Convert PatientInfo object to JSON string
			String patientInfoJson = mapper.writeValueAsString(currentTestData.getPatientinfo());

			// 3. Optional: print payload to debug
			System.out.println("Serialized patientInfo JSON:");
			System.out.println(patientInfoJson);

			// 4. Build the request
			response = request.multiPart("patientInfo", patientInfoJson, "application/json")
					// Optional: if file is included
					// .multiPart("file", new File(currentTestData.getFilePath()),
					// "application/pdf")
					.request(currentTestData.getMethod(), currentTestData.getEndpoint());

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to serialize patientInfo or send request: " + e.getMessage());
		}
	}

	@Then("Admin receives forbidden")
	public void admin_receives_forbidden() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 403, "Expected status code 403");

		String contentType = response.getHeader("Content-Type");
		Assert.assertTrue(contentType.contains("application/json") || contentType.contains("text/plain"),
				"Unexpected content type: " + contentType);

		// Optional: Log the body for clarity
		System.out.println("Forbidden response body: " + response.getBody().asString());
	}
	// Patient steps

	@Given("Patient creates POST request by entering valid data into the form-data key and value fields")
	public void patient_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() {
		prepareRequest(
				"Set patient bearer token Check patient able to create patient with valid data and patient token");
	}

	@When("Patient sends POST http request with endpoint")
	public void patient_sends_post_http_request_with_endpoint() {

		try {
			// 1. Create ObjectMapper
			ObjectMapper mapper = new ObjectMapper();

			// 2. Convert PatientInfo object to JSON string
			String patientInfoJson = mapper.writeValueAsString(currentTestData.getPatientinfo());

			// 3. Optional: print payload to debug
			System.out.println("Serialized patientInfo JSON:");
			System.out.println(patientInfoJson);

			// 4. Build the request
			response = request.multiPart("patientInfo", patientInfoJson, "application/json")
					// Optional: if file is included
					// .multiPart("file", new File(currentTestData.getFilePath()),
					// "application/pdf")
					.request(currentTestData.getMethod(), currentTestData.getEndpoint());

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to serialize patientInfo or send request: " + e.getMessage());
		}
	}

	@Then("Patient receives forbidden")
	public void patient_receives_forbidden() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 403, "Expected status code 403");

		String contentType = response.getHeader("Content-Type");
		Assert.assertTrue(contentType.contains("application/json") || contentType.contains("text/plain"),
				"Unexpected content type: " + contentType);

		// Optional: Log the body for clarity
		System.out.println("Forbidden response body: " + response.getBody().asString());
	}
//	    assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode());
//	    assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine());
//	    assertEquals(response.getContentType(), currentTestData.getExpectedContentType());
//	}

	// Dietician create with mandatory + additional details

	@Given("Dietician creates POST request by entering valid mandatory and additional data into the form-data fields")
	public void dietician_creates_post_request_by_entering_valid_mandatory_and_additional_data_into_the_form_data_fields() {
		prepareRequest("Set dietician bearer token Check dietician able to create patient with valid data and token");
	}

	@Then("Dietician receives success message with response body containing generated ID and password")
	public void dietician_receives_success_message_with_response_body_containing_generated_id_and_password() {

		response.then().statusCode(201);

		JsonPath js = response.jsonPath();
		patientId = js.getInt("patientId");
		patientEmail = js.getString("Email");
		Assert.assertNotNull(patientId, "Generated patientId is null");
		LoggerLoad.info("Patient ID: " + patientId);
		LoggerLoad.info("Patient Email: " + patientEmail);

	}
	// Dietician create with only mandatory details

	@Given("Dietician creates POST request with only valid mandatory details in form-data")
	public void dietician_creates_post_request_with_only_valid_mandatory_details_in_form_data() {
		prepareRequest("Check dietician able to create patient only with valid mandatory details");
	}

	// Dietician create with only additional details

	@Given("Dietician creates POST request with only valid additional details in form-data")
	public void dietician_creates_post_request_with_only_valid_additional_details_in_form_data() {
		prepareRequest("Check dietician able to create patient only with valid additional details");
	}

	@Then("Dietician receives Bad request")
	public void dietician_receives_bad_request() {
		assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode());
		assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine());
	}

	// Dietician create with invalid mandatory details

	@Given("Dietician creates POST request with invalid mandatory details in form-data")
	public void dietician_creates_post_request_with_invalid_mandatory_details_in_form_data() {
		prepareRequest("Check dietician able to create patient with invalid data (mandatory details)");
	}

	// Dietician create with valid mandatory and invalid additional details

	@Given("Dietician creates POST request with valid mandatory and invalid additional details in form-data")
	public void dietician_creates_post_request_with_valid_mandatory_and_invalid_additional_details_in_form_data() {
		prepareRequest(
				"Check dietician able to create patient with valid mandatory fields and invalid data (additional details)");
	}

	// Dietician PUT request with valid data

	@Given("Dietician creates PUT request with valid data in form-data")
	public void dietician_creates_put_request_with_valid_data_in_form_data() {
		prepareRequest("Check dietician able to create patient with valid data and invalid method");
	}

	@When("Dietician sends PUT http request with endpoint")
	public void dietician_sends_put_http_request_with_endpoint() {
		try {
			// 1. Create ObjectMapper
			ObjectMapper mapper = new ObjectMapper();

			// 2. Convert PatientInfo object to JSON string
			String patientInfoJson = mapper.writeValueAsString(currentTestData.getPatientinfo());

			// 3. Optional: print payload to debug
			System.out.println("Serialized patientInfo JSON:");
			System.out.println(patientInfoJson);

			// 4. Build the request
			response = request.multiPart("patientInfo", patientInfoJson, "application/json")
					// Optional: if file is included
					// .multiPart("file", new File(currentTestData.getFilePath()),
					// "application/pdf")
					.request(currentTestData.getMethod(), currentTestData.getEndpoint());

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to serialize patientInfo or send request: " + e.getMessage());
		}
	}

	// Dietician POST request with valid data but invalid endpoint

	@Given("Dietician creates POST request with valid data in form-data")
	public void dietician_creates_post_request_with_valid_data_in_form_data() {
		prepareRequest("Check dietician able to create patient with valid data and invalid endpoint");
	}

	@When("Dietician sends POST http request with invalid endpoint")
	public void dietician_sends_post_http_request_with_invalid_endpoint() {
		try {
			// 1. Create ObjectMapper
			ObjectMapper mapper = new ObjectMapper();

			// 2. Convert PatientInfo object to JSON string
			String patientInfoJson = mapper.writeValueAsString(currentTestData.getPatientinfo());

			// 3. Optional: print payload to debug
			System.out.println("Serialized patientInfo JSON:");
			System.out.println(patientInfoJson);

			// 4. Build the request
			response = request.multiPart("patientInfo", patientInfoJson, "application/json")
					// Optional: if file is included
					// .multiPart("file", new File(currentTestData.getFilePath()),
					// "application/pdf")
					.request(currentTestData.getMethod(), currentTestData.getEndpoint());

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to serialize patientInfo or send request: " + e.getMessage());
		}
	}

	@Then("Dietician receives not found")
	public void dietician_receives_not_found() {
		assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode());
		assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine());
		assertEquals(response.getContentType(), currentTestData.getExpectedContentType());
	}

	// Dietician POST request with valid data and invalid content type

	@Given("Dietician creates POST request with valid data and invalid content type in form-data")
	public void dietician_creates_post_request_with_valid_data_and_invalid_content_type_in_form_data() {

		try {
			ObjectMapper mapper = new ObjectMapper();
			String patientInfoJson = mapper.writeValueAsString(currentTestData.getPatientinfo());

			prepareRequest(currentTestData.getScenarioName());

			request = request.header("Content-Type", "text/plain") // intentionally wrong content type
					.multiPart("patientInfo", patientInfoJson, "text/plain");

		} catch (JsonProcessingException e) {
			Assert.fail("Failed to serialize patientInfo JSON: " + e.getMessage());
		}

	}

	@Then("Dietician receives unsupported media type")
	public void dietician_receives_unsupported_media_type() {
		assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode());
		assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine());
		assertEquals(response.getContentType(), currentTestData.getExpectedContentType());
	}

	@Then("Dietician receives method not allowed")
	public void dietician_receives_method_not_allowed_error() {
		response.then().statusCode(405);
		System.out.println("Received 405 Method Not Allowed as expected");
		String message = response.jsonPath().getString("message");
		System.out.println("Error message: " + message);
	}
}
