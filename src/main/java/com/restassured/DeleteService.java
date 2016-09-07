package com.restassured;

import static com.jayway.restassured.RestAssured.given;

public class DeleteService {

	public static void delete(String contentType, String uri) {
		
		given().contentType(contentType)
		.when()
		.delete(uri)
		.then().statusCode(204);
	}
}
