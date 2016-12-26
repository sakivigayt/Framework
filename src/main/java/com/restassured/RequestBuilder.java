package com.restassured;

import com.jayway.restassured.http.ContentType;
import com.restassured.lib.APIUtil;
import com.restassured.lib.CommonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RequestBuilder {

    public String contentType(String apiName, RequestWrapper request){

        ContentType contentType;

        //Check ContentType required for this API
        String apiExpectedContentType = APIUtil.getAPIExpectedContentType(apiName);

        switch (apiExpectedContentType) {
            case "JSON": {
                contentType = ContentType.JSON;
                break;
            }
            case "XML": {
                contentType = ContentType.XML;
                break;
            }
            default: {
                contentType = ContentType.ANY;
                break;
            }
        }

        //Set ContentType for this request
        request.setContentType(contentType);

        return apiExpectedContentType;
    }

    public void endPoints(String apiName, RequestWrapper request){

        //Get and Set the service endPoint
        String apiEndPoint = APIUtil.getAPIEndPoint(apiName);
        request.setEndPoint(apiEndPoint);
    }

    public void setRequestBody(String apiName, RequestWrapper request){

        //Path of JSON request files
        String basePath = request.getReqFilesLocation() + "JSON-Requests";
        String requestFile = basePath + "/" + apiName + ".json";
        String jsonBody = null;

        try {
            jsonBody = generateStringFromResource(requestFile);
            System.out.println("jsonBody for POST request: \n" + jsonBody);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(StringUtils.isNotEmpty(jsonBody));
        request.setRequestBody(jsonBody);
    }

    //Use to set default charset value
    public void setRequestCharset(RequestWrapper request){
        request.setCharset(APIUtil.getAPIExpectedCharsetValue());
    }

    //Use to set API specific charset value
    public void setRequestCharset(RequestWrapper request, String apiName){
        request.setCharset(APIUtil.getAPIExpectedCharsetValue(apiName));
    }

    public String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
