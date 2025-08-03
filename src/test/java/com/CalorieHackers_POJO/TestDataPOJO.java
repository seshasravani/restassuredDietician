package com.CalorieHackers_POJO;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestDataPOJO {

    private String scenarioName;
    private String testCaseId;
    private String scenarioType;
    private String endpoint;
    private String method;
    private String contentType;
    private String userLoginEmail;
    private String password;
    private String authType;
    private int expectedStatusCode;
    private String expectedStatusLine;
    private String expectedContentType;

    private String reportFilePath;
    private String expecterErrorMsg;

    // Keep this as Map if you want, otherwise remove if unused
    private java.util.Map<String, Object> vitals;

    // Now typed patientinfo as PatientInfo POJO
    private PatientInfo patientinfo;

    private int invaidpatientid;
    private String adminToken;
    private String dieticianToken;
    private String patientToken;
    private String firstname;
    private String lastname;
    private String contactNumber;
    private String dateOfBirth;
    private String email;
    private String hospitalName;
    private String hospitalStreet;
    private String hospitalCity;
    private String hospitalPincode;
    private String education;

    // Getters and setters for all fields including patientinfo

    public PatientInfo getPatientinfo() {
        return patientinfo;
    }

    public void setPatientinfo(PatientInfo patientinfo) {
        this.patientinfo = patientinfo;
    }

    // Other existing getters and setters...

    // Inner static class for PatientInfo
    public static class PatientInfo {

        @JsonProperty("FirstName")
        private String firstName;

        @JsonProperty("LastName")
        private String lastName;

        @JsonProperty("ContactNumber")
        private String contactNumber;

        @JsonProperty("Email")
        private String email;

        @JsonProperty("Allergy")
        private String allergy;

        @JsonProperty("FoodPreference")
        private String foodPreference;

        @JsonProperty("CuisineCategory")
        private String cuisineCategory;

        @JsonProperty("DateOfBirth")
        private String dateOfBirth;

        // Getters and Setters

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAllergy() {
            return allergy;
        }

        public void setAllergy(String allergy) {
            this.allergy = allergy;
        }

        public String getFoodPreference() {
            return foodPreference;
        }

        public void setFoodPreference(String foodPreference) {
            this.foodPreference = foodPreference;
        }

        public String getCuisineCategory() {
            return cuisineCategory;
        }

        public void setCuisineCategory(String cuisineCategory) {
            this.cuisineCategory = cuisineCategory;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }
    }

//	    // Getters and Setters
//
//	    public String getClientFirstName() {
//	        return clientFirstName;
//	    }
//
//	    public void setClientFirstName(String clientFirstName) {
//	        this.clientFirstName = clientFirstName;
//	    }
//
//	    public String getClientLastName() {
//	        return clientLastName;
//	    }
//
//	    public void setClientLastName(String clientLastName) {
//	        this.clientLastName = clientLastName;
//	    }
//
//	    public String getClientContactNumber() {
//	        return clientContactNumber;
//	    }
//
//	    public void setClientContactNumber(String clientContactNumber) {
//	        this.clientContactNumber = clientContactNumber;
//	    }
//
//	    public String getClientEmail() {
//	        return clientEmail;
//	    }
//
//	    public void setClientEmail(String clientEmail) {
//	        this.clientEmail = clientEmail;
//	    }
//
//	    public String getClientAllergy() {
//	        return clientAllergy;
//	    }
//
//	    public void setClientAllergy(String clientAllergy) {
//	        this.clientAllergy = clientAllergy;
//	    }
//
//	    public String getClientFoodPreference() {
//	        return clientFoodPreference;
//	    }
//
//	    public void setClientFoodPreference(String clientFoodPreference) {
//	        this.clientFoodPreference = clientFoodPreference;
//	    }
//
//	    public String getClientCuisineCategory() {
//	        return clientCuisineCategory;
//	    }
//
//	    public void setClientCuisineCategory(String clientCuisineCategory) {
//	        this.clientCuisineCategory = clientCuisineCategory;
//	    }
//
//	    public String getClientDateOfBirth() {
//	        return clientDateOfBirth;
//	    }
//
//	    public void setClientDateOfBirth(String clientDateOfBirth) {
//	    	this.clientDateOfBirth = clientDateOfBirth;
//	    }
//
//	   

	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public String getScenarioType() {
		return scenarioType;
	}

	public void setScenarioType(String scenarioType) {
		this.scenarioType = scenarioType;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getUserLoginEmail() {
		return userLoginEmail;
	}

	public void setUserLoginEmail(String userLoginEmail) {
		this.userLoginEmail = userLoginEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public int getExpectedStatusCode() {
		return expectedStatusCode;
	}

	public void setExpectedStatusCode(int expectedStatusCode) {
		this.expectedStatusCode = expectedStatusCode;
	}

	public String getExpectedStatusLine() {
		return expectedStatusLine;
	}

	public void setExpectedStatusLine(String expectedStatusLine) {
		this.expectedStatusLine = expectedStatusLine;
	}

	public String getExpectedContentType() {
		return expectedContentType;
	}

	public void setExpectedContentType(String expectedContentType) {
		this.expectedContentType = expectedContentType;
	}

	public String getReportFilePath() {
		return reportFilePath;
	}

	public void setReportFilePath(String reportFilePath) {
		this.reportFilePath = reportFilePath;
	}

	public String getExpecterErrorMsg() {
		return expecterErrorMsg;
	}

	public void setExpecterErrorMsg(String expecterErrorMsg) {
		this.expecterErrorMsg = expecterErrorMsg;
	}


	public Map<String, Object> getVitals() {
		return vitals;
	}

	public void setVitals(Map<String, Object> vitals) {
		this.vitals = vitals;
	}

	

//	public Map<String, Object> getPatientinfo() {
//		return patientinfo;
//	}
//
//	public void setPatientinfo(Map<String, Object> patientinfo) {
//		this.patientinfo = patientinfo;
//	}

	public int getInvaidpatientid() {
		return invaidpatientid;
	}

	public void setInvaidpatientid(int invaidpatientid) {
		this.invaidpatientid = invaidpatientid;
	}


	public String getAdminToken() {
		return adminToken;
	}

	public void setAdminToken(String adminToken) {
		this.adminToken = adminToken;
	}

	public String getDieticianToken() {
		return dieticianToken;
	}

	public void setDieticianToken(String dieticianToken) {
		this.dieticianToken = dieticianToken;
	}

	public String getPatientToken() {
		return patientToken;
	}

	public void setPatientToken(String patientToken) {
		this.patientToken = patientToken;
	}
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalStreet() {
		return hospitalStreet;
	}

	public void setHospitalStreet(String hospitalStreet) {
		this.hospitalStreet = hospitalStreet;
	}

	public String getHospitalCity() {
		return hospitalCity;
	}

	public void setHospitalCity(String hospitalCity) {
		this.hospitalCity = hospitalCity;
	}

	public String getHospitalPincode() {
		return hospitalPincode;
	}

	public void setHospitalPincode(String hospitalPincode) {
		this.hospitalPincode = hospitalPincode;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}


}
