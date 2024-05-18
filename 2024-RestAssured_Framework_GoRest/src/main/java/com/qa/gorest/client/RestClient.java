package com.qa.gorest.client;

import java.util.HashMap;
import java.util.Map;

import com.qa.gorest.frameworkException.APIFrameworkException;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	
	private static final String BASE_URI = "https://gorest.co.in";
	private static final String BEARER_TOKEN = "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77";

	private static RequestSpecBuilder specBuilder;
	
	static {
		specBuilder = new RequestSpecBuilder();
	}
	
	/**
	 * 
	 * To set bearer token
	 * @return 
	 */
	public RequestSpecification addAuthorizationHeader() {
		specBuilder.addHeader("Authorization", BEARER_TOKEN);
		return specBuilder.build();
	}
	
	private void setRequestContentType(String contentType) {
		
		switch(contentType.toLowerCase()) {
		case "json" :
			specBuilder.setContentType(ContentType.JSON);
			break;
			
		case "xml" :
			specBuilder.setContentType(ContentType.XML);
			break;
			
		case "html" :
			specBuilder.setContentType(ContentType.HTML);
			break;
			
		case "text" :
			specBuilder.setContentType(ContentType.TEXT);
			break;
			
		case "multipart" :
			specBuilder.setContentType(ContentType.MULTIPART);
			break;
			
		default :
			System.out.println("Please pass the right content type");
			throw new APIFrameworkException("InvalidContentType");
		}
	}
	
	/**
	 * 1
	 * if we want to pass multiple headers along with bearer token
	 * @param headersMap
	 * @return
	 */
	public RequestSpecification addAuthorizationHeader(HashMap<String, String> headersMap) {
		specBuilder.addHeader("Authorization", BEARER_TOKEN);
		if(headersMap!=null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}
	
	/**
	 * 2
	 * To Add multiple or single query param along with headers and bearer token
	 * @param headersMap
	 * @param querParams
	 * @return
	 */
	public RequestSpecification addAuthorizationHeader(HashMap<String, String> headersMap,
														Map<String, String> querParams) {
		specBuilder.addHeader("Authorization", BEARER_TOKEN);
		if(headersMap!=null) {
			specBuilder.addHeaders(headersMap);
		}
		if(querParams!=null) {
			specBuilder.addQueryParams(querParams);
		}
		return specBuilder.build();
	}
	
	/**
	 * 3
	 * To set body & to pass desired content type
	 * @param requestBody
	 * @param contentType
	 * @return
	 */
	public RequestSpecification addAuthorizationHeader(Object requestBody, String contentType) {
		
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		setRequestContentType(contentType);
		
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}
	
	/**
	 * 4
	 * To set body & to pass desired content type & to pass multiple or single header
	 * @param requestBody
	 * @param contentType
	 * @param headersMap
	 * @return
	 */
public RequestSpecification addAuthorizationHeader(Object requestBody, 
													String contentType,
													HashMap<String, String> headersMap) {
		
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		setRequestContentType(contentType);
		
		if(headersMap!=null) {
			specBuilder.addHeaders(headersMap);
		}
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}



}
