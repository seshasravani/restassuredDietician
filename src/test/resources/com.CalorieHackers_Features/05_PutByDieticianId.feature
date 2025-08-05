#Author: Sravani
Feature: Put (by dietician)

  Background: 
    Given Admin bearer token was set, and the admin successfully created a dietician account

  Scenario: Check admin able to update dietician with valid data , dietician id and token
    Given Admin creates PUT request with valid data
    When Admin send PUT http request with endpoint
    Then Admin recieves 200 ok and with updated response body
