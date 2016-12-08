package com.restassured;

import com.jayway.restassured.http.ContentType;
import com.restassured.lib.APIUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteServiceTest {

    @Test
    public void delete() throws Exception {

        // *----------Variable Dec -----------*
        String apiEndPoint;
        ContentType contentType;
        String expectedStatusCode;
        String expectedAPIResponseJSON;
        boolean testStatusCode = true;

        // *----------Object Creation -----------*
        DeleteService deleteService = new DeleteService();
        RequestWrapper request = new RequestWrapper();
        RequestBuilder requestBuilder = new RequestBuilder();

        //Name of the service being tested in current test
        String testAPIName = "TestAPI_Delete1";

        //Set content type for this request
        String apiExpectedContentType = requestBuilder.contentType(testAPIName, request);

        //Set End Points
        requestBuilder.endPoints(testAPIName, request);

        //Set Charset
        requestBuilder.setRequestCharset(request);

        //Get response of service under test with set params
        ResponseWrapper response = deleteService.delete(request);

        // * ----- Response Validation -- * // Test response will not have any body. Just checking status code
        boolean result = APIUtil.validateAPIResponse(response, apiExpectedContentType, testAPIName, testStatusCode);
        assertEquals("API Response Status Code Validation Passed", true, result);
    }

}