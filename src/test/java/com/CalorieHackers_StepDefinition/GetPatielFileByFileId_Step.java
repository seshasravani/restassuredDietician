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
	String adminToken = userLogin_POST_SD.adminToken;
	String dieticianToken = userLogin_POST_SD.dieticianToken;
	String patientToken = userLogin_POST_SD.patientToken;
	String fileId = AddNewReportsPatient_Step.patientFileId;

	@Given("Dietician with no auth create GET patient file request to {string}")
	public void dietician_with_no_auth_create_get_patient_file_request_to(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician with no auth " + scenarioName);
		request = given();
	}

	@When("Dietician send GET http request to retrieve patients by file id with endpoint")
	public void dietician_send_get_http_request_to_retrieve_patients_by_file_id_with_endpoint() {
		response = request.when().get(currentTestData.getEndpoint() + fileId);
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
		LoggerLoad.info("Admin create GET patient file request to " + scenarioName);
		request = given().header("Authorization", "Bearer " + adminToken);
	}

	@When("Admin send GET http request to retrieve patients by file id with endpoint")
	public void admin_send_get_http_request_to_retrieve_patients_by_file_id_with_endpoint() {
		response = request.when().get(currentTestData.getEndpoint() + fileId);
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
		LoggerLoad.info("Patient create GET patient file request to " + scenarioName);
		LoggerLoad.info("Patient token " + patientToken);
		request = given().header("Authorization", "Bearer " + patientToken);
	}

	@When("Patient send GET http request to retrieve patients by file id with endpoint")
	public void patient_send_get_http_request_to_retrieve_patients_by_file_id_with_endpoint() {
		LoggerLoad.info("Patient fileid " + fileId);
		response = request.when().get(currentTestData.getEndpoint() + fileId);
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
		LoggerLoad.info("Dietician create GET patient file request to " + scenarioName);
		LoggerLoad.info("Dietician token " + dieticianToken);
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
		LoggerLoad.info("Dietician create POST patient file request to " + scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken);
	}

	@When("Dietician send POST http request to retrieve patients by file id with endpoint")
	public void dietician_send_post_http_request_to_retrieve_patients_by_file_id_with_endpoint() {
		response = request.when().post(currentTestData.getEndpoint() + fileId);
	}

	@When("Dietician send GET http request to retrieve patients by invalid file id with endpoint")
	public void dietician_send_get_http_request_to_retrieve_patients_by_invalid_file_id_with_endpoint() {
		response = request.when().get(currentTestData.getEndpoint() + currentTestData.getInvaidfileid());
	}

	@When("Dietician send GET http request to retrieve patients by file id with invalid endpoint")
	public void dietician_send_get_http_request_to_retrieve_patients_by_file_id_with_invalid_endpoint() {
		response = request.when().get(currentTestData.getEndpoint() + fileId);
	}

}
