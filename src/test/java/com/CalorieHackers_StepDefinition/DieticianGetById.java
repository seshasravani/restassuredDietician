package com.CalorieHackers_StepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.CalorieHackers_Utilities.JsonDataReader;

import com.CalorieHackers_POJO.DieticianPOJO;
import com.CalorieHackers_POJO.TestDataPOJO;
import com.CalorieHackers_Utilities.ConfigReader;
import com.CalorieHackers_Utilities.LoggerLoad;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DieticianGetById {

    private Response response;
    private final String baseUri = ConfigReader.getKeyValues("BASE_URL");
    private static String authToken;
    private static final String jsondatapath = ConfigReader.getKeyValues("JSON_PATH");
    private TestDataPOJO currentTestData;
    
    
   

    // === Background step ===
    @Given("Admin has a valid auth token")
    public void admin_has_a_valid_auth_token() {
        // Hardcoded token for now
        authToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNDAxQGdtYWlsLmNvbSIsImlhdCI6MTc1NDE2NTU1NCwiZXhwIjoxNzU0MTk0MzU0fQ.SsJ7XATcHfh56yM8D0gQ40XqNhLP97rbmRMQlNBiGd3Jp-DXHK3ITFDPUNVotEI3oBTLEpt36o2ks8DaWnTxdw";
        LoggerLoad.info("Using hardcoded Auth Token: " + authToken);
    }

    @Given("Admin create GET request")
    public void admin_create_get_request() {
        
    	 LoggerLoad.info("Preparing GET request");
    	
    	
    }

    @When("Admin send GET http request with endpoint")
    public void admin_send_get_http_request_with_endpoint() {
        String dieticianId = "123";  // Hardcoded for now
        String endpoint = ConfigReader.getKeyValues("get.dietician.by.id.endpoint") + "/" + dieticianId;
      
        response = given()
                .baseUri(baseUri)
                .header("Authorization", authToken)
                .when()
                .get(endpoint);

        LoggerLoad.info("Response Body: \n" + response.prettyPrint());
       


    }

    @Then("Admin receives 200 ok with details of the dietician id")
    public void admin_receives_200_ok_with_details_of_the_dietician_id() {
        assertEquals(200, response.getStatusCode());
        DieticianPOJO dietician = response.as(DieticianPOJO.class);
        LoggerLoad.info("Verified Dietician ID: " + dietician.getId());
        assertEquals(123, dietician.getId());
      
    }
    
    
    // scenario -2
    
    
    @Given("Admin creates POST request")
    public void admin_creates_post_request() {
    	
    	 LoggerLoad.info("Preparing POST request");
        
    }

    @When("Admin sends POST http request with endpoint for dietician")
    public void admin_sends_post_http_request_with_endpoint() {
       
    	String dieticianId = "123";  // Hardcoded for now
        String endpoint = ConfigReader.getKeyValues("get.dietician.by.id.endpoint") + "/" + dieticianId;

        response = given()
                .baseUri(baseUri)
                .header("Authorization", authToken)
                .when()
                .post(endpoint);

        LoggerLoad.info("Response Body: \n" + response.prettyPrint());
    }

    @Then("Admin receives 405 method not allowed")
    public void admin_receives_method_not_allowed(){
    	
    	assertEquals(405, response.getStatusCode());
        
    }
    

    //invalid dietId
    
    @Given("Admin create GET request for invalid Id")
    public void admin_create_get_request_for_invalid_Id() {
        // Load test data for invalid ID scenario from JSON
        currentTestData = JsonDataReader.getAllTestCase(jsondatapath, "Check admin able to retrieve dietician by invalid id");
        LoggerLoad.info("Preparing GET request for invalid dietician ID: " + currentTestData.getInvalidDieticianId());
    }


       
    @When("Admin send GET http request with invalidId endpoint")
    public void admin_send_get_http_request_with_invalidId_endpoint() {
        // Build the endpoint with invalid ID
        String endpointWithInvalidId = currentTestData.getEndpoint() + "/" + currentTestData.getInvalidDieticianId();
        
        // Print final URL (including base URI if set)
        System.out.println("Calling URL: " + RestAssured.baseURI + endpointWithInvalidId);
        LoggerLoad.info("Calling URL: " + RestAssured.baseURI + endpointWithInvalidId);
        
        // Send GET request
        response = given()
                    .header("Authorization", authToken)
                    .when()
                    .get(endpointWithInvalidId);
                    
        // Log response
        LoggerLoad.info("Response Body: \n" + response.prettyPrint());
    }
    
    
    @Then("Admin receives {int} not found")
    public void admin_receives_not_found(Integer expectedStatusCode) {
        // Compare actual response code with expectedStatusCode
        assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
    }

    @Given("Admin create GET request for invalid endpoint")
    public void admin_create_get_request_for_invalid_endpoint() {
    	
    	 // Load test data for invalid endpoint from JSON
        currentTestData = JsonDataReader.getAllTestCase(jsondatapath, "Check admin able to retrieve dietician by id with invalid endpoint");
        //LoggerLoad.info("Preparing GET request for invalid endpoint: " + currentTestData.getInvalidDieticianId());
        
    }

    
    
    
    @When("Admin send GET http request with invalid endpoint")
    public void admin_send_get_http_request_with_invalid_endpoint() {
    	
    	
    	 // Build invalid endpoint
        String endpointInvalid = currentTestData.getEndpoint() + "/" + currentTestData.getvalidDieticianId();
        
        // Print final URL (including base URI if set)
        System.out.println("Calling URL: " + RestAssured.baseURI + endpointInvalid);
        LoggerLoad.info("Calling URL: " + RestAssured.baseURI + endpointInvalid);
        
        // Send GET request
        response = given()
                    .header("Authorization", authToken)
                    .when()
                    .get(endpointInvalid);
                    
        // Log response
        LoggerLoad.info("Response Body: \n" + response.prettyPrint());
        
    }

    @Then("Admin recieves {int} not found")
    public void admin_recieves_not_found(Integer expectedStatusCode) {
    	
    	// Compare actual response code with expectedStatusCode
        assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
        
    }
    
    
    @Given("No Authentication is set")
    public void no_authentication_is_set() {
       
    }
    
    
    @When("Admin sends GET http request without auth")
    public void admin_sends_get_http_request_without_auth() {
        String dieticianId = "123";  // Hardcoded
        String endpoint = ConfigReader.getKeyValues("get.dietician.by.id.endpoint") + "/" + dieticianId;

        response = given()
                .baseUri(baseUri)
                // No Authorization header
                .when()
                .get(endpoint);

        LoggerLoad.info("Response Body: \n" + response.prettyPrint());
    }


    @Then("Admin receives {int} unauthorized")
    public void admin_receives_unauthorized(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
        assertTrue(response.getStatusLine().contains("Unauthorized"));
    }
    
    @Then("Admin receives 401 unauthorized for noauth")
    public void admin_receives_401_unauthorized() {
        int expectedStatusCode = 401;
        int actualStatusCode = response.getStatusCode(); 
        assertEquals(expectedStatusCode, actualStatusCode);
    }


    }

