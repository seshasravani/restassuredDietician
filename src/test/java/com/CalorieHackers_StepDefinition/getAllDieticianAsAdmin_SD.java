package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.*;
import com.CalorieHackers_POJO.TestDataPOJO;
import com.CalorieHackers_Utilities.JsonDataReader;
import com.CalorieHackers_Utilities.LoggerLoad;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class getAllDieticianAsAdmin_SD {

	RequestSpecification request;
	Response response;
	String adminToken = userLogin_POST_SD.adminToken;
	private TestDataPOJO currentTestData;
	private static final String jsondatapath = "src/test/resources/TestData/TestData.json";

	private void commonRequest(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info(scenarioName);
		LoggerLoad.info(adminToken);

		request = given().log().all().header("Authorization", "Bearer " + adminToken).contentType(ContentType.JSON);

	}

	@Given("Admin create GET request to retrieve all dietician")
	public void admin_create_get_request_to_retrieve_all_dietician() {
		commonRequest("Check admin able to retrieve all dietician with invalid endpoint");
	}

	@When("Admin send GET http request with endpoint to retrieve all dietician")
	public void admin_send_get_http_request_with_endpoint_to_retrieve_all_dietician() {
		response = request.log().all().request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("Admin send GET http request with endpoint to retrieve all dietician as Admin"
				+ response.getStatusLine());
	}

	@Then("Admin recieves {int} ok with response body to retrieve all dietician")
	public void admin_recieves_ok_with_response_body_to_retrieve_all_dietician(Integer statusCode) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType());
		LoggerLoad.info("Expected Status Code " + statusCode + " Actual Status code " + response.getStatusCode());
	}

	@Given("Admin create PUT request to retrieve all dietician")
	public void admin_create_put_request_to_retrieve_all_dietician() {
		commonRequest("Check admin able to retrieve all dietician with invalid method");
	}

	@When("Admin send PUT http request with endpoint to retrieve all dietician")
	public void admin_send_put_http_request_with_endpoint_to_retrieve_all_dietician() {
		response = request.log().all().request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("Admin send PUT http request with endpoint to retrieve all dietician as Admin"
				+ response.getStatusLine());
	}

	@Then("Admin recieves {int} method not allowedto retrieve all dietician")
	public void admin_recieves_method_not_allowedto_retrieve_all_dietician(Integer statusCode) {

		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType());
		LoggerLoad.info("Expected Status Code " + statusCode + " Actual Status code " + response.getStatusCode());
	}

	@Given("Admin create GET request to retrieve all dietician with invalid endpoint")
	public void admin_create_get_request_to_retrieve_all_dietician_with_invalid_endpoint() {
		commonRequest("Check admin able to retrieve all dietician with invalid endpoint");
	}

	@When("Admin send GET http request with invalid endpoint to retrieve all dietician")
	public void admin_send_get_http_request_with_invalid_endpoint_to_retrieve_all_dietician() {
		response = request.log().all().request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("Admin send GET http request with invalid endpoint to retrieve all dietician as Admin"
				+ response.getStatusLine());
	}

	@Then("Admin recieves {int} not found to retrieve all dietician")
	public void admin_recieves_not_found_to_retrieve_all_dietician(Integer statusCode) {

		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType());
		LoggerLoad.info("Expected Status Code " + statusCode + " Actual Status code " + response.getStatusCode());
	}

}
