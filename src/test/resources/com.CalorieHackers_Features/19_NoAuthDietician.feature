Feature: Dietician Get By ID No Auth

  Background: Set no auth
    Given No Authentication is set

  Scenario: Check admin able to retrieve dietician by ID
    Given Admin create GET request
    When Admin sends GET http request without auth
    Then Admin recieves 401 unauthorized
