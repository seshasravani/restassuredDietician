package com.CalorieHackers_Utilities;

import java.util.List;
import java.io.File;
import com.CalorieHackers_POJO.TestDataPOJO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class JsonDataReader {
	private static List<TestDataPOJO> testData;
	// load data
	public static List<TestDataPOJO> readJson(String filePath) {
		if (testData == null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				testData = mapper.readValue(new File(filePath), new TypeReference<List<TestDataPOJO>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to load JSON test data from: " + filePath);
			}
		}
		return testData;
	}
	public static TestDataPOJO getAllTestCase(String filePath,String scenarioName) {
		List<TestDataPOJO> allData = readJson(filePath);
		for (TestDataPOJO data : allData) {
			if (scenarioName.equals(data.getScenarioName())) {
                return data;
            }
		}
		 throw new RuntimeException("Scenario Name " + scenarioName + " not found.");
	}

}
