package com.CalorieHackers_POJO;

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
	private String adminToken;
	private String dieticianToken;
	private String patientToken;
	

	

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

}
