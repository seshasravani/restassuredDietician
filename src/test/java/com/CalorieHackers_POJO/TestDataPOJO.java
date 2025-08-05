package com.CalorieHackers_POJO;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
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
	private Map<String, Object> vitals;
	private Map<String, Object> patientUpdateInfo;
	private int invaidpatientid;
	private String invaidfileid;
	private String adminToken;
	private String dieticianToken;
	private String patientToken;
	private String Firstname;
	private String Lastname;
	private String ContactNumber;
	private String DateOfBirth;
	private String Email;
	private String HospitalName;
	private String HospitalStreet;
	private String HospitalCity;
	private String HospitalPincode;
	private String Education;
	// private String Allergy;
	// private String FoodPreference;
	// private String CuisineCategory;
	private int dieticianID;
	private String dieticianLoginPassword;
	private String dieticianEmail;
	private String expectedStatusMessage;
	private int invalidDieticianId;
	private int validDieticianId;
	private String loginPassword;
	private String invalidUrl;

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

	public String getContactNumber() {
		return ContactNumber;
	}

	public void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getExpecterErrorMsg() {
		return expecterErrorMsg;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public int getValidDieticianId() {
		return validDieticianId;
	}

	public void setValidDieticianId(int validDieticianId) {
		this.validDieticianId = validDieticianId;
	}
	// Other existing getters and setters...
	// Inner static class for PatientInfo

	public PatientInfo getPatientinfo() {
		return patientinfo;
	}

	public void setPatientinfo(PatientInfo patientinfo) {
		this.patientinfo = patientinfo;
	}

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

	public String getScenarioName() {
		return scenarioName;
	}

	// Keep this as Map if you want, otherwise remove if unused
//    private java.util.Map<String, Object> vitals;
	// Now typed patientinfo as PatientInfo POJO
	private PatientInfo patientinfo;
//
//	// getters and setters for all fields including patientinfo
//

	public void setExpecterErrorMsg(String expecterErrorMsg) {
		this.expecterErrorMsg = expecterErrorMsg;
	}

	public Map<String, Object> getVitals() {
		return vitals;
	}

	public String getInvaidfileid() {
		return invaidfileid;
	}

	public void setInvaidfileid(String invaidfileid) {
		this.invaidfileid = invaidfileid;
	}

	public void setVitals(Map<String, Object> vitals) {
		this.vitals = vitals;
	}

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

	public int getDieticianID() {
		return dieticianID;
	}

	public void setDieticianID(int dieticianID) {
		this.dieticianID = dieticianID;
	}

	public String getDieticianLoginPassword() {
		return dieticianLoginPassword;
	}

	public void setDieticianLoginPassword(String dieticianLoginPassword) {
		this.dieticianLoginPassword = dieticianLoginPassword;
	}

	public String getDieticianEmail() {
		return dieticianEmail;
	}

	public void setDieticianEmail(String dieticianEmail) {
		this.dieticianEmail = dieticianEmail;
	}

	public int getInvalidDieticianId() {
		return invalidDieticianId;
	}

	public void setInvalidDieticianId(int invalidDieticianId) {
		this.invalidDieticianId = invalidDieticianId;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public String getHospitalName() {
		return HospitalName;
	}

	public void setHospitalName(String hospitalName) {
		HospitalName = hospitalName;
	}

	public String getHospitalStreet() {
		return HospitalStreet;
	}

	public void setHospitalStreet(String hospitalStreet) {
		HospitalStreet = hospitalStreet;
	}

	public String getHospitalCity() {
		return HospitalCity;
	}

	public void setHospitalCity(String hospitalCity) {
		HospitalCity = hospitalCity;
	}

	public String getHospitalPincode() {
		return HospitalPincode;
	}

	public void setHospitalPincode(String hospitalPincode) {
		HospitalPincode = hospitalPincode;
	}

	public String getEducation() {
		return Education;
	}

	public void setEducation(String education) {
		Education = education;
	}

	// Keep this as Map if you want, otherwise remove if unused
//    private java.util.Map<String, Object> vitals;
	// Now typed patientinfo as PatientInfo POJO
	// private PatientInfo patientinfo;
	public Map<String, Object> getPatientUpdateInfo() {
		return patientUpdateInfo;
	}

	public void setPatientUpdateInfo(Map<String, Object> patientUpdateInfo) {
		this.patientUpdateInfo = patientUpdateInfo;
	}

	public String getExpectedStatusMessage() {
		return expectedStatusMessage;
	}

	public void setExpectedStatusMessage(String expectedStatusMessage) {
		this.expectedStatusMessage = expectedStatusMessage;
	}

	public String getinvalidUrl() {

		return invalidUrl;
	}

	public void setvalidDieticianUrl(String invalidUrl) {
		this.invalidUrl = invalidUrl;
	}

}
