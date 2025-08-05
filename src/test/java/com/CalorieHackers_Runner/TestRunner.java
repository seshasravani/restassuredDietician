package com.CalorieHackers_Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "pretty", "html:target/apireports.html",
		"com.aventstack.chaintest.plugins.ChainTestCucumberListener:",
		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" }, // reporting purpose
		dryRun = false, monochrome = true, // console output color
		features = { "src/test/resources/com.CalorieHackers_Features/01_AdminLoginPost.feature",
				"src/test/resources/com.CalorieHackers_Features/02_createDietician.feature",
				"src/test/resources/com.CalorieHackers_Features/03_DieticianLoginPost.feature"
				,"src/test/resources/com.CalorieHackers_Features/04_GetAllDieticianByAdminToken.feature"
				,"src/test/resources/com.CalorieHackers_Features/04_GetAllDieticianByDieticianToken.feature",
				"src/test/resources/com.CalorieHackers_Features/07_CreatePatient_Post.feature"
				,"src/test/resources/com.CalorieHackers_Features/08_PatientLoginPost.feature"
				,"src/test/resources/com.CalorieHackers_Features/10_PutNewReportsPatient.feature"
				,"src/test/resources/com.CalorieHackers_Features/11_GetPatientsMorbidity.feature"
				,"src/test/resources/com.CalorieHackers_Features/12_GetPatientFileByFileId.feature"
				,"src/test/resources/com.CalorieHackers_Features/16_DeletePatient.feature"
				}, // location of feature files
//		 tags = "@GetPatientFileByFileID", // tags from feature file

		glue = { "com.CalorieHackers_StepDefinition" }) // location of step definition files

public class TestRunner extends AbstractTestNGCucumberTests {

}
