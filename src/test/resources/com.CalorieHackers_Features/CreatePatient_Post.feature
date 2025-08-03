#Feature: POST Operation - Create Patient
#
#Background: admin and dietician authentication flow
    #Given Admin logs in with valid credentials
    #When Admin creates a new dietician with valid details
    #Then Admin receives dietician credentials in the response
    #
    #Given Dietician has email and password from previous step
    #When Dietician send Post request with email and password 
    #Then daitician recieved dietician token in the response creation is successful with valid response
#
  #Scenario: Set no auth Check dietician able to create patient with valid data
    #Given Dietician creates POST request by entering valid data into the form-data key and value fields
    #When Dietician sends POST http request with endpoint
    #Then Dietician receives unauthorized
#
  #Scenario: Set admin bearer token Check admin able to create patient with valid data and admin token
    #Given Admin creates POST request by entering valid data into the form-data key and value fields
    #When Admin sends POST http request with endpoint
    #Then Admin receives forbidden
#
  #Scenario: Set patient bearer token Check patient able to create patient with valid data and patient token
    #Given Patient creates POST request by entering valid data into the form-data key and value fields
    #When Patient sends POST http request with endpoint
    #Then Patient receives forbidden
#
  #Scenario: Set dietician bearer token Check dietician able to create patient with valid data and token
    #Given Dietician creates POST request by entering valid mandatory and additional data into the form-data fields
    #When Dietician sends POST http request with endpoint
    #Then Dietician receives success message with response body containing generated ID and password
#
  #Scenario: Check dietician able to create patient only with valid mandatory details
    #Given Dietician creates POST request with only valid mandatory details in form-data
    #When Dietician sends POST http request with endpoint
    #Then Dietician receives success message with response body containing generated ID and password
#
  #Scenario: Check dietician able to create patient only with valid additional details
    #Given Dietician creates POST request with only valid additional details in form-data
    #When Dietician sends POST http request with endpoint
    #Then Dietician receives Bad request
#
  #Scenario: Check dietician able to create patient with invalid mandatory data
    #Given Dietician creates POST request with invalid mandatory details in form-data
    #When Dietician sends POST http request with endpoint
    #Then Dietician receives Bad request
#
  #Scenario: Check dietician able to create patient with valid mandatory fields and invalid additional data
    #Given Dietician creates POST request with valid mandatory and invalid additional details in form-data
    #When Dietician sends POST http request with endpoint
    #Then Dietician receives Bad request
#
  #Scenario: Check dietician able to create patient with valid data and invalid method
    #Given Dietician creates PUT request with valid data in form-data
    #When Dietician sends PUT http request with endpoint
    #Then Dietician receives method not allowed
#
  #Scenario: Check dietician able to create patient with valid data and invalid endpoint
    #Given Dietician creates POST request with valid data in form-data
    #When Dietician sends POST http request with invalid endpoint
    #Then Dietician receives not found
#
  #Scenario: Check dietician able to create patient with valid data and invalid content type
    #Given Dietician creates POST request with valid data and invalid content type in form-data
    #When Dietician sends POST http request with endpoint
    #Then Dietician receives unsupported media type
#
#
                 #|
#
  #@GetAllMorbidities
  #Scenario Outline: Get all morbidities scenarios by scenario name
    #When I send a "<scenarioname>" GET request for all morbidities
    #Then the response status code should be <statusCode>
    #And the response status text should be "<statusText>"
#
    #Examples:
      #| scenarioname                                               | statusCode | statusText           |
      #| Set no auth - Check dietician able to retrieve all morbidities              | 401        | Unauthorized         |
      #| Set Patient Token - Check patient unable to retrieve all morbidities        | 403        | Forbidden            |
      #| Set admin token - Check admin able to retrieve all morbidities              | 200        | OK                   |
      #| Check admin able to retrieve all morbidities with invalid method (POST)     | 405        | Method Not Allowed    |
      #| Check admin able to retrieve all morbidities with invalid endpoint          | 404        | Not Found            |
      #| Set dietician token - Check dietician able to retrieve all morbidities      | 200        | OK                   |
      #| Check dietician able to retrieve all morbidities with invalid method (POST) | 405        | Method Not Allowed    |
      #| Check dietician able to retrieve all morbidities with invalid endpoint      | 404        | Not Found            |
#
  #@GetMorbidityByTestName
  #Scenario Outline: Get morbidity condition by test name scenarios by scenario name
    #When I send a "<scenarioname>" GET request for morbidity condition by test name
    #Then the response status code should be <statusCode>
    #And the response status text should be "<statusText>"
#
    #Examples:
      #| scenarioname                                                     | statusCode | statusText           |
      #| Set no auth - Check dietician unable to retrieve morbidity condition by test name | 401  | Unauthorized         |
      #| Set Patient Token - Check patient unable to retrieve morbidity condition by test name | 403 | Forbidden           |
      #| Set admin token - Check admin able to retrieve morbidity condition by test name      | 200  | OK                   |
      #| Check admin able to retrieve morbidity condition by test name with invalid method (POST) | 405 | Method Not Allowed  |
      #| Check admin able to retrieve morbidity condition by invalid test name                 | 404  | Not Found            |
      #| Check admin able to retrieve morbidity condition by test name with invalid endpoint   | 404  | Not Found            |
      #| Set dietician token - Check dietician able to retrieve morbidity condition by test name | 200  | OK                   |
      #| Check dietician able to retrieve morbidity condition by test name with invalid method (POST) | 405 | Method Not Allowed  |
      #| Check dietician able to retrieve morbidity condition by invalid test name             | 404  | Not Found            |
      #| Check dietician able to retrieve morbidity condition by test name with invalid endpoint | 404  | Not Found            |
      