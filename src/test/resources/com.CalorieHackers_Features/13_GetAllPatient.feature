Feature: Get All Patient

<<<<<<< HEAD


=======
>>>>>>> 3a248d4 (added all files)
  Scenario: Set no auth Check dietician able to retrieve all patients
    Given Dietician creates GET request without Authorization header
    When Dietician sends GET HTTP request with valid endpoint
    Then the Dietician receives Unauthorized

  Scenario: Set admin token Check admin able to retrieve all patients
    Given Admin creates GET request with Authorization token
    When Admin sends GET HTTP request with valid endpoint
    Then the Admin receives Forbidden

  Scenario: Set patient token Check patient able to retrieve all patients
    Given Patient creates GET request with Authorization token
    When Patient sends GET HTTP request with valid endpoint
    Then the Patient receives Forbidden

  Scenario: Set dietician token Check dietician able to retrieve all patients
    Given Dietician creates GET request with Authorization token
    When Dietician sends GET HTTP request with valid endpoint
    Then the Dietician receives OK with response body

  Scenario: Check dietician able to retrieve all patient with invalid method PUT
    Given Dietician creates PUT request with Authorization token
    When Dietician sends PUT HTTP request with valid endpoint
    Then the Dietician receives Method Not Allowed

  Scenario: Check dietician able to retrieve all patient with invalid endpoint
    Given Dietician creates GET request with Authorization token
    When Dietician sends GET HTTP request with invalid endpoint
    Then the Dietician receives Not Found
