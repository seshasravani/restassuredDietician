
Feature: Dietician Get By ID

Background: Set admin token
  

 
 Scenario: Set admin token - Check admin able to retrieve dietician by ID
    
    Given Admin create GET request
    When Admin send GET http request with endpoint
   
    Then Admin receives 200 ok with details of the dietician id
    
    
    #Scenario: Set no auth - Check admin able to retrieve dietician by ID
#    Given Set no auth
 #   When Admin create GET request
 #   And Admin send GET http request with endpoint
 #   Then Admin receives 401 unauthorized
 