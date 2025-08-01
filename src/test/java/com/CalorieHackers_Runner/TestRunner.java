package com.CalorieHackers_Runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = { "pretty", "html:target/apireports.html",
				"com.aventstack.chaintest.plugins.ChainTestCucumberListener:",
		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}, // reporting purpose
		dryRun = false, monochrome = true, // console output color
			 //tags = "@userLoginPostAdmin", // tags from feature file
		features = { "src/test/resources/com.CalorieHackers_Features" }, // location of feature files
		glue = { "com.CalorieHackers_StepDefinition" }) // location of step definition files

public class TestRunner extends AbstractTestNGCucumberTests {
	

}

