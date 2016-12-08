package com.restassured;

import com.jayway.restassured.http.ContentType;
import com.restassured.lib.APIUtil;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GetServiceTest_JUnit {

    @Test
    public void testget_json_response() throws Exception {

        // *----------Variable Dec -----------*
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
        assertEquals("API Response Validation Passed", true, result);

    }

    @Test
    public void testget_xml_response() throws Exception {

        // *----------Variable Dec -----------*
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
        assertEquals("API Response Validation Passed", true, result);

    }

}