package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;

import java.io.File;

import com.CalorieHackers_POJO.TestDataPOJO;
import com.CalorieHackers_Utilities.ConfigReader;
import com.CalorieHackers_Utilities.JsonDataReader;
import com.CalorieHackers_Utilities.LoggerLoad;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.listener.ResponseValidationFailureListener;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.config.FailureConfig;

public class AddNewReportsPatient_Step {

	RequestSpecification request;
	Response response;
	private TestDataPOJO currentTestData;
	private static final String jsondatapath = ConfigReader.getKeyValues("JSON_PATH");
	String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNDAxQGdtYWlsLmNvbSIsImlhdCI6MTc1NDAyMTQ2MCwiZXhwIjoxNzU0MDUwMjYwfQ.r6P0fHg5ZwXq1DN1eH2XFkWJxsETL1CWFChHMXJ_EI-jeFhVVu3dcfenfzYHa6MwDxtyIPa_qfng43XHHTI4eg";
	String dieticianToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDYWxvcmllRGlldGljaWFuMDFAZ21haWwuY29tIiwiaWF0IjoxNzU0MDM0NjM0LCJleHAiOjE3NTQwNjM0MzR9.eNOizoYUtoCcMKZ3ZSaSPcIxxOMG5sKJtAuJy7epNk1b2x5YfbHv6t4_LM1fJ8IEN81yWDN6t5Um9ZxSc3gc7A";
	String patientToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDYWxvcmllRGlldGljaWFuMDFAZ21haWwuY29tIiwiaWF0IjoxNzUzOTg5ODMwLCJleHAiOjE3NTQwMTg2MzB9.MlEJEFnyIGaymOTZds4tCrMxYWd1PX8HPME6LbPXy-Gi8la08LQzSkuRr_A5FIBjH7oczKHNoMpv82mSsyi1Dg";
	int patientID=56;

	@Given("Dietician with no auth creates PUT request by {string}")
	public void dietician_with_no_auth_creates_put_request_by(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician with no auth " +scenarioName);
		request = given()
				.contentType(ContentType.MULTIPART)
				.multiPart("file", new File(currentTestData.getReportFilePath()) ,"application/pdf");
	}

	@When("User send PUT http request to add new report")
	public void User_send_put_http_request_to_add_new_report() {
		response = request.when().put(currentTestData.getEndpoint()+patientID);
	}

	@Then("Dietician recieves {int} unauthorized")
	public void dietician_recieves_unauthorized(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine())
		.contentType(currentTestData.getExpectedContentType());
		LoggerLoad.info("Dietician with no auth receives 401 unauthorized when adding report to existing patient " + response.asPrettyString());
	}

	@Given("Admin creates PUT request to add new report by {string}")
	public void admin_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_valid_patient_id(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Admin " +scenarioName);
		request = given().header("Authorization", "Bearer " + adminToken)
				.contentType(ContentType.MULTIPART)
				.multiPart("file", new File(currentTestData.getReportFilePath()) ,"application/pdf");
	}

//	@When("Admin send PUT http request to add new report")
//	public void admin_send_put_http_request_to_add_new_report() {
//		response = request.when().put(currentTestData.getEndpoint()+patientID);
//	}

	@Then("Admin recieves {int} forbidden")
	public void admin_recieves_forbidden(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Admin receives 403 forbidden when adding report to existing patient " + response.asPrettyString());

	}

	@Given("Patient creates PUT request to add new report by {string}")
	public void patient_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_valid_patient_id(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Patient " +scenarioName);
		request = given().header("Authorization", "Bearer " + adminToken)
				.contentType(ContentType.MULTIPART)
				.multiPart("file", new File(currentTestData.getReportFilePath()) ,"application/pdf");
	}

//	@When("Patient send PUT http request to add new report")
//	public void patient_send_put_http_request_to_add_new_report() {
//		response = request.when().put(currentTestData.getEndpoint()+patientID);
//	}

	@Then("Patient recieves {int} forbidden")
	public void patient_recieves_forbidden(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Patient receives 403 forbidden when adding report to existing patient " + response.asPrettyString());
	}

	@Given("Dietician creates PUT request to add new report by entering {string}")
	public void dietician_creates_put_request_by_entering_valid_data_mandatory_and_additional_details_into_the_form_data_key_and_value_fields_and_valid_patient_id(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician add new report by entering " +scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken)
//				.contentType(ContentType.MULTIPART)
				.queryParam("patientInfo", currentTestData.getPatientinfo())
				.queryParam("vitals", currentTestData.getVitals())
				.multiPart("file", new File(currentTestData.getReportFilePath()) ,"application/pdf");
	}

	@Then("Dietician recieves {int} ok and with updated response body")
	public void dietician_recieves_ok_and_with_updated_response_body(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Dietician able to add patient report "+ response.asPrettyString());
	}

	@Given("Dietician creates PUT request to add new report by entering valid data into the form-data key and value fields {string}")
	public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_except_valid_vitals_data_and_valid_patient_id(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician add new report by entering valid data into the form-data key and value fields " +scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken)
//				.contentType(ContentType.MULTIPART)
				.queryParam("patientInfo", currentTestData.getPatientinfo())
				.multiPart("file", new File(currentTestData.getReportFilePath()) ,"application/pdf");
	}

	@Given("Dietician creates PUT request to add new report by entering valid {string}")
	public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_except_file_and_valid_patient_id(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician add new report by entering valid " +scenarioName);
		ResponseValidationFailureListener logOnFailure = (request, respSpec, response) -> LoggerLoad.error("Dietician not able to update  " +response.prettyPrint());;
		request = given().config(RestAssured.config().failureConfig(new FailureConfig().with().failureListeners(logOnFailure)))
				.header("Authorization", "Bearer " + dieticianToken)
				.contentType(ContentType.ANY)
				.queryParam("patientInfo", currentTestData.getPatientinfo())
				.queryParam("vitals", currentTestData.getVitals());

	}

	//	@Then("Dietician recieves {int} ok and with updated response body.")
	//	public void dietician_recieves_ok_and_with_updated_response_body(Integer int1) {
	//	    
	//	    
	//	}

	@Given("Dietician creates PUT request by entering valid patient ID and valid Mandatory data into the form-data key and value fields and valid patient ID")
	public void dietician_creates_put_request_by_entering_valid_mandatory_data_into_the_form_data_key_and_value_fields_and_valid_patient_id() {


	}

	@Given("Dietician creates PUT request by entering valid Additional details data into the form-data key and value fields and valid patient ID")
	public void dietician_creates_put_request_by_entering_valid_additional_details_data_into_the_form_data_key_and_value_fields_and_valid_patient_id() {


	}

	@Given("Dietician creates PUT request by entering valid patient ID and {string}")
	public void dietician_creates_put_request_by_entering_invalid_additional_details_data_into_the_form_data_key_and_value_fields_and_valid_patient_id(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician add new report by entering valid patient ID and " +scenarioName);
		ResponseValidationFailureListener logOnFailure = (request, respSpec, response) -> LoggerLoad.error("Dietician does not recieve 400 Bad request  " +response.prettyPrint());;
		request = given().config(RestAssured.config().failureConfig(new FailureConfig().with().failureListeners(logOnFailure)))
				.queryParam("vitals", currentTestData.getVitals())
				.multiPart("file", new File(currentTestData.getReportFilePath()) ,"application/pdf");

	}

	@Then("Dietician recieves {int} Bad request")
	public void dietician_recieves_bad_request(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Dietician recieves 400 Bad request "+ response.asPrettyString());

	}

	@Given("Dietician creates PUT request by entering {string}")
	public void dietician_creates_put_request_by_entering_invalid_additional_details_data_into_the_form_data_key_and_value_fields_and_invalid_patient_id(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician add new report by entering " +scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken)
				.queryParam("vitals", currentTestData.getVitals())
				.multiPart("file", new File(currentTestData.getReportFilePath()) ,"application/pdf");
	}

	@When("User send PUT http request to add new report with invalid patient id")
	public void User_send_put_http_request_to_add_new_report_with_invalid_patient_id() {
		response = request.when().put(currentTestData.getEndpoint()+currentTestData.getInvaidpatientid());
	}
	
	@Then("Dietician recieves {int} not found")
	public void dietician_recieves_not_found(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Dietician recieves 404 not found "+ response.asPrettyString());

	}

	@Given("Dietician creates POST request by {string}")
	public void dietician_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_valid_patient_id(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician add new report by sending post request and entering " +scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken)
				.queryParam("vitals", currentTestData.getVitals())
				.multiPart("file", new File(currentTestData.getReportFilePath()) ,"application/pdf");
	}

	@When("Dietician send POST http request with endpoint to add new report")
	public void dietician_send_post_http_request_with_endpoint() {
		response = request.when().post(currentTestData.getEndpoint()+patientID);

	}

	@Then("Dietician recieves {int} method not allowed")
	public void dietician_recieves_method_not_allowed(Integer int1) {
		response.then().statusCode(currentTestData.getExpectedStatusCode())
		.statusLine(currentTestData.getExpectedStatusLine());
		LoggerLoad.info("Dietician recieves 405 "+ response.asPrettyString());

	}
	
	@Given("Dietician creates PUT request with invalid endpoint to add new report by entering {string}")
	public void dietician_creates_put_request_with_invalid_endpoint_to_add_new_report_by_entering(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician add new report using invalid endpoint by entering " +scenarioName);
		request = given().header("Authorization", "Bearer " + dieticianToken)
				.queryParam("vitals", currentTestData.getVitals())
				.multiPart("file", new File(currentTestData.getReportFilePath()) ,"application/pdf");
	}

	@When("Dietician sent PUT http request with invalid endpoint")
	public void dietician_sent_put_http_request_with_invalid_endpoint() {
		response = request.when().put(currentTestData.getEndpoint()+patientID);

	}

	@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields and {string}")
	public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_valid_patient_id_with_invalid_content_type(String scenarioName) {
		currentTestData = JsonDataReader.getAllTestCase(jsondatapath, scenarioName);
		LoggerLoad.info("Dietician add new report by entering invalid content type");
		request = given().header("Authorization", "Bearer " + dieticianToken)
				.contentType(ContentType.JSON)
//				.header("Content-Type", "application/json")
				.queryParam("vitals", currentTestData.getVitals());
//				.multiPart("file", new File(currentTestData.getReportFilePath()));
	}

	@Then("Dietician recieves {int} unsupported media type")
	public void dietician_recieves_unsupported_media_type(Integer int1) {
		response.then().log().all();
//		response.then().statusCode(currentTestData.getExpectedStatusCode())
//		.statusLine(currentTestData.getExpectedStatusLine());
//		LoggerLoad.info("Dietician recieves 404 not found "+ response.asPrettyString());

	}
}
