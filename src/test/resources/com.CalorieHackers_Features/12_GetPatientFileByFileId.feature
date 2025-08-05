@GetPatientFileByFileID
Feature: Retrieve Patient file by FileId

  Scenario: Check dietician wit no auth able to retrieve patients by file id
    Given Dietician with no auth create GET patient file request to "Retrieve patients by file id with no auth"
    When Dietician send GET http request to retrieve patients by file id with endpoint
    Then Dietician recieves "401 unauthorized" to retrieve patients by file id

  Scenario: Check admin is able to retrieve patients morbidity details by file id
    Given Admin create GET patient file request to "Retrieve patients by file id with admin token"
    When Admin send GET http request to retrieve patients by file id with endpoint
    Then Admin recieves "403 Forbidden" to retrieve patients by file id

  Scenario: Check patient is able to retrieve patients morbidity details by file id
    Given Patient create GET patient file request to "Retrieve patients by file id"
    When Patient send GET http request to retrieve patients by file id with endpoint
    Then Patient recieves "200 ok" with details of the patient id by file id

  Scenario: Check dietician able to retrieve patients by file id
    Given Dietician create GET patient file request to "Retrieve patients by file id"
    When Dietician send GET http request to retrieve patients by file id with endpoint
    Then Dietician recieves "200 ok" with details of the patient id by file id

  Scenario: Check dietician able to retrieve patients by file id with invalid method
    Given Dietician create POST patient file request to "Retrieve patients by file id with invalid method"
    When Dietician send POST http request to retrieve patients by file id with endpoint
    Then Dietician recieves "405 method not allowed" to retrieve patients by file id

  Scenario: Check dietician able to retrieve patients by invalid file id
    Given Dietician create GET patient file request to "Retrieve patients by invalid file id"
    When Dietician send GET http request to retrieve patients by invalid file id with endpoint
    Then Dietician recieves "404 not found" to retrieve patients by file id

  Scenario: Check dietician able to retrieve patients by file id with invalid endpoint
    Given Dietician create GET patient file request to "Retrieve patients by file id with invalid endpoint"
    When Dietician send GET http request to retrieve patients by file id with invalid endpoint
    Then Dietician recieves "404 not found" to retrieve patients by file id
