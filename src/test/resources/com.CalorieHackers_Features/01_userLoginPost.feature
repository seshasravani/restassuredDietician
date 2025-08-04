@userLoginPostAdmin
Feature: POST (/LOGIN)

  Background: No auth

  Scenario: Check user able to login as admin with invalid email
    Given User creates Post request with invalid email
    When User send POST HTTP request with endpoint
    Then User receives 401 unauthorized

  Scenario: Check user able to login as admin with invalid email format
    Given User creates Post request with invalid email format
    When User send POST HTTP request with endpoint
    Then User receives 401 unauthorized

  Scenario: Check user able to login as admin with invalid Password
    Given User creates Post request with invalid Password
    When User send POST HTTP request with endpoint
    Then User receives 401 unauthorized

  Scenario: Check user able to login as admin with empty credentials
    Given User creates Post request with empty credentials
    When User send POST HTTP request with endpoint
    Then User receives 401 unauthorized

  Scenario: Check user able to login as admin with valid credential and invalid method
    Given User creates GET request with request body.Request body : Userlogin and password
    When User send GET HTTP request with endpoint
    Then User receives 405 method not allowed

  Scenario: Check user able to login as admin with valid credential and invalid endpoint
    Given User creates Post request with request body.Request body : Userlogin and password
    When User send POST HTTP request with invalid endpoint
   Then User receives 401 unauthorized

  Scenario: Check user able to login as admin with valid credential and invalid content type
    Given User creates Post request with request body and invalid content type.Request body : Userlogin and password
    When User send POST HTTP request with endpoint
    Then User receives 415 unsupported media type

  Scenario: Check user able to login as dietician with invalid email
    Given User creates Post request with invalid email as dietician
    When User send POST HTTP request with endpoint
    Then User receives 401 unauthorized

  Scenario: Check user able to login as dietician with invalid email format
    Given User creates Post request with invalid email format as dietician
    When User send POST HTTP request with endpoint
    Then User receives 401 unauthorized

  Scenario: Check user able to login as dietician with invalid Password
    Given User creates Post request with invalid Password as dietician
    When User send POST HTTP request with endpoint
    Then User receives 401 unauthorized

  Scenario: Check user able to login as patient with invalid email
    Given User creates Post request with invalid email as patient
    When User send POST HTTP request with endpoint
    Then User receives 401 unauthorized

  Scenario: Check user able to login as patient with invalid email format
    Given User creates Post request with invalid email format as patient
    When User send POST HTTP request with endpoint
    Then User receives 401 unauthorized

  Scenario: Check user able to login as patient with invalid Password
    Given User creates Post request with invalid Password as patient
    When User send POST HTTP request with endpoint
    Then User receives 401 unauthorized

  @adminLogin
  Scenario: Check user able to login as admin with valid data
    Given User creates Post request with request body as admin
    When User send POST HTTP request with endpoint as admin
    Then User receives 200 created with response body as admin
    
    @dieticianLogin
  Scenario: Check user able to login as dietician with valid credential
    Given User creates Post request with request body as dietician
    When User send POST HTTP request with endpoint as dietician
    Then User receives 200 created with response body as dietician

  @patientLogin
  Scenario: Check user able to login as patient with valid credential
    Given User creates Post request with request body as patient
    When User send POST HTTP request with endpoint as patient
    Then User receives 200 created with response body as patient
    
