package com.restassured;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class GetService {

	public static String get(String contentType, String uri, String jsonResponse) {
		
		String result=null;
		
		Response response = given()
				.contentType(contentType)
				.get(uri);

		String responseAsString = response.body().asString();
		JsonPath jsonPath = new JsonPath(responseAsString);

		result = jsonPath.getString(jsonResponse);
		
		return result;
			
		
	}

	
}
