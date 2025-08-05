@getAllDieticianAsDietician
Feature: GET (All dietician)

  Background: Set dietician token

  Scenario: Check dietician able to retrieve all dietician
    Given Dietician create GET request to retrieve all dietician
    When Dietician send GET http request with endpoint to retrieve all dietician
    Then Dietician recieves 200 ok with response body to retrieve all dietician

  Scenario: Check dietician able to retrieve all dietician with invalid method
    Given Dietician create PUT request to retrieve all dietician
    When Dietician send PUT http request with endpoint to retrieve all dietician
    Then Dietician recieves 405 method not allowed to retrieve all dietician

  Scenario: Check dietician able to retrieve all dietician with invalid endpoint
    Given Dietician create GET request to retrieve all dietician with invalid endpoint
    When Dietician send GET http request with invalid endpoint to retrieve all dietician
    Then Dietician recieves 404 not found to retrieve all dietician
