
package com.restassured.lib;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;
import com.restassured.ResponseWrapper;
import org.apache.log4j.Logger;
import org.junit.Assert;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import org.w3c.dom.Node;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;
import org.xmlunit.xpath.XPathEngine;
import org.xmlunit.xpath.JAXPXPathEngine;
import org.xmlunit.matchers.EvaluateXPathMatcher;

import javax.xml.transform.Source;
import java.util.Iterator;

public class APIUtil {
	
	String apiName;
	private final static Logger Log = Logger.getLogger(APIUtil.class); //for logging

	//Validate API Response - Strictly match complete response
	public static boolean validateAPIResponse(ResponseWrapper response, String responseContentType, String testAPIName){

		String expectedAPIResponseJSON=null;
		String expectedAPIResponseFileXML=null;
		String expectedStatusCode;
		String expectedAPIResponseFilePath_XML="src/test/resources/XML-Responses/";

		//Get expected Status code for this service
		expectedStatusCode = getAPIExpectedStatusCode(testAPIName);

		//Verify Status/Response code
		assertEquals("Validating Status Code", expectedStatusCode, response.getResponseCode());

		//Get expected response value
		if (responseContentType.equals("JSON")) {
			expectedAPIResponseJSON = APIUtil.getAPIExpectedResponseJson(testAPIName);

			//Test JSON responses
			assertTrue(JSONUtil.compareJSONs(expectedAPIResponseJSON, response.getResponseBody()));

		} else if (responseContentType.equals("XML")){

			//Get Path of expected XMl file
			expectedAPIResponseFileXML = expectedAPIResponseFilePath_XML + testAPIName + ".xml";

			//Compare XMl files
			Diff xmlCompareResult = DiffBuilder.compare(Input.fromFile(expectedAPIResponseFileXML)).withTest(response.getResponseBody()).build();
			assertTrue(!xmlCompareResult.hasDifferences());
		}

		return true;
	}

	//Validate API Response - Status Code Only
	public static boolean validateAPIResponse(ResponseWrapper response, String responseContentType, String testAPIName, boolean testStatusCode){

		String expectedAPIResponseJSON=null;
		String expectedAPIResponseFileXML=null;
		String expectedStatusCode;
		String expectedAPIResponseFilePath_XML="src/test/resources/XML-Responses/";

		if (testStatusCode) {
			//Get expected Status code for this service
			expectedStatusCode = getAPIExpectedStatusCode(testAPIName);

			//Verify Status/Response code
			assertEquals("Validating Status Code", expectedStatusCode, response.getResponseCode());
		}
		return true;
	}

	//Validate API Response - Compare specific response keys only
	public static boolean validateAPIResponse(ResponseWrapper response, String responseContentType, String testAPIName, String keyToCompare){

		String expectedAPIResponseJSON=null;
		String expectedAPIResponseFileXML=null;
		String expectedStatusCode;
		String expectedAPIResponseFilePath_XML="src/test/resources/XML-Responses/";

		//Get expected Status code for this service
		expectedStatusCode = getAPIExpectedStatusCode(testAPIName);

		//Verify Status/Response code
		assertEquals("Validating Status Code", expectedStatusCode, response.getResponseCode());

		//Get expected response value
		if (responseContentType.equals("JSON")) {
			expectedAPIResponseJSON = APIUtil.getAPIExpectedResponseJson(testAPIName);

			//Test Specific JSON Value
			String jsonTestKey = keyToCompare;
			assertEquals(JSONUtil.getJsonKeyValue(expectedAPIResponseJSON, jsonTestKey), JSONUtil.getJsonKeyValue(response.getResponseBody(),jsonTestKey));

		} else if (responseContentType.equals("XML")){

			//Get Path of expected XMl file
			expectedAPIResponseFileXML = expectedAPIResponseFilePath_XML + testAPIName + ".xml";

			//Compare Individual Node values
			String xmlNodeToBeCompared=keyToCompare;

			//Get Expected value of this node from expected XML response file
			Source sourceXML = Input.fromFile(expectedAPIResponseFileXML).build();
			XPathEngine xpath = new JAXPXPathEngine();
			Iterable<Node> allMatches = xpath.selectNodes(xmlNodeToBeCompared, sourceXML);
			assert allMatches.iterator().hasNext();
			String nodeValue = xpath.evaluate(xmlNodeToBeCompared + "/text()", sourceXML);

			assertThat(response.getResponseBody(), EvaluateXPathMatcher.hasXPath(xmlNodeToBeCompared + "/text()", equalTo(nodeValue)));
		}

		return true;
	}

	//Reads expected status code of API from api.properties file
	public static String getAPIExpectedStatusCode(String sAPIName){
		String statusCode;
		String currEnv;

		currEnv= CommonUtil.getEnv();

		statusCode= CommonUtil.getPropertyValue("service.statusCode." + currEnv + "." + sAPIName);

		return statusCode;
	}

	//Returns the StatusCode of the API's
	public static int getAPIStatusCode(String sAPIURL){
		Response webResponse = null;

		try{
		 webResponse= RestAssured.get(sAPIURL);
		 return webResponse.getStatusCode();
		}
		catch (Exception e){
			Log.info("Invalid URI: " + sAPIURL);
			Assert.fail("Invalid URI: " + sAPIURL);
			return 0;
		}
	}

	//Reads expected status code of API from api.properties file
	public static String getAPIExpectedContentType(String sAPIName){
		String contentType;
		String currEnv;

		currEnv= CommonUtil.getEnv();
		contentType= CommonUtil.getPropertyValue("service.contentType." + currEnv + "." + sAPIName);

		return contentType;
	}

	//Returns True if the API's status code is 200 or OK, else returns False
	public static boolean isAPIOK(String sAPIURL){
		try{
			Response webResponse= RestAssured.get(sAPIURL);
			if (webResponse.getStatusCode()==200) {return true;}
			else {return false;}
		}
		catch (Exception e){
			Log.info("Invalid URI: " + sAPIURL);
			Assert.fail("Invalid URI: " + sAPIURL);
			return false;			
		}	
	}


	//Returns API's Endpoint from Properties file based on environment
	public static String getAPIEndPoint(String sAPIName){
		String sCurrEnv= CommonUtil.getEnv();
		return CommonUtil.getPropertyValue("service." + sCurrEnv + "." + sAPIName);

	}
	
	//Returns API's RequestJson from Properties file based on environment
	public static String getAPIRequestJson(String sAPIName){
		String sCurrEnv= CommonUtil.getEnv();
		return CommonUtil.getPropertyValue("service.request." + sCurrEnv + "." + sAPIName);

	}

	//Returns API's Expected ReponseJson from Properties file based on environment
	public static String getAPIExpectedResponseJson(String sAPIName){
		String sCurrEnv= CommonUtil.getEnv();
		return CommonUtil.getPropertyValue("service.response." + sCurrEnv + "." + sAPIName);
	}


	//Returns API's Expected Charset from Properties file
	public static String getAPIExpectedCharsetValue(){
		String sCurrEnv= CommonUtil.getEnv();
		return CommonUtil.getPropertyValue("charset");
	}

	//Returns API's Expected Charset from Properties file
	public static String getAPIExpectedCharsetValue(String sAPIName){
		String sCurrEnv= CommonUtil.getEnv();
		return CommonUtil.getPropertyValue("service.charset." + sAPIName);
	}

}


