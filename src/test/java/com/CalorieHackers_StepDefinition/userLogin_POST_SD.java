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
	public static String adminToken;
	public static String dieticianToken;
	public static String patientToken;
	String patientEmail = CreatePatient_Post.patientEmail;
	String dieticianLoginPassword = createDietician_SD.dieticianLoginPassword;
	String dieticianEmail = createDietician_SD.dieticianEmail;
	private TestDataPOJO currentTestData;
	private static final String jsondatapath = "src/test/resources/TestData/TestData.json";

	private void commonRequest(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info(scenarioName);
		request = given().baseUri(ConfigReader.getKeyValues("BASE_URL"));

	}

	@Given("User creates Post request with invalid email")
	public void user_creates_post_request_with_invalid_email() {

		commonRequest("Check user able to login as admin with invalid email");

	}

	@When("User send POST HTTP request with endpoint")
	public void user_send_post_http_request_with_endpoint() {

		if ("Check user able to login as admin with valid credential and invalid content type"
				.equalsIgnoreCase(currentTestData.getScenarioName())) {
			request = request.header("Content-Type", "text/plain");// invalid content type
		} else {
			request = request.contentType(ContentType.JSON).body(currentTestData);
		}
		response = request.request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("User send POST HTTP request with endpoint" + response.getStatusLine());

	}

	@Then("User receives {int} unauthorized")
	public void user_receives_unauthorized(Integer statusCode) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType());
		LoggerLoad.info("Expected Status Code " + statusCode + " Actual Status code " + response.getStatusCode());

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

		request = request.contentType(ContentType.JSON).body(currentTestData);
		response = request.request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("User send POST HTTP request with endpoint as Admin" + response.getStatusLine());

	}

	@Then("User receives {int} created with response body as admin")
	public void user_receives_created_with_response_body_as_admin(Integer statusCode) {

		String responseBody = response.getBody().asPrettyString();

		response.then().assertThat().log().all().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType())
				.body("loginUserEmail", equalTo(currentTestData.getUserLoginEmail()))
				.body("roles", hasItem("ROLE_ADMIN"));
		LoggerLoad.info("Response body:" + responseBody);

		JsonPath js = response.jsonPath();
		adminToken = js.getString("token");
		currentTestData.setAdminToken(adminToken);

	}

	@Given("User creates GET request with request body.Request body : Userlogin and password")
	public void user_creates_get_request_with_request_body_request_body_userlogin_and_password() {

		commonRequest("Check user able to login as admin with valid credential and invalid method");
	}

	@When("User send GET HTTP request with endpoint")
	public void user_send_get_http_request_with_endpoint() {

		request = request.contentType(ContentType.JSON).body(currentTestData);
		response = request.request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("User send GET HTTP request with endpoint" + response.getStatusLine());
	}

	@Then("User receives {int} method not allowed")
	public void user_receives_method_not_allowed(Integer statusCode) {

		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType());
		LoggerLoad.info("Expected Status Code " + statusCode + " Actual Status code " + response.getStatusCode());

	}

	@Given("User creates Post request with request body.Request body : Userlogin and password")
	public void user_creates_post_request_with_request_body_request_body_userlogin_and_password() {

		commonRequest("Check user able to login as admin with valid credential and invalid endpoint");
	}

	@When("User send POST HTTP request with invalid endpoint")
	public void user_send_post_http_request_with_invalid_endpoint() {

		request = request.contentType(ContentType.JSON).body(currentTestData);
		response = request.request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("User send POST HTTP request with endpoint" + response.getStatusLine());
	}

	@Given("User creates Post request with request body and invalid content type.Request body : Userlogin and password")
	public void user_creates_post_request_with_request_body_and_invalid_content_type_request_body_userlogin_and_password() {

		commonRequest("Check user able to login as admin with valid credential and invalid content type");
	}

	@Then("User receives {int} unsupported media type")
	public void user_receives_unsupported_media_type(Integer statusCode) {

		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType());
		LoggerLoad.info("Expected Status Code " + statusCode + " Actual Status code " + response.getStatusCode());
	}

	@Given("User creates Post request with invalid email as dietician")
	public void user_creates_post_request_with_invalid_email_as_dietician() {

		commonRequest("Check user able to login as dietician with invalid email");

	}

	@Given("User creates Post request with invalid email format as dietician")
	public void user_creates_post_request_with_invalid_email_format_as_dietician() {

		commonRequest("Check user able to login as dietician with invalid email format");
	}

	@Given("User creates Post request with invalid Password as dietician")
	public void user_creates_post_request_with_invalid_password_as_dietician() {

		commonRequest("Check user able to login as dietician with invalid Password");
	}

	@Given("User creates Post request with invalid email as patient")
	public void user_creates_post_request_with_invalid_email_as_patient() {

		commonRequest("Check user able to login as patient with invalid Password");
	}

	@Given("User creates Post request with invalid email format as patient")
	public void user_creates_post_request_with_invalid_email_format_as_patient() {

		commonRequest("Check user able to login as patient with invalid Password");
	}

	@Given("User creates Post request with invalid Password as patient")
	public void user_creates_post_request_with_invalid_password_as_patient() {

		commonRequest("Check user able to login as patient with invalid Password");
	}

	@Given("User creates Post request with request body as dietician")
	public void user_creates_post_request_with_request_body_as_dietician() {
		commonRequest("Check user able to login as dietician with valid credential");
	}

	@When("User send POST HTTP request with endpoint as dietician")
	public void user_send_post_http_request_with_endpoint_as_dietician() {

		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("userLoginEmail", dieticianEmail);
		requestBody.put("password", dieticianLoginPassword);
		System.out.println("request body for Dietician" + requestBody);
		request = request.contentType(ContentType.JSON).body(requestBody);
		response = request.request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("User send POST HTTP request with endpoint as Dietician" + response.getStatusLine());
	}

	@Then("User receives {int} created with response body as dietician")
	public void user_receives_created_with_response_body_as_dietician(Integer statusCode) {

		String responseBody = response.getBody().asPrettyString();

		response.then().assertThat().log().all().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType())
				// .body("loginUserEmail", equalTo(dieticianEmail))
				.body("roles", hasItem("ROLE_DIETICIAN"));
		LoggerLoad.info("Response body:" + responseBody);

		JsonPath js = response.jsonPath();
		dieticianToken = js.getString("token");
		currentTestData.setDieticianToken(dieticianToken);
		LoggerLoad.info("Dietician Token:" + dieticianToken);
	}

	@Given("User creates Post request with request body as patient")
	public void user_creates_post_request_with_request_body_as_patient() {

		commonRequest("Check user able to login as patient with valid credential");
	}

	@When("User send POST HTTP request with endpoint as patient")
	public void user_send_post_http_request_with_endpoint_as_patient() {

		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("userLoginEmail", patientEmail);
		requestBody.put("password", currentTestData.getPassword());
		LoggerLoad.info("request body for Patient" + requestBody);
		request = request.contentType(ContentType.JSON).body(requestBody);
		response = request.request(currentTestData.getMethod(), currentTestData.getEndpoint());
		LoggerLoad.info("User send POST HTTP request with endpoint as Patient" + response.getStatusLine());

	}

	@Then("User receives {int} created with response body as patient")
	public void user_receives_created_with_response_body_as_patient(Integer statusCode) {

		String responseBody = response.getBody().asPrettyString();

		response.then().assertThat().log().all().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine())
				.contentType(currentTestData.getExpectedContentType()).body("roles", hasItem("ROLE_PATIENT"));
		LoggerLoad.info("Response body:" + responseBody);

		JsonPath js = response.jsonPath();
		patientToken = js.getString("token");
		currentTestData.setPatientToken(patientToken);
		LoggerLoad.info("Patient Token:" + patientToken);
	}

}
