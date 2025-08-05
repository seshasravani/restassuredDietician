package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import com.CalorieHackers_POJO.TestDataPOJO;
import com.CalorieHackers_Utilities.JsonDataReader;
import com.CalorieHackers_Utilities.LoggerLoad;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteDietician {

	private RequestSpecification request;
	private Response response;
	private TestDataPOJO testData;
	int dieticianID = createDietician_SD.dieticianID;
	String adminToken = userLogin_POST_SD.adminToken;
	int dieticianId;

	@Given("Admin bearer token is set, and the admin successfully created a dietician account")
	public void admin_bearer_token_is_set_and_the_admin_successfully_created_a_dietician_account() {

		LoggerLoad.info("Admin Token: " + adminToken);

	}

	@Given("Admin creates DELETE request for delete Dietician")
	public void admin_creates_delete_request_for_delete_dietician() {
		request = given().header("Authorization", "Bearer " + adminToken).contentType("application/json");

	}

	@When("Admin send DELETE http request with endpoint for delete dietician") // valid id
	public void admin_send_delete_http_request_with_endpoint_for_delete_dietician() {
		testData = JsonDataReader.getAllTestCase("src/test/resources/TestData/TestData.json",
				"Check admin able to delete dietician by ID");
		// dieticianId = testData.getDieticianID();
		LoggerLoad.info("DieticianID " + dieticianID);
		String url = "/dietician/" + dieticianID;
		LoggerLoad.info("Sending DELETE Request to URL: " + url);
		response = request.delete(url);
		LoggerLoad.info("Response Body: " + response.getBody().asString());
	}

	// invalid id

	@When("Admin send DELETE http request with endpoint for delete dietician with invalid id")
	public void admin_send_delete_http_request_with_endpoint_for_delete_dietician_with_invalid_id() {

		// Fetch the data for this scenario instead of reusing previous testData
		testData = JsonDataReader.getAllTestCase("src/test/resources/TestData/TestData.json",
				"Check admin able to delete dietician by invalid id");

		dieticianId = testData.getInvalidDieticianId(); // Use invalid ID from JSON
		String url = "/dietician/" + dieticianId;

		LoggerLoad.info("Sending DELETE Request to URL: " + url);
		response = request.delete(url);
		LoggerLoad.info("Response Body: " + response.getBody().asString());
	}

	@Then("Admin receives {int} ok dietician is deleted")
	public void admin_recieves_ok_dietician_is_deleted(Integer expectedStatusCode) {
		assertEquals(expectedStatusCode.intValue(), response.getStatusCode());

		if (response.getStatusCode() == 200) {
			LoggerLoad.info("Deleted Dietician with ID: " + dieticianId);
		} else if (response.getStatusCode() == 404) {
			LoggerLoad.info("Dietician with ID: " + dieticianId + " not found for deletion.");
		}
	}

	@Then("Admin receives {int} invalid dietician id")
	public void admin_recieves_not_found(Integer expectedStatusCode) {
		assertEquals(expectedStatusCode.intValue(), response.getStatusCode());

	}

	@When("Admin send DELETE http request with invalid endpoint for delete dietician")
	public void admin_send_delete_http_request_with_invalid_endpoint_for_delete_dietician() {

		testData = JsonDataReader.getAllTestCase("src/test/resources/TestData/TestData.json",
				"Check admin able to delete dietician by invalid endpoint");

		String url = testData.getinvalidUrl() + "/" + dieticianID;
		LoggerLoad.info("Sending DELETE Request to URL: " + url);

		response = request.delete(url);
		LoggerLoad.info("Response Body: " + response.getBody().asString());

	}

	@Then("Admin recieves {int} invalid dietician url")
	public void admin_recieves_invalid_dietician_url(Integer expectedStatusCode) {
		assertEquals(expectedStatusCode.intValue(), response.getStatusCode());

	}

}
