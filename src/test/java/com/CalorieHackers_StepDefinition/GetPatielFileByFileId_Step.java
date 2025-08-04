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

public class GetPatielFileByFileId_Step {
	
	RequestSpecification request;
	Response response;
	private TestDataPOJO currentTestData;
	private static final String jsondatapath = ConfigReader.getKeyValues("JSON_PATH");
	private static final String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNDAxQGdtYWlsLmNvbSIsImlhdCI6MTc1NDA3NzUwMCwiZXhwIjoxNzU0MTA2MzAwfQ.hNw3C6lfkRBtFpKhNvZSUl_DYQliR2bFXdFMJTKLOxWu443ouju1LhhfzbjJrDlweAqYnnA2HsRdVuajtmrfbw";
	private static final String dieticianToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDYWxvcmllRGlldGljaWFuMDFAZ21haWwuY29tIiwiaWF0IjoxNzU0Mjc4OTU1LCJleHAiOjE3NTQzMDc3NTV9.NBaPU9jQclMTzM96nzNU04Rw-24tqO_MM3COJT00q14jmA1jsV47Zn5ElwcyQFclGC1gbr6pg1JSkpFk_27nNA";
	private static final String patientToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtZWxpc3NhNEBnbWFpbC5jb20iLCJpYXQiOjE3NTQyODM1OTAsImV4cCI6MTc1NDMxMjM5MH0.jTb7ITRoB2gCJGcqw-2xp2vD2y40i_IlTkjgYj90CP1yXTMw9uScJZwEcUbp35jRZK_txZVPFjrdNECVrSLS_g";
	private static final String fileId="688d0926302e7e2cd4b0cb47";

	@Given("Dietician with no auth create GET patient file request to {string}")
	public void dietician_with_no_auth_create_get_patient_file_request_to(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician with no auth " +scenarioName);
		request = given();
	}

	@When("Dietician send GET http request to retrieve patients by file id with endpoint")
	public void dietician_send_get_http_request_to_retrieve_patients_by_file_id_with_endpoint() {
		response = request.when().get(currentTestData.getEndpoint()+fileId);
	}

	@Then("Dietician recieves {string} to retrieve patients by file id")
	public void dietician_recieves_to_retrieve_patients_by_file_id(String string) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Dietician receives error " + response.asPrettyString());
	}

	@Given("Admin create GET patient file request to {string}")
	public void admin_create_get_patient_file_request_to(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Admin create GET patient file request to " +scenarioName);
		request = given().header("Authorization", "Bearer " + adminToken);
	}

	@When("Admin send GET http request to retrieve patients by file id with endpoint")
	public void admin_send_get_http_request_to_retrieve_patients_by_file_id_with_endpoint() {
		response = request.when().get(currentTestData.getEndpoint()+fileId);
	}

	@Then("Admin recieves {string} to retrieve patients by file id")
	public void admin_recieves_to_retrieve_patients_by_file_id(String string) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Admin receives forbidden " + response.asPrettyString());
	}

	@Given("Patient create GET patient file request to {string}")
	public void patient_create_get_patient_file_request_to(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Patient create GET patient file request to " +scenarioName);
		request = given().header("Authorization", "Bearer " + patientToken);
	}

	@When("Patient send GET http request to retrieve patients by file id with endpoint")
	public void patient_send_get_http_request_to_retrieve_patients_by_file_id_with_endpoint() {
		response = request.when().get(currentTestData.getEndpoint()+fileId);
	}

	@Then("Patient recieves {string} with details of the patient id by file id")
	public void patient_recieves_with_details_of_the_patient_id_by_file_id(String string) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine())
		.contentType(currentTestData.getExpectedContentType());
		LoggerLoad.info("Patients receives details " + response.getStatusLine());
	}

	@Given("Dietician create GET patient file request to {string}")
	public void dietician_create_get_patient_file_request_to(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician create GET patient file request to " +scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken);
	}

	@Then("Dietician recieves {string} with details of the patient id by file id")
	public void dietician_recieves_with_details_of_the_patient_id_by_file_id(String string) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine())
		.contentType(currentTestData.getExpectedContentType());
		LoggerLoad.info("Dietician receives details " + response.getStatusLine());
	}

	@Given("Dietician create POST patient file request to {string}")
	public void dietician_create_post_patient_file_request_to(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician create POST patient file request to " +scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken);
	}

	@When("Dietician send POST http request to retrieve patients by file id with endpoint")
	public void dietician_send_post_http_request_to_retrieve_patients_by_file_id_with_endpoint() {
		response = request.when().post(currentTestData.getEndpoint()+fileId);
	}

	@When("Dietician send GET http request to retrieve patients by invalid file id with endpoint")
	public void dietician_send_get_http_request_to_retrieve_patients_by_invalid_file_id_with_endpoint() {
		response = request.when().get(currentTestData.getEndpoint()+currentTestData.getInvaidfileid());
	}

	@When("Dietician send GET http request to retrieve patients by file id with invalid endpoint")
	public void dietician_send_get_http_request_to_retrieve_patients_by_file_id_with_invalid_endpoint() {
		response = request.when().get(currentTestData.getEndpoint()+fileId);
	}

}
