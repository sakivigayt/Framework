package com.restassured;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;


public class GetService {

	private ResponseWrapper response = new ResponseWrapper();

	public ResponseWrapper get(RequestWrapper request) {

		//Get Params
		String result=null;
		String endPoint = request.getEndPoint();
		ContentType contentType = request.getContentType();
		Response response;

		//fetch get response
		if (request.getCookies() == null) {
			response = given().contentType(contentType).get(endPoint);
		} else {
			response = given().cookies(request.getCookies()).contentType(contentType).get(endPoint);
		}

		String responseAsString = response.body().asString();
//		System.out.println("\n Printing Response as String for get request\n" + responseAsString);

		this.response.setResponseCode(String.valueOf(response.getStatusCode()));
		this.response.setResponseBody(response.getBody().asString());

		return this.response;
	}

	
}
