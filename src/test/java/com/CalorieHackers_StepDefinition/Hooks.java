package com.CalorieHackers_StepDefinition;

import com.CalorieHackers_Utilities.ConfigReader;

import io.cucumber.java.BeforeAll;

import static io.restassured.RestAssured.*;

public class Hooks {

	@BeforeAll
	public static void setup() {
		baseURI = ConfigReader.getKeyValues("BASE_URL");
	}

}
