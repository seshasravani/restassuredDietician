@getAllDieticianAsAdmin
Feature: GET (All dietician)

  Background: Set admin token

  Scenario: Check admin able to retrieve all dietician
    Given Admin create GET request to retrieve all dietician
    When Admin send GET http request with endpoint to retrieve all dietician
    Then Admin recieves 200 ok with response body to retrieve all dietician

  Scenario: Check admin able to retrieve all dietician with invalid method
    Given Admin create PUT request to retrieve all dietician
    When Admin send PUT http request with endpoint to retrieve all dietician
    Then Admin recieves 405 method not allowedto retrieve all dietician

  Scenario: Check admin able to retrieve all dietician with invalid endpoint
    Given Admin create GET request to retrieve all dietician with invalid endpoint
    When Admin send GET http request with invalid endpoint to retrieve all dietician
    Then Admin recieves 404 not found to retrieve all dietician
