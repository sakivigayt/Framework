package com.restassured;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.log.LoggerFactory;
import com.log.MyLogger;

public class GetService {

	private ResponseWrapper response = new ResponseWrapper();
	private final static MyLogger logger = LoggerFactory.getLogger(GetService.class);

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
		logger.info("\n Printing Response as String for get request\n" + responseAsString);

		this.response.setResponseCode(String.valueOf(response.getStatusCode()));
		this.response.setResponseBody(response.getBody().asString());

		return this.response;
	}

	
}
