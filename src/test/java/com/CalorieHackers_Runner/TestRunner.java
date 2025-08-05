package com.CalorieHackers_Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "pretty", "html:target/apireports.html",
		"com.aventstack.chaintest.plugins.ChainTestCucumberListener:",
		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" }, // reporting purpose
		dryRun = false, monochrome = true, // console output color


			 //tags = "@userLoginPostAdmin", // tags from feature file
		
//			 tags = "", // tags from feature file


		features = { "src/test/resources/com.CalorieHackers_Features/01_AdminLoginPost.feature",
				"src/test/resources/com.CalorieHackers_Features/02_createDietician.feature",
				"src/test/resources/com.CalorieHackers_Features/03_DieticianLoginPost.feature"
				,"src/test/resources/com.CalorieHackers_Features/04_GetAllDieticianByAdminToken.feature"
				,"src/test/resources/com.CalorieHackers_Features/04_GetAllDieticianByDieticianToken.feature",
				"src/test/resources/com.CalorieHackers_Features/07_CreatePatient_Post.feature",
				"src/test/resources/com.CalorieHackers_Features/07_CreatePatient_withpatientToken.feature",
				"src/test/resources/com.CalorieHackers_Features/13_GetAllPatient.feature",
				"src/test/resources/com.CalorieHackers_Features/14_GetAllMorbidity.feature",
				"src/test/resources/com.CalorieHackers_Features/15_GetMorbiditybyTestName.feature"
				
				
}, // location of feature
																								// files
		// tags = "@userLoginPostAdmin", // tags from feature file


		glue = { "com.CalorieHackers_StepDefinition" }) // location of step definition files

public class TestRunner extends AbstractTestNGCucumberTests {

}
