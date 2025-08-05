Feature: POST Operation - Create Patient with patient token

  Scenario: Set patient bearer token Check patient able to create patient with valid data and patient token
    Given Patient creates POST request by entering valid data into the form-data key and value fields
    When Patient sends POST http request with endpoint
    Then Patient receives forbidden
