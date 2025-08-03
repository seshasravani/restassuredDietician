Feature: Get All Patient


Background: admin and dietician authentication flow
    Given Admin logs in with valid credentials
    When Admin creates a new dietician with valid details
    Then Admin receives dietician credentials in the response
    
    Given Dietician has email and password from previous step
    When Dietician send Post request with email and password 
    Then daitician recieved dietician token in the response creation is successful with valid response


  Scenario: Set no auth Check dietician able to retrieve all patients
    Given Dietician creates GET request without Authorization header
    When Dietician sends GET HTTP request with valid endpoint
    Then the Dietician receives Unauthorized

  Scenario: Set admin token Check admin able to retrieve all patients
    Given Admin creates GET request with Authorization token
    When Admin sends GET HTTP request with valid endpoint
    Then  the Admin receives Forbidden

  Scenario: Set patient token Check patient able to retrieve all patients
    Given Patient creates GET request with Authorization token
    When Patient sends GET HTTP request with valid endpoint
    Then the Patient receives Forbidden

  Scenario: Set dietician token Check dietician able to retrieve all patients
    Given Dietician creates GET request with Authorization token
    When Dietician sends GET HTTP request with valid endpoint
    Then the Dietician receives OK with response body

  Scenario: Check dietician able to retrieve all patient with invalid method PUT
    Given Dietician creates PUT request with Authorization token
    When Dietician sends PUT HTTP request with valid endpoint
    Then the Dietician receives Method Not Allowed

  Scenario: Check dietician able to retrieve all patient with invalid endpoint
    Given Dietician creates GET request with Authorization token
    When Dietician sends GET HTTP request with invalid endpoint
    Then the Dietician receives Not Found