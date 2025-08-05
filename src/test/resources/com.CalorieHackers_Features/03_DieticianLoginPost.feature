@userLoginPostDietician
Feature: POST (/LOGIN)

  Background: No auth

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

  @dieticianLogin
  Scenario: Check user able to login as dietician with valid credential
    Given User creates Post request with request body as dietician
    When User send POST HTTP request with endpoint as dietician
    Then User receives 200 created with response body as dietician
