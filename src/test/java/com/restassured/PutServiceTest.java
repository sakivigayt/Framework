package com.restassured;

import com.jayway.restassured.http.ContentType;
import com.restassured.lib.APIUtil;
import org.junit.Test;

import static org.junit.Assert.*;


public class PutServiceTest {

    @Test
    public void testput() throws Exception {

        // *----------Variable Dec -----------*
        String apiEndPoint;
        ContentType contentType;
        String expectedStatusCode;
        String expectedAPIResponseJSON;
        String keyToCompare="job";

        // *----------Object Creation -----------*
        PutService putService = new PutService();
        RequestWrapper request = new RequestWrapper();
        RequestBuilder requestBuilder = new RequestBuilder();

        //Name of the service being tested in current test
        String testAPIName = "TestAPI_Put1";

        //Set content type for this request
        String apiExpectedContentType = requestBuilder.contentType(testAPIName, request);

        //Set End Points
        requestBuilder.endPoints(testAPIName, request);

        //Set Charset
        requestBuilder.setRequestCharset(request);

        //set Request Body
        requestBuilder.setRequestBody(testAPIName, request);

        //Get response of service under test with set params
        ResponseWrapper response = putService.put(request);

        System.out.println("Printing Response Body: \n" + response.getResponseBody());

        // * ----- Response Validation -- * //
        boolean result = APIUtil.validateAPIResponse(response, apiExpectedContentType, testAPIName, keyToCompare);
        assertEquals("API Response Validation Passed", true, result);

    }


}