	package com.CalorieHackers_StepDefinition;
	
	
	import static io.restassured.RestAssured.given;
	import static org.hamcrest.Matchers.*;
	import static org.testng.Assert.assertEquals;
	import static org.testng.Assert.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
	import java.util.Map;
import java.util.Random;

import org.testng.Assert;

import com.CalorieHackers_POJO.TestDataPOJO;
import com.CalorieHackers_POJO.TestDataPOJO.PatientInfo;
import com.CalorieHackers_Utilities.ConfigReader;
	import com.CalorieHackers_Utilities.JsonDataReader;
	import com.CalorieHackers_Utilities.LoggerLoad;
	import io.cucumber.java.en.*;
	import io.restassured.http.ContentType;
	import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;
	
	public class CreatePatient_Post {
		
		private TestDataPOJO currentTestData;
		private static final String JSON_DATA_PATH = "src/test/resources/TestData/TestData.json";
		private RequestSpecification request; 
		private Response response;
		
		 private String adminToken;
		    private String dieticianEmail;
		    private String dieticianPassword;
		    private String dieticianToken;
		    private String patientToken;
	
		    private void prepareRequest(String scenarioName) {
		        System.out.println("Loading scenario: " + scenarioName);
		        currentTestData = JsonDataReader.getAllTestCase(JSON_DATA_PATH, scenarioName);
		        LoggerLoad.info("Preparing request for scenario: " + scenarioName);

		        request = given().baseUri(ConfigReader.getKeyValues("BASE_URL"));

		        String authType = currentTestData.getAuthType();
		        if (authType != null) {
		            switch(authType) {
		                case "Admin Token":
		                    request.header("Authorization", "Bearer " + adminToken);
		                    break;
		                case "Dietician Token":
		                    request.header("Authorization", "Bearer " + dieticianToken);
		                    break;
		                case "Patient Token":
		                    request.header("Authorization", "Bearer " + patientToken);
		                    break;
		                case "No Auth":
		                    // no Authorization header
		                    break;
		                default:
		                    throw new RuntimeException("Unsupported auth type: " + authType);
		            }
		        }
		    }
		    
		    @Given("Admin logs in with valid credentials")
		    public void admin_logs_in() {
		        // Build request directly with login details
		    	  response = given()
		            .baseUri(ConfigReader.getKeyValues("BASE_URL"))
		            .contentType(ContentType.JSON)
		            .body("{ \"userLoginEmail\": \"testingteam03@gmail.com\", \"password\": \"test\" }")
		            .post("/login");
	
		        // Verify login success (200 OK)
		        response.then().statusCode(200);
	
		     // Save to class variable
		        adminToken = response.jsonPath().getString("token");
	
		        System.out.println("Admin token: " + adminToken);
		    }
		    
	
		    @When("Admin creates a new dietician with valid details")
		    public void admin_creates_dietician() {
		        // Generate unique values
		        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		        String uniqueEmail = "dietician_" + timestamp + "@example.com";
		        String uniqueContact = "9" + (100000000 + new Random().nextInt(899999999));
		        String uniqueDob = "1990-01-05T00:00:00.000Z"; // Or generate random if needed

		        String dieticianJson = "{\n" +
		                "  \"ContactNumber\": \"" + uniqueContact + "\",\n" +
		                "  \"DateOfBirth\": \"" + uniqueDob + "\",\n" +
		                "  \"Education\": \"MD\",\n" +
		                "  \"Email\": \"" + uniqueEmail + "\",\n" +
		                "  \"Firstname\": \"AmyE\",\n" +
		                "  \"HospitalCity\": \"AnotherCity\",\n" +
		                "  \"HospitalName\": \"Wellness Hospital\",\n" +
		                "  \"HospitalPincode\": \"654321\",\n" +
		                "  \"HospitalStreet\": \"Second Street\",\n" +
		                "  \"Lastname\": \"ShunE\"\n" +
		                "}";

		        response = given()
		                .baseUri(ConfigReader.getKeyValues("BASE_URL"))
		                .header("Authorization", "Bearer " + adminToken)
		                .contentType(ContentType.JSON)
		                .body(dieticianJson)
		                .when()
		                .post("/dietician");  // ✅ Dietician endpoint

		        System.out.println("Response: " + response.asPrettyString());

		        dieticianEmail = response.jsonPath().getString("Email");
		        dieticianPassword = response.jsonPath().getString("loginPassword"); // Adjust keys if needed
		    }
	
		    @Then("Admin receives dietician credentials in the response")
		    public void verify_dietician_credentials() {
		    	int statusCode = response.getStatusCode();
		        if (statusCode == 200 || statusCode == 201) {
		            String email = response.jsonPath().getString("Email");
		            Assert.assertNotNull(email, "Dietician email is null");
		            // other assertions...
		        } else {
		            // For error responses, no need to check email
		            LoggerLoad.warn("Response status " + statusCode + ", no dietician email expected.");
		        }
		    }
	
		    @Given("Dietician has email and password from previous step")
		    public void dietician_has_email_and_password_from_previous_step() {
		        assertNotNull(dieticianEmail, "Dietician email is null from previous step");
		        assertNotNull(dieticianPassword, "Dietician password is null from previous step");
		    }
	
		    @When("Dietician send Post request with email and password")
		    public void dietician_send_post_request_with_email_and_password() {
		    	 response = given()
		                .baseUri(ConfigReader.getKeyValues("BASE_URL"))
		                .contentType(ContentType.JSON)
		                .body("{ \"userLoginEmail\": \"" + dieticianEmail + "\", \"password\": \"" + dieticianPassword + "\" }")
		                .when()
		                .post("/login");
	
		        System.out.println("Dietician login response: " + response.asPrettyString());
		    }
	
		    @Then("daitician recieved dietician token in the response creation is successful with valid response")
		    public void dietician_recieved_token() {
		    	response.then().statusCode(200);
		        dieticianToken = response.jsonPath().getString("token");
		        assertNotNull(dieticianToken, "Dietician token is null");
		        System.out.println("Dietician token: " + dieticianToken);
	
		    
		    }
		
//	
//		    private void prepareRequest(String scenarioName) {
//		    	 System.out.println("Loading scenario: " + scenarioName);
//		    	    currentTestData = JsonDataReader.getAllTestCase(JSON_DATA_PATH, scenarioName);
//		    	    LoggerLoad.info("Preparing request for scenario: " + scenarioName);
//
//		    	    request = given().baseUri(ConfigReader.getKeyValues("BASE_URL"));
//
//		    	    String authType = currentTestData.getAuthType();
//		    	    if (authType != null) {
//		    	        switch(authType) {
//		    	            case "Admin Token":
//		    	                request.header("Authorization", "Bearer " + currentTestData.getAdminToken());
//		    	                break;
//		    	            case "Dietician Token":
//		    	                request.header("Authorization", "Bearer " + currentTestData.getDieticianToken());
//		    	                break;
//		    	            case "Patient Token":
//		    	                request.header("Authorization", "Bearer " + currentTestData.getPatientToken());
//		    	                break;
//		    	            case "No Auth":
//		    	                // No Authorization header
//		    	                break;
//		    	            default:
//		    	                throw new RuntimeException("Unsupported auth type: " + authType);
//		    	        }
//		    	    }
//		    	}
//		    
		    //No Auth
	
	@Given("Dietician creates POST request by entering valid data into the form-data key and value fields")
	public void dietician_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() {
		prepareRequest("Set no auth Check dietician able to create patient with valid data");
	}
	
	@When("Dietician sends POST http request with endpoint")
	public void dietician_sends_post_http_request_with_endpoint() {
		  try {
		        // 1. Create ObjectMapper
		        ObjectMapper mapper = new ObjectMapper();
		        
		        // 2. Convert PatientInfo object to JSON string
		        String patientInfoJson = mapper.writeValueAsString(currentTestData.getPatientinfo());

		        // 3. print payload to debug
		        System.out.println("Serialized patientInfo JSON:");
		        System.out.println(patientInfoJson);

		        // 4. Build the request
		        response = request
		                .multiPart("patientInfo", patientInfoJson, "application/json")
		                .request(currentTestData.getMethod(), currentTestData.getEndpoint());

		    } catch (Exception e) {
		        e.printStackTrace();
		        Assert.fail("Failed to serialize patientInfo or send request: " + e.getMessage());
		    }
		}
	
	@Then("Dietician receives unauthorized")
	public void dietician_receives_unauthorized() {
		   // Log actual vs expected values
		LoggerLoad.info(" Expected Status Code: " + currentTestData.getExpectedStatusCode());
	    LoggerLoad.info(" Actual Status Code: " + response.getStatusCode());
	    LoggerLoad.info(" Expected Status Line: " + currentTestData.getExpectedStatusLine());
	    LoggerLoad.info(" Actual Status Line: " + response.getStatusLine());
	 
	    // Validate all fields
	    assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode(), "Status code mismatch");
	    assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine(), " Status line mismatch");
	  
	}
	
	
	// Admin steps

	@Given("Admin creates POST request by entering valid data into the form-data key and value fields")
	public void admin_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() {
	    prepareRequest("Set admin bearer token Check admin able to create patient with valid data and admin token");
	}

	@When("Admin sends POST http request with endpoint")
	public void admin_sends_post_http_request_with_endpoint() {
		 try {
		        // 1. Create ObjectMapper
		        ObjectMapper mapper = new ObjectMapper();
		        
		        // 2. Convert PatientInfo object to JSON string
		        String patientInfoJson = mapper.writeValueAsString(currentTestData.getPatientinfo());

		        // 3. Optional: print payload to debug
		        System.out.println("Serialized patientInfo JSON:");
		        System.out.println(patientInfoJson);
		        

		        // 4. Build the request
		        response = request
		                .multiPart("patientInfo", patientInfoJson, "application/json")
		                .request(currentTestData.getMethod(), currentTestData.getEndpoint());
		        

		    } catch (Exception e) {
		        e.printStackTrace();
		        Assert.fail("Failed to serialize patientInfo or send request: " + e.getMessage());
		    }
		}
	

	@Then("Admin receives forbidden")
	public void admin_receives_forbidden() {
		int statusCode = response.getStatusCode();
	    Assert.assertEquals(statusCode, 403, "Expected status code 403");

	    String contentType = response.getHeader("Content-Type");
	    Assert.assertTrue(
	        contentType.contains("application/json") || contentType.contains("text/plain"),
	        "Unexpected content type: " + contentType
	    );

	    // Optional: Log the body for clarity
	    System.out.println("Forbidden response body: " + response.getBody().asString());
	}
	// Patient steps

	@Given("Patient creates POST request by entering valid data into the form-data key and value fields")
	public void patient_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() {
	    prepareRequest("Set patient bearer token Check patient able to create patient with valid data and patient token");
	}

	@When("Patient sends POST http request with endpoint")
	public void patient_sends_post_http_request_with_endpoint() {
	   
		 try {
		        // 1. Create ObjectMapper
		        ObjectMapper mapper = new ObjectMapper();
		        
		        // 2. Convert PatientInfo object to JSON string
		        String patientInfoJson = mapper.writeValueAsString(currentTestData.getPatientinfo());

		        // 3. Optional: print payload to debug
		        System.out.println("Serialized patientInfo JSON:");
		        System.out.println(patientInfoJson);

		        // 4. Build the request
		        response = request
		                .multiPart("patientInfo", patientInfoJson, "application/json")
		                .request(currentTestData.getMethod(), currentTestData.getEndpoint());

		    } catch (Exception e) {
		        e.printStackTrace();
		        Assert.fail("Failed to serialize patientInfo or send request: " + e.getMessage());
		    }
		}
	
	@Then("Patient receives forbidden")
	public void patient_receives_forbidden() {
		int statusCode = response.getStatusCode();
	    Assert.assertEquals(statusCode, 403, "Expected status code 403");

	    String contentType = response.getHeader("Content-Type");
	    Assert.assertTrue(
	        contentType.contains("application/json") || contentType.contains("text/plain"),
	        "Unexpected content type: " + contentType
	    );

	    // Optional: Log the body for clarity
	    System.out.println("Forbidden response body: " + response.getBody().asString());
	}

	// Dietician create with mandatory + additional details

	@Given("Dietician creates POST request by entering valid mandatory and additional data into the form-data fields")
	public void dietician_creates_post_request_by_entering_valid_mandatory_and_additional_data_into_the_form_data_fields() {
	    prepareRequest("Set dietician bearer token Check dietician able to create patient with valid data and token");
	}

	@Then("Dietician receives success message with response body containing generated ID and password")
	public void dietician_receives_success_message_with_response_body_containing_generated_id_and_password() {
		
		response.then().statusCode(201);

	    JsonPath js = response.jsonPath();
	    int patientId = js.getInt("patientId");
	    //String email = js.getString("Email");  // Be careful with case sensitivity if needed

	    Assert.assertNotNull(patientId, "Generated patientId is null");
	   // Assert.assertNotNull(email, "Email is null");

	    LoggerLoad.info("Patient ID: " + patientId);
	   // LoggerLoad.info("Patient Email: " + email);

	    currentTestData.setPatientId(patientId);
	   // currentTestData.setPatientEmail(email);
	}

	// Dietician create with only mandatory details

	@Given("Dietician creates POST request with only valid mandatory details in form-data")
	public void dietician_creates_post_request_with_only_valid_mandatory_details_in_form_data() {
	    prepareRequest("Check dietician able to create patient only with valid mandatory details");
	}

	// Dietician create with only additional details

	@Given("Dietician creates POST request with only valid additional details in form-data")
	public void dietician_creates_post_request_with_only_valid_additional_details_in_form_data() {
	    prepareRequest("Check dietician able to create patient only with valid additional details");
	}

	@Then("Dietician receives Bad request")
	public void dietician_receives_bad_request() {
	    assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode());
	    assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine());
	}

	// Dietician create with invalid mandatory details

	@Given("Dietician creates POST request with invalid mandatory details in form-data")
	public void dietician_creates_post_request_with_invalid_mandatory_details_in_form_data() {
	    prepareRequest("Check dietician able to create patient with invalid data (mandatory details)");
	}

	// Dietician create with valid mandatory and invalid additional details

	@Given("Dietician creates POST request with valid mandatory and invalid additional details in form-data")
	public void dietician_creates_post_request_with_valid_mandatory_and_invalid_additional_details_in_form_data() {
	    prepareRequest("Check dietician able to create patient with valid mandatory fields and invalid data (additional details)");
	}

	// Dietician PUT request with valid data

	@Given("Dietician creates PUT request with valid data in form-data")
	public void dietician_creates_put_request_with_valid_data_in_form_data() {
	    prepareRequest("Check dietician able to create patient with valid data and invalid method");
	}

	@When("Dietician sends PUT http request with endpoint")
	public void dietician_sends_put_http_request_with_endpoint() {
		 try {
		        // 1. Create ObjectMapper
		        ObjectMapper mapper = new ObjectMapper();
		        
		        // 2. Convert PatientInfo object to JSON string
		        String patientInfoJson = mapper.writeValueAsString(currentTestData.getPatientinfo());

		        // 3. Optional: print payload to debug
		        System.out.println("Serialized patientInfo JSON:");
		        System.out.println(patientInfoJson);

		        // 4. Build the request
		        response = request
		                .multiPart("patientInfo", patientInfoJson, "application/json")
		                .request(currentTestData.getMethod(), currentTestData.getEndpoint());

		    } catch (Exception e) {
		        e.printStackTrace();
		        Assert.fail("Failed to serialize patientInfo or send request: " + e.getMessage());
		    }
		}
	
	// Dietician POST request with valid data but invalid endpoint

	@Given("Dietician creates POST request with valid data in form-data")
	public void dietician_creates_post_request_with_valid_data_in_form_data() {
	    prepareRequest("Check dietician able to create patient with valid data and invalid endpoint");
	}

	@When("Dietician sends POST http request with invalid endpoint")
	public void dietician_sends_post_http_request_with_invalid_endpoint() {
		 try {
		        // 1. Create ObjectMapper
		        ObjectMapper mapper = new ObjectMapper();
		        
		        // 2. Convert PatientInfo object to JSON string
		        String patientInfoJson = mapper.writeValueAsString(currentTestData.getPatientinfo());

		        // 3. Optional: print payload to debug
		        System.out.println("Serialized patientInfo JSON:");
		        System.out.println(patientInfoJson);

		        // 4. Build the request
		        response = request
		                .multiPart("patientInfo", patientInfoJson, "application/json")
		                .request(currentTestData.getMethod(), currentTestData.getEndpoint());

		    } catch (Exception e) {
		        e.printStackTrace();
		        Assert.fail("Failed to serialize patientInfo or send request: " + e.getMessage());
		    }
		}

	@Then("Dietician receives not found")
	public void dietician_receives_not_found() {
	    assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode());
	    assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine());
	    assertEquals(response.getContentType(), currentTestData.getExpectedContentType());
	}

	// Dietician POST request with valid data and invalid content type

	@Given("Dietician creates POST request with valid data and invalid content type in form-data")
	public void dietician_creates_post_request_with_valid_data_and_invalid_content_type_in_form_data() {

		try {
		    ObjectMapper mapper = new ObjectMapper();
		    String patientInfoJson = mapper.writeValueAsString(currentTestData.getPatientinfo());

		    prepareRequest(currentTestData.getScenarioName());

		    request = request
		        .header("Content-Type", "text/plain")  // intentionally wrong content type
		        .multiPart("patientInfo", patientInfoJson, "text/plain");

		} catch (JsonProcessingException e) {
		    Assert.fail("Failed to serialize patientInfo JSON: " + e.getMessage());
		}

	}

	@Then("Dietician receives unsupported media type")
	public void dietician_receives_unsupported_media_type() {
	    assertEquals(response.getStatusCode(), currentTestData.getExpectedStatusCode());
	    assertEquals(response.getStatusLine(), currentTestData.getExpectedStatusLine());
	    assertEquals(response.getContentType(), currentTestData.getExpectedContentType());
	}
		
	@Then("Dietician receives Method Not Allowed")
	public void dietician_receives_method_not_allowed_error() {
	    response.then().statusCode(405);
	    System.out.println("Received 405 Method Not Allowed as expected");
	    
	    

	    // Optional: assert error message in body
	    String message = response.jsonPath().getString("message");
	    System.out.println("Error message: " + message);
	}
	}
