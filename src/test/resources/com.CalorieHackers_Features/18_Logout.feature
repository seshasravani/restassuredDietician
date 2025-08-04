@Logout
Feature: GET (/LOGOUTDIETICIAN)

  Background: 
             Set bearer token in header


  @adminLogout
  Scenario: Check admin able to logout
    Given User creates GET request as admin
    When User send GET HTTP request with endpoint for logout
    Then User recieves 200 created with Logout successful message

  @dieticianLogout
  Scenario: Check dietician able to logout
    Given User creates GET request as dietician
    When User send GET HTTP request with endpoint for logout
    Then User recieves 200 created with Logout successful message

  @patientLogout
  Scenario: Check patient able to logout
    Given User creates GET request as patient
    When User send GET HTTP request with endpoint for logout
    Then User recieves 200 created with Logout successful message

  Scenario: Check admin able to logout  with invalid method
    Given User creates POST request as admin
    When User send POST HTTP request with endpoint for logout
    Then User recieves 405 method not allowed

  Scenario: Check dietician able to logout  with invalid method
    Given User creates POST request as dietician
    When User send POST HTTP request with endpoint for logout
    Then User recieves 405 method not allowed

  Scenario: Check patient able to logout  with invalid method
    Given User creates POST request as patient
    When User send POST HTTP request with endpoint for logout
    Then User recieves 405 method not allowed
