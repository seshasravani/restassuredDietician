@GetPatientMorbidity
Feature: Get Patients Morbidity Details

  Scenario: Check dietician with no auth able to retrieve patients morbidity details by patient ID
    Given Dietician with "no auth create GET morbidity" request to retrieve patients morbidity details
    When Dietician send GET http request to retrieve patients morbidity details with endpoint
    Then Dietician recieves 401 unauthorized to retrieve patients morbidity details

  Scenario: Check admin is able to retrieve patients morbidity details by patient ID
    Given Admin create "GET morbidity request with admin token" to retrieve patients morbidity details
    When Admin send GET http request to retrieve patients morbidity details with endpoint
    Then Admin recieves 403 Forbidden to retrieve patients morbidity details

  Scenario: Check patient is able to retrieve patients morbidity details by patient ID
    Given Patient create valid "GET morbidity request to retrieve patients" morbidity details
    When Patient send GET http request to retrieve patients morbidity details with endpoint
    Then Patient recieves 200 ok with details of the patient id

  Scenario: Check dietician able to retrieve patients morbidity details by patient ID
    Given Dietician create valid "GET morbidity request to retrieve patients" morbidity details
    When Dietician send GET http request to retrieve patients morbidity details with endpoint
    Then Dietician recieves 200 ok with details of the patient id

  Scenario: Check dietician able to retrieve patients morbidity details by patient ID with invalid method
    Given Dietician create "POST morbidity request" to retrieve patients morbidity details
    When Dietician send POST http request to retrieve patients morbidity details with endpoint
    Then Dietician recieves 405 method not allowed to retrieve patients morbidity details

  Scenario: Check dietician able to retrieve patients morbidity details by invalid patient ID
    Given Dietician create GET morbidity request by "invalid patient id to retrieve patients morbidity" details
    When Dietician send GET http request to retrieve invalid patients morbidity details with endpoint
    Then Dietician recieves 404 not found to retrieve patients morbidity details

  Scenario: Check dietician able to retrieve patients morbidity details by patient ID with invalid endpoint
    Given Dietician create invalid "GET morbidity request with invalid endpoint" to retrieve patients morbidity details
    When Dietician send  GET http request to retrieve patients morbidity details with invalid endpoint
    Then Dietician recieves 404 not found to retrieve patients morbidity details
