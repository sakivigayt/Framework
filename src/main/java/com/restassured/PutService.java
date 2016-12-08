package com.restassured;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;


public class PutService {

	private ResponseWrapper response = new ResponseWrapper();

	public ResponseWrapper put(RequestWrapper request) {

		//Get Params
		String result=null;
		String endPoint = request.getEndPoint();
		ContentType contentType = request.getContentType();
		String jsonBody = request.getRequestBody();
		String charset = ";charset="+request.getCharset();
		Response response;

		//fetch post response
		if (request.getCookies() == null) {
			response = given().contentType(contentType+charset).body(jsonBody).when().put(endPoint).thenReturn();
		} else {
			response = given().cookies(request.getCookies()).contentType(ContentType.JSON).body(jsonBody).when().put(endPoint).thenReturn();
		}

		String responseAsString = response.body().asString();
		System.out.println("\n Printing Response as String for put request \n" + responseAsString);

		this.response.setResponseCode(String.valueOf(response.getStatusCode()));
		this.response.setResponseBody(response.getBody().asString());

		return this.response;
	}

}
