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
		System.out.println("Driver name is :: " + driverName);
		System.out.println("Inside set capability");
		final Map<String, Object> returnedMap = loadJsonToMap("Capabilities.json");
		System.out.println("Map returned");
		System.out.println(returnedMap.get("firefox"));
		Map<String, String> BROWSER = (Map<String, String>) returnedMap.get(driverName);
		System.out.println("Browser fetched on driver name");
		String capability = BROWSER.get(capabilityName);
		System.out.println(capability);
		return capability;

	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> loadJsonToMap(String jsonFile) {
		System.out.println("Inside Load to json");
		Map<String, Object> map = null;

		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting().serializeNulls();
		Gson gson = builder.create();
		System.out.println("gson created");
		try {
			System.out.println("Inside try");
			map = gson.fromJson(new FileReader(new File(jsonFile)), Map.class);
		} catch (FileNotFoundException e) {

		}
		System.out.println("return map");
		return map;
	}
	
	public static String getCap() {
		return setCapability("firefox", "browserName");
	}

	public static void main(String args[]) {
		
		System.out.println(getCap());
		
	}
}
