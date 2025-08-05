@userLoginPostpatient
Feature: POST (/LOGIN)

  Background: No auth

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

  @patientLogin
  Scenario: Check user able to login as patient with valid credential
    Given User creates Post request with request body as patient
    When User send POST HTTP request with endpoint as patient
    Then User receives 200 created with response body as patient
