package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import com.CalorieHackers_POJO.TestDataPOJO;
import com.CalorieHackers_Utilities.ConfigReader;
import com.CalorieHackers_Utilities.JsonDataReader;
import com.CalorieHackers_Utilities.LoggerLoad;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class userLogin_POST_SD {

	RequestSpecification request;
	Response response;
	private TestDataPOJO currentTestData;
	private static final String jsondatapath = "src/test/resources/TestData/TestData.json";

	private void commonRequest(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info(scenarioName);
		request = given().baseUri(ConfigReader.getKeyValues("BASE_URL"));

	}

	@Given("User creates Post request with invalid email")
	public void user_creates_post_request_with_invalid_email() {

		// String scenarioName = "Check user able to login as admin with invalid email";
		commonRequest("Check user able to login as admin with invalid email");
		// LoggerReader.info("Check user able to login as admin with invalid email");

	}

	@When("User send POST HTTP request with endpoint")
	public void user_send_post_http_request_with_endpoint() {

		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("userLoginEmail", currentTestData.getUserLoginEmail());
		requestBody.put("password", currentTestData.getPassword());

		request = request.contentType(ContentType.JSON).body(currentTestData);
		response = request.request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("User send POST HTTP request with endpoint" + response.getStatusLine());

	}

	@Then("User receives {int} unauthorized")
	public void user_receives_unauthorized(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType());
		LoggerLoad.info("User receives 401 unauthorized" + response.getStatusLine());
	}

	@Given("User creates Post request with invalid email format")
	public void user_creates_post_request_with_invalid_email_format() {

		commonRequest("Check user able to login as admin with invalid email format");
	}

	@Given("User creates Post request with invalid Password")
	public void user_creates_post_request_with_invalid_password() {

		commonRequest("Check user able to login as admin with invalid Password");
	}

	@Given("User creates Post request with empty credentials")
	public void user_creates_post_request_with_empty_credentials() {
		commonRequest("Check user able to login as admin with empty credentials");

	}

	@Given("User creates Post request with request body as admin")
	public void user_creates_post_request_with_request_body_as_admin() {
		commonRequest("Check user able to login as admin with valid data");
	}

	@When("User send POST HTTP request with endpoint as admin")
	public void user_send_post_http_request_with_endpoint_as_admin() {

		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("userLoginEmail", currentTestData.getUserLoginEmail());
		requestBody.put("password", currentTestData.getPassword());
		request = request.contentType(ContentType.JSON).body(currentTestData);
		response = request.request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("User send POST HTTP request with endpoint as Admin" + response.getStatusLine());
	}

	@Then("User receives {int} created with response body as admin")
	public void user_receives_created_with_response_body_as_admin(Integer int1) {

		String responseBody = response.getBody().asPrettyString();

		response.then().assertThat().log().all().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType())
				.body("loginUserEmail", equalTo(currentTestData.getUserLoginEmail()))
				.body("roles",hasItem("ROLE_ADMIN"));
		LoggerLoad.info("Response body:" + responseBody);

		JsonPath js = response.jsonPath();
		String adminToken = js.getString("token");
		LoggerLoad.info("Admin Token:" + adminToken);

	}

}
