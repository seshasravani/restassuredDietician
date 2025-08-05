Feature: Get All Patient

Background: admin and dietician authentication flow
    Given Admin logs in with valid credentials
    When Admin creates a new dietician with valid details
    Then Admin receives dietician credentials in the response
    
    Given Dietician has email and password from previous step
    When Dietician send Post request with email and password 
    Then daitician recieved dietician token in the response creation is successful with valid response

  Scenario: Set No Auth Check dietician able to retrieve all morbidities details
    Given Dietician creates GET request to retrieve Morbidity without Authorization header
    When Dietician sends GET HTTP request with valid endpoint to retrieve Morbidity
    Then Dietician receives Unauthorized message

  Scenario: Set PAtient Token Check pateint is able to retrieve all morbidities details
    Given Patient creates GET request to retrieve Morbidity without Authorization header
    When Patient sends GET HTTP request with valid endpoint to retrieve Morbidity
    Then Patient receives Forbidden Message

  Scenario: Set Admin Token Check admin able to retrieve all morbidities details
    Given Admin creates GET request to retrieve Morbidity without Authorization header
    When Admin sends GET HTTP request with valid endpoint to retrieve Morbidity
    Then Dietician receives OK message with response body

  Scenario: Set Admin Token Check admin able to retrieve all morbidities details with invalid method
    Given Admin creates POST request with Authorization token to retieve Morbididty
    When Admin sends POST HTTP request with valid endpoint to retieve Morbididty
    Then Admin receives Method Not Allowed message

  Scenario: Set Admin Token Check admin able to retrieve all morbidities details with invalid endpoint
    Given Admin creates GET request with Authorization token to retieve Morbididty
    When Admin sends GET HTTP request with invalid endpoint to retieve Morbididty
    Then Dietician receives Not Found message

  Scenario: Set Dietician Token Check dietician able to retrieve all morbidities details
    Given Dietician creates GET request with Authorization token to retieve Morbididty
    When Dietician sends GET HTTP request with valid endpoint to retieve Morbididty
    Then Dietician receives OK with response body message

  Scenario: Set Dietician Token Check dietician able to retrieve all morbidities details with invalid method
    Given Dietician creates POST request with Authorization token to retieve Morbididty
    When Dietician sends POST HTTP request with endpoint to retieve Morbididty
    Then Dietician receives Method Not Allowed message

  Scenario: Set Dietician Token Check dietician able to retrieve all morbidities details with invalid endpoint
    Given Dietician creates GET request with Authorization token to retieve Morbididty
    When Dietician sends GET HTTP request with invalid endpoint to retieve Morbididty
    Then Dietician receives Not Found message
