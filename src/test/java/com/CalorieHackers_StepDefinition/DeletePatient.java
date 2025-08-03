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
	private static final String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNDAxQGdtYWlsLmNvbSIsImlhdCI6MTc1NDA3NzUwMCwiZXhwIjoxNzU0MTA2MzAwfQ.hNw3C6lfkRBtFpKhNvZSUl_DYQliR2bFXdFMJTKLOxWu443ouju1LhhfzbjJrDlweAqYnnA2HsRdVuajtmrfbw";
	private static final String dieticianToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDYWxvcmllRGlldGljaWFuMDFAZ21haWwuY29tIiwiaWF0IjoxNzU0MDcyNjU3LCJleHAiOjE3NTQxMDE0NTd9.mlBUoIGUWoxkrWV1nS4xlzCo8OccC7XMksf2CeaUCMeOvHXibYWMjQfykvdkWSFQNsZm-PnZQNXL15Kx4VfWSA";
	private static final String patientToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtZWxpc3NhNEBnbWFpbC5jb20iLCJpYXQiOjE3NTQwNzI5MjUsImV4cCI6MTc1NDEwMTcyNX0.eMe8rcik3mwkv3G0hNX_BTeK9kwfNzqR9T-8TO7N_U1ixE1wLwUMirAx6VfJscYWj8HaGhEgFVRjKO94CBmO2Q";
	private static final int patientID=73;
	
	
	@Given("Dietician with no auth create DELETE patient request to {string}")
	public void dietician_with_no_auth_create_delete_patient_request_to(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician with no auth create DELETE patient request to " +scenarioName);
		request = given();	    
	}

	@When("Dietician send DELETE http request to delete patient by ID with endpoint")
	public void dietician_send_delete_http_request_to_delete_patient_by_id_with_endpoint() {
		response = request.when().delete(currentTestData.getEndpoint()+patientID);	    
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
		LoggerLoad.info("Admin create valid " +scenarioName);
		request = given().header("Authorization", "Bearer " + adminToken);	    
	}

	@When("Admin send GET http request to delete patient by ID with endpoint")
	public void admin_send_get_http_request_to_delete_patient_by_id_with_endpoint() {
		response = request.when().delete(currentTestData.getEndpoint()+patientID);		    
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
		LoggerLoad.info("Patient create valid " +scenarioName);
		request = given().header("Authorization", "Bearer " + patientToken);	    
	}

	@When("Patient send GET http request to delete patient by ID with endpoint")
	public void patient_send_get_http_request_to_delete_patient_by_id_with_endpoint() {
		response = request.when().delete(currentTestData.getEndpoint()+patientID);	    
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
		LoggerLoad.info("Dietician create valid " +scenarioName);
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
		LoggerLoad.info("Dietician create valid " +scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken);	    
	}

	@When("Dietician send POST http request to delete patient by ID with endpoint")
	public void dietician_send_post_http_request_to_delete_patient_by_id_with_endpoint() {
		response = request.when().post(currentTestData.getEndpoint()+patientID);	 	    
	}
	
	@When("Dietician send DELETE http request to delete patient by invalid ID with endpoint")
	public void dietician_send_delete_http_request_to_delete_patient_by_invalid_id_with_endpoint() {
		response = request.when().delete(currentTestData.getEndpoint()+currentTestData.getInvaidpatientid());	    
	}

	@When("Dietician send DELETE http request to delete patient by ID with invalid endpoint")
	public void dietician_send_delete_http_request_to_delete_patient_by_id_with_invalid_endpoint() {
		response = request.when().delete(currentTestData.getEndpoint()+patientID);	    
	}

}
