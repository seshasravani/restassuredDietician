@DeletePatient
Feature: Delete Patient file by patient id

  Scenario: Check dietician able to delete patient by ID with no auth
    Given Dietician with no auth create DELETE patient request to "Delete patient by ID with no auth"
    When Dietician send DELETE http request to delete patient by ID with endpoint
    Then Dietician recieves "401 unauthorized" to delete patient by ID

  Scenario: Check admin is able to delete patients details by patient ID
    Given Admin create DELETE patient request to "Delete patient by ID with admin token"
    When Admin send GET http request to delete patient by ID with endpoint
    Then Admin recieves "403 Forbidden" to delete patient by ID

  Scenario: Check patient is able to delete patients details by patient ID
    Given Patient create DELETE patient request to "Delete patient by ID with patient token"
    When Patient send GET http request to delete patient by ID with endpoint
    Then Patient recieves "403 Forbidden" to delete patient by ID

  Scenario: Check dietician able to delete patient by ID
    Given Dietician create DELETE patient request to "Delete patient by ID"
    When Dietician send DELETE http request to delete patient by ID with endpoint
    Then Dietician recieves 200 ok to delete patient by ID

  Scenario: Check dietician able to delete patient by id with invalid method
    Given Dietician create POST patient request to "Delete patient by ID with invalid method"
    When Dietician send POST http request to delete patient by ID with endpoint
    Then Dietician recieves "405 method not allowed" to delete patient by ID

  Scenario: Check dietician able to delete patient by invalid id
    Given Dietician create DELETE patient request to "Delete patient by invalid ID"
    When Dietician send DELETE http request to delete patient by invalid ID with endpoint
    Then Dietician recieves "404 not found" to delete patient by ID

  Scenario: Check dietician able to delete patient by id with invalid endpoint
    Given Dietician create DELETE patient request to "Delete patient by ID with invalid endpoint"
    When Dietician send DELETE http request to delete patient by ID with invalid endpoint
    Then Dietician recieves "404 not found" to delete patient by ID
