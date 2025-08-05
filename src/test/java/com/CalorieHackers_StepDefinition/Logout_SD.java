package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import com.CalorieHackers_POJO.TestDataPOJO;
import com.CalorieHackers_Utilities.ConfigReader;
import com.CalorieHackers_Utilities.JsonDataReader;
import com.CalorieHackers_Utilities.LoggerLoad;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Logout_SD {

	RequestSpecification request;
	Response response;
	private TestDataPOJO currentTestData;
	String adminToken = userLogin_POST_SD.adminToken;
	String dieticianToken = userLogin_POST_SD.dieticianToken;
	String patientToken = userLogin_POST_SD.patientToken;

	private static final String jsondatapath = ConfigReader.getKeyValues("JSON_PATH");

	@Given("User creates GET request as admin")
	public void user_creates_get_request_as_admin() {

		String scenarioName = "Check admin able to logout";

		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info(scenarioName);
		LoggerLoad.info(adminToken);
		request = given().log().all().header("Authorization", "Bearer " + adminToken).contentType(ContentType.JSON);

	}

	@When("User send GET HTTP request with endpoint for logout")
	public void user_send_get_http_request_with_endpoint_for_logout() {

		response = request.log().all().request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("User send GET HTTP request with endpoint for logout as Admin" + response.getStatusLine());
	}

	@Then("User recieves {int} created with Logout successful message")
	public void user_recieves_created_with_logout_successful_message(Integer statusCode) {
		String resBody = response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine()).extract().asString();
		Assert.assertEquals(currentTestData.getExpectedStatusMessage(), resBody);
		LoggerLoad.info("Expected Status Code " + statusCode + " Actual Status code " + response.getStatusCode());
	}

	@Given("User creates GET request as dietician")
	public void user_creates_get_request_as_dietician() {

		String scenarioName = "Check dietician able to logout";

		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info(scenarioName);
		LoggerLoad.info(dieticianToken);
		request = given().log().all().header("Authorization", "Bearer " + dieticianToken).contentType(ContentType.JSON);
	}

	@Given("User creates GET request as patient")
	public void user_creates_get_request_as_patient() {

		String scenarioName = "Check patient able to logout";

		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info(scenarioName);
		LoggerLoad.info(patientToken);
		request = given().log().all().header("Authorization", "Bearer " + patientToken).contentType(ContentType.JSON);
	}

	@Given("User creates POST request as admin")
	public void user_creates_post_request_as_admin() {

		String scenarioName = "Check admin able to logout  with invalid method";

		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info(scenarioName);
		LoggerLoad.info(adminToken);
		request = given().log().all().header("Authorization", "Bearer " + adminToken).contentType(ContentType.JSON);
	}

	@When("User send POST HTTP request with endpoint for logout")
	public void user_send_post_http_request_with_endpoint_for_logout() {
		response = request.log().all().request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("User send GET HTTP request with endpoint for logout as Admin" + response.getStatusLine());
	}

	@Then("User recieves {int} method not allowed")
	public void user_recieves_method_not_allowed(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine());
	}

	@Given("User creates POST request as dietician")
	public void user_creates_post_request_as_dietician() {

		String scenarioName = "Check dietician able to logout  with invalid method";

		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info(scenarioName);
		LoggerLoad.info(dieticianToken);
		request = given().log().all().header("Authorization", "Bearer " + dieticianToken).contentType(ContentType.JSON);

	}

	@Given("User creates POST request as patient")
	public void user_creates_post_request_as_patient() {

		String scenarioName = "Check patient able to logout  with invalid method";

		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info(scenarioName);
		LoggerLoad.info(patientToken);
		request = given().log().all().header("Authorization", "Bearer " + patientToken).contentType(ContentType.JSON);
	}

}
