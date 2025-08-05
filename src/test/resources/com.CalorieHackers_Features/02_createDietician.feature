@CreateDietician
Feature: POST (create dietician )

  Background: Set admin bearer token

  Scenario: Check admin able to create dietician with valid data and token
    Given Admin creates POST request with valid data. ( Mandatory and additional details)
    When Admin send POST http request with endpoint

    Then Admin recieves 201 created and with response body. (Auto created dietician ID and login password)

  Scenario: Check admin able to create dietician only with valid mandatory details
    Given Admin creates POST request only with valid mandatory details
    When Admin send POST http request with endpoint
    Then Admin recieves 201 created and with response body. (Auto created dietician ID and login password)

  Scenario: Check admin able to create dietician with all fields empty
    Given Admin creates POST request only with all fields empty
    When Admin send POST http request with endpoint
    Then Admin recieves 400 Bad request

  Scenario: Check admin able to create dietician with number and special character in FirstName
    Given Admin creates POST request only with number and special character in firstname
    When Admin send POST http request with endpoint
    Then Admin recieves 400 Bad request

  Scenario: Check admin able to create dietician with number and special character in LastName
    Given Admin creates POST request only with number and special character in lastname
    When Admin send POST http request with endpoint
    Then Admin recieves 400 Bad request

