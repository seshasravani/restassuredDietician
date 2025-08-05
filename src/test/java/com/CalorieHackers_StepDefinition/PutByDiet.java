package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import com.CalorieHackers_Utilities.JsonDataReader;
import com.CalorieHackers_Utilities.LoggerLoad;
import com.CalorieHackers_POJO.DieticianPOJO;
import com.CalorieHackers_POJO.TestDataPOJO;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutByDiet {

	private RequestSpecification request;
	private Response response;
	private TestDataPOJO testData;
	private DieticianPOJO dietician;
	String adminToken = userLogin_POST_SD.adminToken;
	int dieticianID = createDietician_SD.dieticianID;

	@Given("Admin bearer token was set, and the admin successfully created a dietician account")
	public void admin_bearer_token_was_set_and_the_admin_successfully_created_a_dietician_account() {
		testData = JsonDataReader.getAllTestCase("src/test/resources/TestData/TestData.json",
				"Check admin able to update dietician with valid data , dietician id and token");
		LoggerLoad.info("Using Admin Token: " + adminToken);

	}

	@Given("Admin creates PUT request with valid data")
	public void admin_creates_put_request_with_valid_data() throws JsonProcessingException {
		dietician = new DieticianPOJO();
		dietician.setId(dieticianID);
		dietician.setFirstname(testData.getFirstname());
		dietician.setLastname(testData.getLastname());
		dietician.setContactNumber(testData.getContactNumber());
		dietician.setDateOfBirth(testData.getDateOfBirth());
		dietician.setEmail(testData.getEmail());

		dietician.setHospitalName(testData.getHospitalName());
		dietician.setHospitalStreet(testData.getHospitalStreet());
		dietician.setHospitalCity(testData.getHospitalCity());
		dietician.setHospitalPincode(testData.getHospitalPincode());
		dietician.setEducation(testData.getEducation());

		ObjectMapper mapper = new ObjectMapper();
		String requestJson = mapper.writeValueAsString(dietician);
		LoggerLoad.info("Final JSON being sent: " + requestJson);

		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("id", dietician.getId());

		// Required personal info fields
		requestBody.put("Firstname", dietician.getFirstname());
		requestBody.put("Lastname", dietician.getLastname());
		requestBody.put("ContactNumber", dietician.getContactNumber()); // API expects "contact"
		requestBody.put("DateOfBirth", dietician.getDateOfBirth());
		requestBody.put("Email", dietician.getEmail()); // API expects "userLoginEmail"

		// Hospital info fields (with 'd' prefix)
		requestBody.put("HospitalName", dietician.getHospitalName());
		requestBody.put("HospitalStreet", dietician.getHospitalStreet());
		requestBody.put("HospitalCity", dietician.getHospitalCity());
		requestBody.put("HospitalPincode", dietician.getHospitalPincode());
		requestBody.put("Education", dietician.getEducation());

		LoggerLoad.info("Preparing PUT Request with Body: " + requestBody);

		request = given().header("Authorization", adminToken).contentType("application/json").body(requestBody);
	}

	@When("Admin send PUT http request with endpoint")
	public void admin_send_put_http_request_with_endpoint() {
		String url = "/dietician/" + dieticianID;

		response = request.put(url);
		LoggerLoad.info("Response Body: " + response.getBody().asString());
	}

	@Then("Admin recieves {int} ok and with updated response body")
	public void admin_recieves_ok_and_with_updated_response_body(Integer expectedStatusCode) {
		assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
		assertTrue(response.getStatusLine().contains("OK"));

		assertEquals(dietician.getFirstname(), response.jsonPath().getString("Firstname"));
		assertEquals(dietician.getEmail(), response.jsonPath().getString("Email"));
		assertEquals(dietician.getHospitalName(), response.jsonPath().getString("HospitalName"));
	}
}
