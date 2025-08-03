#@AddNewReport
#Feature: Add New Reports with/without Vitals for existing Patient
#
#
#Scenario: Check dietician with no auth is able to add new reports for existing patient with valid data
    #Given Dietician with no auth creates PUT request by "Entering valid report and valid patient ID"
    #When User send PUT http request to add new report
    #Then Dietician recieves 401 unauthorized
  #
#Scenario: Check admin able to add new reports for existing patient with valid data and admin token
    #Given Admin creates PUT request to add new report by "Entering valid data into the form-data key and value fields and valid patient ID token"
    #When User send PUT http request to add new report
    #Then Admin recieves 403 forbidden
     #
#Scenario: Check patient able to add new reports for existing patient with valid data and patient token
    #Given Patient creates PUT request to add new report by "Entering valid data into the form-data key and value fields and valid patient ID token"
    #When User send PUT http request to add new report
    #Then Patient recieves 403 forbidden
     #
#Scenario: Check dietician able to add new reports with vitals for existing patient with valid data
    #Given Dietician creates PUT request to add new report by entering "Valid data Mandatory and additional details into the form-data key and value fields and valid patient ID"
    #When User send PUT http request to add new report
    #Then Dietician recieves 200 ok and with updated response body
   #
#Scenario: Check dietician able to add new reports without vitals for existing patient with valid data
    #Given Dietician creates PUT request to add new report by entering valid data into the form-data key and value fields "Except valid vitals data and valid patient ID"
    #When User send PUT http request to add new report
    #Then Dietician recieves 200 ok and with updated response body
    #
#Scenario: Check dietician able to add only new vitals without reports for existing patient with report
    #Given Dietician creates PUT request to add new report by entering valid "Data into the form-data key and value fields except file and valid patient ID"
    #When User send PUT http request to add new report
    #Then Dietician recieves 200 ok and with updated response body
    #
#Scenario: Check dietician able to add only new vitals without reports for existing patient without report
    #Given Dietician creates PUT request by entering valid data into the form-data key and value fields except file and valid patient ID
    #When User send PUT http request to add new report
    #Then Dietician recieves 200 ok and with updated response body
    #
#Scenario: Check dietician able to add new reports for existing patient only with valid mandatory details 
    #Given Dietician creates PUT request by entering valid Mandatory data into the form-data key and value fields and valid patient ID
    #When User send PUT http request to add new report
    #Then Dietician recieves 200 ok and with updated response body
    #
#Scenario: Check dietician able to add new reports for existing patient only with valid additional details
    #Given Dietician creates PUT request by entering valid Additional details data into the form-data key and value fields and valid patient ID
    #When User send PUT http request to add new report
    #Then Dietician recieves 200 ok and with updated response body
    #
#Scenario: Check dietician able to add new reports  for existing patient with invalid data
    #Given Dietician creates PUT request by entering valid patient ID and "Invalid Additional details data into the form-data key and value fields"
    #When User send PUT http request to add new report
    #Then Dietician recieves 400 Bad request
    #
#Scenario: Check dietician able to add new reports for existing patient with valid data and invalid patient id as path parameter
    #Given Dietician creates PUT request by entering "Invalid Additional details data into the form-data key and value fields and invalid patient ID"
    #When User send PUT http request to add new report with invalid patient id
    #Then Dietician recieves 404 not found
    #
#Scenario: Check dietician able to add new reports for existing patient  with valid data and invalid method
    #Given Dietician creates POST request by "Entering valid data into the form-data key and value fields and valid patient ID"
    #When Dietician send POST http request with endpoint to add new report
    #Then Dietician recieves 405 method not allowed
    #
#Scenario: Check dietician able to add new reports for existing patient with valid data and invalid endpoint
    #Given Dietician creates PUT request with invalid endpoint to add new report by entering "Valid data and with invalid reports endpoint"
    #When Dietician sent PUT http request with invalid endpoint
    #Then Dietician recieves 404 not found
    #
#Scenario: Check dietician able to add new reports for existing patient  with valid data and invalid content type
    #Given Dietician creates PUT request by entering valid data into the form-data key and value fields and "Valid patient ID with invalid content type"
    #When User send PUT http request to add new report
    #Then Dietician recieves 415 unsupported media type