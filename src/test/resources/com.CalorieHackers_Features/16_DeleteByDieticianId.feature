Feature: DELETE ( by dietician)

  Background: 
    Given Admin bearer token is set, and the admin successfully created a dietician account

  Scenario: Check admin able to delete dietician by ID
    Given Admin creates DELETE request for delete Dietician
    When Admin send DELETE http request with endpoint for delete dietician
    Then Admin receives 200 ok dietician is deleted

  Scenario: Check admin able to delete dietician by invalid id
    Given Admin creates DELETE request for delete Dietician
    When Admin send DELETE http request with endpoint for delete dietician with invalid id
    Then Admin receives 404 invalid dietician id

  Scenario: Check admin able to delete dietician by id with invalid endpoint
    Given Admin creates DELETE request for delete Dietician
    When Admin send DELETE http request with invalid endpoint for delete dietician
    Then Admin recieves 404 invalid dietician url
