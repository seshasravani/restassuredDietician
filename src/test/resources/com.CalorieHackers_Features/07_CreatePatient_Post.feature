Feature: POST Operation - Create Patient

  Scenario: Set no auth Check dietician able to create patient with valid data
    Given Dietician creates POST request by entering valid data into the form-data key and value fields
    When Dietician sends POST http request with endpoint
    Then Dietician receives unauthorized

  Scenario: Set admin bearer token Check admin able to create patient with valid data and admin token
    Given Admin creates POST request by entering valid data into the form-data key and value fields
    When Admin sends POST http request with endpoint
    Then Admin receives forbidden

  Scenario: Set dietician bearer token Check dietician able to create patient with valid data and token
    Given Dietician creates POST request by entering valid mandatory and additional data into the form-data fields
    When Dietician sends POST http request with endpoint
    Then Dietician receives success message with response body containing generated ID and password

  Scenario: Check dietician able to create patient only with valid mandatory details
    Given Dietician creates POST request with only valid mandatory details in form-data
    When Dietician sends POST http request with endpoint
    Then Dietician receives success message with response body containing generated ID and password

  Scenario: Check dietician able to create patient only with valid additional details
    Given Dietician creates POST request with only valid additional details in form-data
    When Dietician sends POST http request with endpoint
    Then Dietician receives Bad request

  Scenario: Check dietician able to create patient with invalid mandatory data
    Given Dietician creates POST request with invalid mandatory details in form-data
    When Dietician sends POST http request with endpoint
    Then Dietician receives Bad request

  Scenario: Check dietician able to create patient with valid mandatory fields and invalid additional data
    Given Dietician creates POST request with valid mandatory and invalid additional details in form-data
    When Dietician sends POST http request with endpoint
    Then Dietician receives Bad request

  Scenario: Check dietician able to create patient with valid data and invalid method
    Given Dietician creates PUT request with valid data in form-data
    When Dietician sends PUT http request with endpoint
    Then Dietician receives method not allowed

  Scenario: Check dietician able to create patient with valid data and invalid endpoint
    Given Dietician creates POST request with valid data in form-data
    When Dietician sends POST http request with invalid endpoint
    Then Dietician receives not found

  Scenario: Check dietician able to create patient with valid data and invalid content type
    Given Dietician creates POST request with valid data and invalid content type in form-data
    When Dietician sends POST http request with endpoint
    Then Dietician receives unsupported media type
