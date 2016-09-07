package com.restassured;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PutService {

	public static void put(String contentType, String requestFileName,
			String uri) {

		String jSonBody;
		try {
			jSonBody = generateStringFromResource(requestFileName);
			given().contentType(contentType).body(jSonBody).when().put(uri)
					.then().statusCode(204);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String generateStringFromResource(String path)
			throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
