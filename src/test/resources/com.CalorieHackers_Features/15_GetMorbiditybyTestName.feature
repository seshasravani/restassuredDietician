Feature: Get Morbidity By Name - Access and Method Validation


  @GetMorbidityByName
  Scenario: Set no auth	Check dietician able to morbidity condition by test name 
    Given Dietician create GET request to retrieve morbidityby name
    When Dietician send GET http request with endpoint  to retrieve morbididty by name
    Then Dietician recieve unauthorized

  Scenario: Patient forbidden to retrieve morbidity by name
    Given Patient create GET request to retrieve morbididty by name
    When Patient send GET http request with endpoint  to retrieve morbididty by name
    Then Patient recieve Forbidden

  Scenario: Admin successful GET morbidity by name
    Given admin create GET request to retrieve morbidity byname
    When admin send GET http request with endpoint  to retrieve morbididty by name
    Then admin recieve ok with details of the patient id

  Scenario: Admin uses POST instead of GET on morbidity by name
    Given admin create GET request to retrieve morbidity byname
    When admin send POST http request with endpoint  to retrieve morbididty by name
    Then admin recieve method not allowed

  Scenario: Admin uses GET on unknown morbidity by name
    Given admin create GET request to retrieve morbidity byname
    When admin send GET http request with endpoint  to retrieve morbididty by name
    Then admin recieve  not found

  Scenario: Admin uses invalid endpoint to retrieve morbidity by name
    Given admin create GET request to retrieve morbidity byname
    When admin send GET http request with invalid endpoint  to retrieve morbididty by name
    Then admin recieve  not found

  Scenario: Dietician successful GET morbidity by name
    Given Dietician create GET request to retrieve morbidityby name
    When Dietician send GET http request with endpoint  to retrieve morbididty by name
    Then Dietician recieve  ok with details of the patient id

  Scenario: Dietician uses POST instead of GET on morbidity by name
    Given Dietician create GET request to retrieve morbidityby name
    When Dietician send POST http request with endpoint  to retrieve morbididty by name
    Then Dietician recieve method not allowed

  Scenario: Dietician uses GET on unknown morbidity by name
    Given Dietician create GET request to retrieve morbidityby name
    When Dietician send GET http request with endpoint  to retrieve morbididty by name
    Then Dietician recieve  not found

  Scenario: Dietician uses invalid endpoint to retrieve morbidity by name
    Given Dietician create GET request to retrieve morbidityby name
    When Dietician send GET http request with invalid endpoint  to retrieve morbididty by name
    Then Dietician recieve  not found