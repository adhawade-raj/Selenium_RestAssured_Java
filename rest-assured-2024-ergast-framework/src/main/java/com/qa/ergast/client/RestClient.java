package com.qa.ergast.client;

import java.util.Map;
import java.util.Properties;

import com.qa.ergast.frameworkException.APIFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	private RequestSpecBuilder specBuilder;
	boolean isAuthorizationAdded=false;
	private Properties prop;
	private String baseURI;
	
	public RestClient(Properties prop, String baseURI){
		specBuilder = new RequestSpecBuilder();
		this.baseURI=baseURI;
		this.prop=prop;
		
	}
	
	public void addAuthorization() {
		if(!isAuthorizationAdded) {
			specBuilder.addHeader("Authorization", prop.getProperty("tokenID"));
			isAuthorizationAdded=true;
		}
	}
	
	private void setContentType(String contentType) {
		
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			
		case "html":
			specBuilder.setContentType(ContentType.HTML);
			
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
			
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			
		default:
			System.out.println("Please Pass the right content type");
			throw new APIFrameworkException("InvalidContentType");
		}
		
	}
	
	/** Only authentication Header as requestSpec */
	public RequestSpecification createRequestSpec(boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorization();
		}
		return specBuilder.build();
	}
	
	/** Authorization & ContenetType*/
	public RequestSpecification createRequestSpec(boolean includeAuth, String contentType) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorization();
		}
		setContentType(contentType);

		return specBuilder.build();
	}
	
	/** Authentication, headers*/
	public RequestSpecification createRequestSpec(boolean includeAuth, Map<String, String>headersMap) {
		specBuilder.setBaseUri(baseURI);
		if(!includeAuth) {
			addAuthorization();
		}
		if(headersMap!=null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}
	
	/** Authentication, headers, queryParams*/
public RequestSpecification createRequestSpec(boolean includeAuth, 
												Map<String, String>headersMap,
												Map<String, String> queryParams) {
	specBuilder.setBaseUri(baseURI);
		if(!includeAuth) {
			addAuthorization();
		}
		
		if(headersMap!=null) {
			specBuilder.addHeaders(headersMap);
		}
		if(queryParams!=null) {
			specBuilder.addQueryParams(queryParams);
		}
		
		return specBuilder.build();
	}

	/** TO set Body*/
	public RequestSpecification createRequestSpec(Object requestBody,
														   String contentType) {
		specBuilder.setBaseUri(baseURI);
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}
		if(contentType!=null) {
			setContentType(contentType);
		}
		return specBuilder.build();
	}

	/**requestBody, Content, headers, authorzation*/
	public RequestSpecification createRequestSpec(boolean includeAuth,
															String contentType,
															Map<String, String> headersMap,
															Object requetBody)	{
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorization();
		}
		if(contentType!=null) {
			setContentType(contentType);
		}
		if(headersMap!=null) {
			specBuilder.addHeaders(headersMap);
		}
		if(requetBody!=null) {
			specBuilder.setBody(requetBody);
		}
		
		return specBuilder.build();
	}
	
	/**GET CALLS
	 * @return */
	
	
	/**
	 * 
	 * @param serviceUrl
	 * @param log
	 * @param includeAuth
	 * @return
	 */
	public Response get(String serviceUrl, boolean log, boolean includeAuth) {
		if(log) {
			RestAssured.given(createRequestSpec(includeAuth)).log().all()
			.when()
			.get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth))
				.when()
				.get(serviceUrl);
	}
	
	/**
	 * 
	 * @param serviceUrl
	 * @param headersMap
	 * @param log
	 * @param includeAuth
	 * @return
	 */
	public Response get(String serviceUrl, Map<String,String>headersMap, boolean log, boolean includeAuth) {
		if(log) {
		RestAssured.given(createRequestSpec(includeAuth, headersMap)).log().all()
		.when()
		.get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth, headersMap))
				.when()
				.get(serviceUrl);
		}

	
	public Response get(String serviceUrl, Map<String,String>headersMap, Map<String, String> queryParams,boolean log, boolean includeAuth) {
		
		if(log) {
			RestAssured.given(createRequestSpec(includeAuth, headersMap, queryParams)).log().all()
			.when()
			.get(serviceUrl);
		}
		
		return RestAssured.given(createRequestSpec(includeAuth, headersMap, queryParams))
				.when()
				.get(serviceUrl);
	}
	
}
