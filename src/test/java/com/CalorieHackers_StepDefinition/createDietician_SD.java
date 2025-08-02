package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import com.CalorieHackers_POJO.TestDataPOJO;
import com.CalorieHackers_Utilities.ConfigReader;
import com.CalorieHackers_Utilities.JsonDataReader;
import com.CalorieHackers_Utilities.LoggerLoad;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class createDietician_SD {

	RequestSpecification request;
	Response response;
	private TestDataPOJO currentTestData;
	String adminToken=userLogin_POST_SD.adminToken;
	// private static final String jsondatapath =
	// ConfigReader.getKeyValues("dataFilePath");
	private static final String jsondatapath = "src/test/resources/TestData/TestData.json";

	private void commonRequest(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info(scenarioName);
		LoggerLoad.info(adminToken);
		request = given().baseUri(ConfigReader.getKeyValues("BASE_URL")).header("Authorization",
				"Bearer " + adminToken);

	}

	@Given("Admin creates POST request with valid data. \\( Mandatory and additional details)")
	public void admin_creates_post_request_with_valid_data_mandatory_and_additional_details() {
		commonRequest("Check admin able to create dietician with valid data and token");
	}

	@When("Admin send POST http request with endpoint")
	public void admin_send_post_http_request_with_endpoint() {
		request = request.contentType(ContentType.JSON).body(currentTestData);
		response = request.request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("User send POST HTTP request with endpoint as Admin" + response.getStatusLine());
	}

	@Then("Admin recieves {int} created and with response body. \\(Auto created dietician ID and login password)")
	public void admin_recieves_created_and_with_response_body_auto_created_dietician_id_and_login_password(
			Integer statusCode) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType());
		LoggerLoad.info("Expected Status Code " + statusCode + " Actual Status code " + response.getStatusCode());
	}

}
