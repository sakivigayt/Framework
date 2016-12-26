package com.restassured;

import com.jayway.restassured.http.ContentType;
import com.restassured.lib.APIUtil;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetServiceTest_TestNG {
    @Test
    public void testGet_json_response() throws Exception {

        // *----------Variable Dec -----------*
        String apiEndPoint;
        ContentType contentType;
        String expectedStatusCode;
        String expectedAPIResponseJSON;
        String keyToCompare="headers.host";

        // *----------Object Creation -----------*
        GetService getService = new GetService();
        RequestWrapper request = new RequestWrapper();
        RequestBuilder requestBuilder = new RequestBuilder();

        //Name of the service being tested in current test
        String testAPIName = "TestAPI1";

        //Set content type for this request
        String apiExpectedContentType = requestBuilder.contentType(testAPIName,request);

        //Set End Points
        requestBuilder.endPoints(testAPIName, request);

        //Get response of service under test with set params
        ResponseWrapper response = getService.get(request);

        // * ----- Response Validation -- * //
        boolean result = APIUtil.validateAPIResponse(response, apiExpectedContentType, testAPIName, keyToCompare);
        assertEquals(true, result, "API Response Validation Passed");

    }

    @Test
    public void testget_xml_response() throws Exception {

        // *----------Variable Dec -----------*
        String apiEndPoint;
        ContentType contentType;
        String expectedStatusCode;
        String expectedAPIResponseJSON;
        String keyToCompare="/CUSTOMER/ID";

        // *----------Object Creation -----------*
        GetService getService = new GetService();
        RequestWrapper request = new RequestWrapper();
        RequestBuilder requestBuilder = new RequestBuilder();

        //Name of the service being tested in current test
        String testAPIName = "TestAPI3";

        //Set content type for this request
        String apiExpectedContentType = requestBuilder.contentType(testAPIName,request);

        //Set End Points
        requestBuilder.endPoints(testAPIName, request);

        //Get response of service under test with set params
        ResponseWrapper response = getService.get(request);

        // * ----- Response Validation -- * //
        boolean result = APIUtil.validateAPIResponse(response, apiExpectedContentType, testAPIName, keyToCompare);
        assertEquals(true, result, "API Response Validation Passed");

    }

}