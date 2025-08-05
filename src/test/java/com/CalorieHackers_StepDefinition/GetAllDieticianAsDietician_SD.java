package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;

import com.CalorieHackers_POJO.TestDataPOJO;
import com.CalorieHackers_Utilities.JsonDataReader;
import com.CalorieHackers_Utilities.LoggerLoad;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllDieticianAsDietician_SD {

	RequestSpecification request;
	Response response;
	String dieticianToken = userLogin_POST_SD.dieticianToken;
	private TestDataPOJO currentTestData;
	private static final String jsondatapath = "src/test/resources/TestData/TestData.json";

	private void commonRequest(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info(scenarioName);
		LoggerLoad.info(dieticianToken);

		request = given().log().all().header("Authorization", "Bearer " + dieticianToken).contentType(ContentType.JSON);

	}

	@Given("Dietician create GET request to retrieve all dietician")
	public void Dietician_create_get_request_to_retrieve_all_dietician() {
		commonRequest("Check dietician able to retrieve all dietician");
	}

	@When("Dietician send GET http request with endpoint to retrieve all dietician")
	public void Dietician_send_get_http_request_with_endpoint_to_retrieve_all_dietician() {
		response = request.log().all().request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("Dietician send GET http request with endpoint to retrieve all dietician as Dietician"
				+ response.getStatusLine());
	}

	@Then("Dietician recieves {int} ok with response body to retrieve all dietician")
	public void Dietician_recieves_ok_with_response_body_to_retrieve_all_dietician(Integer statusCode) {

		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType());
		LoggerLoad.info("Expected Status Code " + statusCode + " Actual Status code " + response.getStatusCode());
	}

	@Given("Dietician create PUT request to retrieve all dietician")
	public void Dietician_create_put_request_to_retrieve_all_dietician() {

		commonRequest("Check dietician able to retrieve all dietician with invalid method");
	}

	@When("Dietician send PUT http request with endpoint to retrieve all dietician")
	public void Dietician_send_put_http_request_with_endpoint_to_retrieve_all_dietician() {

		response = request.log().all().request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info(
				"Dietician send PUT http request with endpoint to retrieve all dietician " + response.getStatusLine());
	}

	@Then("Dietician recieves {int} method not allowed to retrieve all dietician")
	public void dietician_recieves_method_not_allowed_to_retrieve_all_dietician(Integer statusCode) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType());
		LoggerLoad.info("Expected Status Code " + statusCode + " Actual Status code " + response.getStatusCode());
	}

	@Given("Dietician create GET request to retrieve all dietician with invalid endpoint")
	public void Dietician_create_get_request_to_retrieve_all_dietician_with_invalid_endpoint() {

		commonRequest("Check dietician able to retrieve all dietician with invalid endpoint");
	}

	@When("Dietician send GET http request with invalid endpoint to retrieve all dietician")
	public void Dietician_send_get_http_request_with_invalid_endpoint_to_retrieve_all_dietician() {

		response = request.log().all().request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("Dietician send GET http request with invalid endpoint to retrieve all dietician "
				+ response.getStatusLine());
	}

	@Then("Dietician recieves {int} not found to retrieve all dietician")
	public void Dietician_recieves_not_found_to_retrieve_all_dietician(Integer statusCode) {

		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType());
		LoggerLoad.info("Expected Status Code " + statusCode + " Actual Status code " + response.getStatusCode());

	}
}
