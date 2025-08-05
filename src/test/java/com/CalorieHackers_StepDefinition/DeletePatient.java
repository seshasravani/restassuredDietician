package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;

import com.CalorieHackers_POJO.TestDataPOJO;
import com.CalorieHackers_Utilities.ConfigReader;
import com.CalorieHackers_Utilities.JsonDataReader;
import com.CalorieHackers_Utilities.LoggerLoad;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeletePatient {

	RequestSpecification request;
	Response response;
	private TestDataPOJO currentTestData;
	private static final String jsondatapath = ConfigReader.getKeyValues("JSON_PATH");
	String adminToken = userLogin_POST_SD.adminToken;
	String dieticianToken = userLogin_POST_SD.dieticianToken;

	String patientToken = userLogin_POST_SD.patientToken;
	int patientID = CreatePatient_Post.patientId;

	@Given("Dietician with no auth create DELETE patient request to {string}")
	public void dietician_with_no_auth_create_delete_patient_request_to(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician with no auth create DELETE patient request to " + scenarioName);
		request = given();
	}

	@When("Dietician send DELETE http request to delete patient by ID with endpoint")
	public void dietician_send_delete_http_request_to_delete_patient_by_id_with_endpoint() {
		response = request.when().delete(currentTestData.getEndpoint() + patientID);
	}

	@Then("Dietician recieves {string} to delete patient by ID")
	public void dietician_recieves_to_delete_patient_by_id(String string) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Dietician receives error " + response.asPrettyString());
	}

	@Given("Admin create DELETE patient request to {string}")
	public void admin_create_delete_patient_request_to(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Admin create valid " + scenarioName);
		request = given().header("Authorization", "Bearer " + adminToken);
	}

	@When("Admin send GET http request to delete patient by ID with endpoint")
	public void admin_send_get_http_request_to_delete_patient_by_id_with_endpoint() {
		response = request.when().delete(currentTestData.getEndpoint() + patientID);
	}

	@Then("Admin recieves {string} to delete patient by ID")
	public void admin_recieves_to_delete_patient_by_id(String string) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Admin receives forbidden " + response.asPrettyString());
	}

	@Given("Patient create DELETE patient request to {string}")
	public void patient_create_delete_patient_request_to(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Patient create valid " + scenarioName);
		request = given().header("Authorization", "Bearer " + patientToken);
	}

	@When("Patient send GET http request to delete patient by ID with endpoint")
	public void patient_send_get_http_request_to_delete_patient_by_id_with_endpoint() {
		response = request.when().delete(currentTestData.getEndpoint() + patientID);
	}

	@Then("Patient recieves {string} to delete patient by ID")
	public void patient_recieves_to_delete_patient_by_id(String string) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Patients receives forbidden " + response.asPrettyString());
	}

	@Given("Dietician create DELETE patient request to {string}")
	public void dietician_create_delete_patient_request_to(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician create valid " + scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken);
	}

	@Then("Dietician recieves 200 ok to delete patient by ID")
	public void dietician_recieves_ok_to_delete_patient_by_id() {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
				.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Dietician receives success message " + response.asPrettyString());
	}

	@Given("Dietician create POST patient request to {string}")
	public void dietician_create_post_patient_request_to(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician create valid " + scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken);
	}

	@When("Dietician send POST http request to delete patient by ID with endpoint")
	public void dietician_send_post_http_request_to_delete_patient_by_id_with_endpoint() {
		response = request.when().post(currentTestData.getEndpoint() + patientID);
	}

	@When("Dietician send DELETE http request to delete patient by invalid ID with endpoint")
	public void dietician_send_delete_http_request_to_delete_patient_by_invalid_id_with_endpoint() {
		response = request.when().delete(currentTestData.getEndpoint() + currentTestData.getInvaidpatientid());
	}

	@When("Dietician send DELETE http request to delete patient by ID with invalid endpoint")
	public void dietician_send_delete_http_request_to_delete_patient_by_id_with_invalid_endpoint() {
		response = request.when().delete(currentTestData.getEndpoint() + patientID);
	}

}
