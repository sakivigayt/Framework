package com.restassured;

import com.jayway.restassured.http.ContentType;
import com.restassured.lib.APIUtil;
import org.junit.Test;

import static org.junit.Assert.*;


public class PostServiceTest {

    @Test
    public void testpost_json_response_strict_response_check() throws Exception {

        // *----------Variable Dec -----------*
        String apiEndPoint;
        ContentType contentType;
        String expectedStatusCode;
        String expectedAPIResponseJSON;

        // *----------Object Creation -----------*
        PostService postService = new PostService();
        RequestWrapper request = new RequestWrapper();
        RequestBuilder requestBuilder = new RequestBuilder();

        //Name of the service being tested in current test
        String testAPIName = "TestAPI_Post1";

        //Set content type for this request
        String apiExpectedContentType = requestBuilder.contentType(testAPIName, request);

        //Set End Points
        requestBuilder.endPoints(testAPIName, request);

        //Set Charset
        requestBuilder.setRequestCharset(request);

        //set Request Body
        requestBuilder.setRequestBody(testAPIName, request);

        //Get response of service under test with set params
        ResponseWrapper response = postService.post(request);

        System.out.println("Printing Response Body: \n" + response.getResponseBody());

        // * ----- Response Validation -- * //
        boolean result = APIUtil.validateAPIResponse(response, apiExpectedContentType, testAPIName);
        assertEquals("API Response Validation Passed", true, result);

    }


    @Test
    public void testpost_json_response_response_simplekey_check() throws Exception {

        // *----------Variable Dec -----------*
        String apiEndPoint;
        ContentType contentType;
        String expectedStatusCode;
        String expectedAPIResponseJSON;
        String keyToCompare="name";

        // *----------Object Creation -----------*
        PostService postService = new PostService();
        RequestWrapper request = new RequestWrapper();
        RequestBuilder requestBuilder = new RequestBuilder();

        //Name of the service being tested in current test
        String testAPIName = "TestAPI_Post2";

        //Set content type for this request
        String apiExpectedContentType = requestBuilder.contentType(testAPIName, request);

        //Set End Points
        requestBuilder.endPoints(testAPIName, request);

        //Set Charset
        requestBuilder.setRequestCharset(request);

        //set Request Body
        requestBuilder.setRequestBody(testAPIName, request);

        //Get response of service under test with set params
        ResponseWrapper response = postService.post(request);

        System.out.println("Printing Response Body: \n" + response.getResponseBody());

        // * ----- Response Validation -- * //
        boolean result = APIUtil.validateAPIResponse(response, apiExpectedContentType, testAPIName, keyToCompare);
        assertEquals("API Response Validation Passed", true, result);

    }

    @Test
    public void testpost_json_response_response_nestedkey_check() throws Exception {

        // *----------Variable Dec -----------*
        String apiEndPoint;
        ContentType contentType;
        String expectedStatusCode;
        String expectedAPIResponseJSON;
        String keyToCompare="error.errors";

        // *----------Object Creation -----------*
        PostService postService = new PostService();
        RequestWrapper request = new RequestWrapper();
        RequestBuilder requestBuilder = new RequestBuilder();

        //Name of the service being tested in current test
        String testAPIName = "TestAPI_Post1";

        //Set content type for this request
        String apiExpectedContentType = requestBuilder.contentType(testAPIName, request);

        //Set End Points
        requestBuilder.endPoints(testAPIName, request);

        //Set Charset
        requestBuilder.setRequestCharset(request);

        //set Request Body
        requestBuilder.setRequestBody(testAPIName, request);

        //Get response of service under test with set params
        ResponseWrapper response = postService.post(request);

        System.out.println("Printing Response Body: \n" + response.getResponseBody());

        // * ----- Response Validation -- * //
        boolean result = APIUtil.validateAPIResponse(response, apiExpectedContentType, testAPIName, keyToCompare);
        assertEquals("API Response Validation Passed", true, result);

    }


}