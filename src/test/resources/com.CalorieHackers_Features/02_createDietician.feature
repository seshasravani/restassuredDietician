
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
