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
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetPatientMorbidity_Step {
	
	RequestSpecification request;
	Response response;
	private TestDataPOJO currentTestData;
	private static final String jsondatapath = ConfigReader.getKeyValues("JSON_PATH");
	private static final String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNDAxQGdtYWlsLmNvbSIsImlhdCI6MTc1NDA3NzUwMCwiZXhwIjoxNzU0MTA2MzAwfQ.hNw3C6lfkRBtFpKhNvZSUl_DYQliR2bFXdFMJTKLOxWu443ouju1LhhfzbjJrDlweAqYnnA2HsRdVuajtmrfbw";
	private static final String dieticianToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDYWxvcmllRGlldGljaWFuMDFAZ21haWwuY29tIiwiaWF0IjoxNzU0Mjc4OTU1LCJleHAiOjE3NTQzMDc3NTV9.NBaPU9jQclMTzM96nzNU04Rw-24tqO_MM3COJT00q14jmA1jsV47Zn5ElwcyQFclGC1gbr6pg1JSkpFk_27nNA";
	private static final String patientToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtZWxpc3NhNEBnbWFpbC5jb20iLCJpYXQiOjE3NTQyODM1OTAsImV4cCI6MTc1NDMxMjM5MH0.jTb7ITRoB2gCJGcqw-2xp2vD2y40i_IlTkjgYj90CP1yXTMw9uScJZwEcUbp35jRZK_txZVPFjrdNECVrSLS_g";
	private static final int patientID=56;
	
	@Given("Dietician with {string} request to retrieve patients morbidity details")
	public void dietician_with_request_to_retrieve_patients_morbidity_details(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician with " +scenarioName);
		request = given();
	}

	@When("Dietician send GET http request to retrieve patients morbidity details with endpoint")
	public void dietician_send_get_http_request_to_retrieve_patients_morbidity_details_with_endpoint() {
		response = request.when().get(currentTestData.getEndpoint()+patientID);
	}

	@Then("Dietician recieves {int} unauthorized to retrieve patients morbidity details")
	public void dietician_recieves_unauthorized_to_retrieve_patients_morbidity_details(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Dietician receives unauthorized " + response.asPrettyString());
	}

	@Given("Admin create {string} to retrieve patients morbidity details")
	public void admin_create_to_retrieve_patients_morbidity_details(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Admin create valid " +scenarioName);
		request = given().header("Authorization", "Bearer " + adminToken);
	}

	@When("Admin send GET http request to retrieve patients morbidity details with endpoint")
	public void admin_send_get_http_request_to_retrieve_patients_morbidity_details_with_endpoint() {
		response = request.when().get(currentTestData.getEndpoint()+patientID);
	}

	@Then("Admin recieves {int} Forbidden to retrieve patients morbidity details")
	public void admin_recieves_forbidden_to_retrieve_patients_morbidity_details(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Admin receives forbidden " + response.asPrettyString());	   
	}

	@Given("Patient create valid {string} morbidity details")
	public void patient_create_valid_morbidity_details(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Patient create valid " +scenarioName);
		request = given().header("Authorization", "Bearer " + patientToken);
	}

	@When("Patient send GET http request to retrieve patients morbidity details with endpoint")
	public void patient_send_get_http_request_to_retrieve_patients_morbidity_details_with_endpoint() {
		response = request.when().get(currentTestData.getEndpoint()+patientID);
	}

	@Then("Patient recieves {int} ok with details of the patient id")
	public void patient_recieves_ok_with_details_of_the_patient_id(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine())
		.contentType(currentTestData.getExpectedContentType())
		.assertThat().body(matchesJsonSchemaInClasspath("schemas/GetPatientMorbiditySchema.json"));
		LoggerLoad.info("Patients receives morbidity details " + response.asPrettyString());
	}

	@Given("Dietician create valid {string} morbidity details")
	public void dietician_create_valid_morbidity_details(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician create valid " +scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken);
	}

	@Then("Dietician recieves {int} ok with details of the patient id")
	public void dietician_recieves_ok_with_details_of_the_patient_id(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine())
		.contentType(currentTestData.getExpectedContentType())
		.assertThat().body(matchesJsonSchemaInClasspath("TestData/GetPatientMorbiditySchema.json"));;
		LoggerLoad.info("Dietician receives morbidity details " + response.asPrettyString());
	}

	@Given("Dietician create {string} to retrieve patients morbidity details")
	public void dietician_create_to_retrieve_patients_morbidity_details(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician create " +scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken);
	}

	@When("Dietician send POST http request to retrieve patients morbidity details with endpoint")
	public void dietician_send_post_http_request_to_retrieve_patients_morbidity_details_with_endpoint() {
		response = request.when().post(currentTestData.getEndpoint()+patientID);
	}

	@Then("Dietician recieves {int} method not allowed to retrieve patients morbidity details")
	public void dietician_recieves_method_not_allowed_to_retrieve_patients_morbidity_details(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Dietician receives method not allowed " + response.asPrettyString());	   
	}

	@Given("Dietician create GET morbidity request by {string} details")
	public void dietician_create_get_morbidity_request_by_details(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician create GET morbidity request by " +scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken);
	}
	
	@When("Dietician send GET http request to retrieve invalid patients morbidity details with endpoint")
	public void dietician_send_get_http_request_to_retrieve_invalid_patients_morbidity_details_with_endpoint() {
		response = request.when().get(currentTestData.getEndpoint()+currentTestData.getInvaidpatientid());
	}

	@Then("Dietician recieves {int} not found to retrieve patients morbidity details")
	public void dietician_recieves_not_found_to_retrieve_patients_morbidity_details(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Patients receives 404 not found " + response.asPrettyString());
	}

	@Given("Dietician create invalid {string} to retrieve patients morbidity details")
	public void dietician_create_invalid_to_retrieve_patients_morbidity_details(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician create invalid " +scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken);
	}

	@When("Dietician send  GET http request to retrieve patients morbidity details with invalid endpoint")
	public void dietician_send_get_http_request_to_retrieve_patients_morbidity_details_with_invalid_endpoint() {
		response = request.when().get(currentTestData.getEndpoint()+patientID);
	}
	
}
