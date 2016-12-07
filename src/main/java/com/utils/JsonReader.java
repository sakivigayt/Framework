package com.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonReader {

	@SuppressWarnings("unchecked")
	public static String setCapability(String driverName, String capabilityName) {

		final Map<String, Object> returnedMap = loadJsonToMap("E:\\SFramework\\sapientframework\\Capabilities.json");
		Map<String, String> BROWSER = (Map<String, String>) returnedMap
				.get(driverName);
		String capability = BROWSER.get(capabilityName);
		return capability;

	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> loadJsonToMap(String jsonFile) {
		Map<String, Object> map = null;

		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting().serializeNulls();
		Gson gson = builder.create();
		try {
			map = gson.fromJson(new FileReader(new File(jsonFile)), Map.class);
		} catch (FileNotFoundException e) {

		}
		return map;
	}

}
