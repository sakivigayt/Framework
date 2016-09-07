package com.restassured;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class PostService {

	public static String post(String contentType, String requestFile,
			String uri, String jsonResponse) {

		String jsonBody = null;
		String result = null;
		try {
			jsonBody = generateStringFromResource(requestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Response response = given().contentType(contentType)
				.body(jsonBody).when()
				.post(uri);

		String responseAsString = response.body().asString();
		JsonPath jsonPath = new JsonPath(responseAsString);

		result = jsonPath.getString(jsonResponse);

		return result;

	}

	public static String generateStringFromResource(String path)
			throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}
}
