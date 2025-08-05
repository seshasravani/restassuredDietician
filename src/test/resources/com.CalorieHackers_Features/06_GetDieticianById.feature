Feature: Dietician Get By ID

  Background: Set admin token
    Given Admin has a valid auth token

  Scenario: Set admin token - Check admin able to retrieve dietician by ID
    Given Admin create GET request
    When Admin send GET http request with endpoint
    Then Admin receives 200 ok with details of the dietician id

  Scenario: Set no auth - Check admin able to retrieve dietician by ID
    Given No Authentication is set
    When Admin create GET request
    And Admin send GET http request with endpoint
    Then Admin receives 401 unauthorized

  Scenario: Check admin able to retrieve dietician by id with invalid method
    Given Admin creates POST request
    When Admin sends POST http request with endpoint for dietician
    Then Admin receives 405 method not allowed

  Scenario: Check admin able to retrieve dietician by invalid id
    Given Admin create GET request for invalid Id
    When Admin send GET http request with invalidId endpoint
    Then Admin receives 404 not found

  Scenario: Check admin able to retrieve dietician by id with invalid endpoint
    Given Admin create GET request for invalid endpoint
    When Admin send GET http request with invalid endpoint
    Then Admin recieves 404 not found
