package com.qa.gorest.client;

import java.util.HashMap;
import java.util.Map;

import com.qa.gorest.frameworkException.APIFrameworkException;
import io.restassured.response.Response;
import io.restassured.RestAssured;
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
	private RequestSpecification addAuthorizationHeader() {
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
	 * 
	 * @return
	 */
	 private RequestSpecification createRequestSpec() {
		 specBuilder.setBaseUri(BASE_URI);
		 addAuthorizationHeader();
		return specBuilder.build();
	}
	
	/**
	 * 1
	 * if we want to pass multiple headers along with bearer token
	 * @param headersMap
	 * @return
	 */
	 private RequestSpecification createRequestSpec(Map<String, String> headersMap) {
		 specBuilder.setBaseUri(BASE_URI);
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
	private RequestSpecification createRequestSpec(Map<String, String> headersMap,
														Map<String, String> querParams) {
		specBuilder.setBaseUri(BASE_URI);
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
	private RequestSpecification createRequestSpec(Object requestBody, String contentType) {
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
	private RequestSpecification createRequestSpec(Object requestBody, 
													String contentType,
													Map<String, String> headersMap) {
		
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

	/**GET Call Methods*/
	 
	/**
	 * 
	 * @param seriveUrl
	 * @param log
	 * @return
	 */
	public Response get(String seriveUrl, boolean log) {
		
		if(log) {
			return RestAssured.given(createRequestSpec()).log().all()
			.when()
			.get(seriveUrl);
		}
		return RestAssured.given(createRequestSpec())
		.when()
		.get(seriveUrl);
	}
	
	/**
	 * 
	 * @param seriveUrl
	 * @param headersMap
	 * @param log
	 * @return
	 */
	public Response get(String seriveUrl, Map<String, String> headersMap, boolean log) {
		
		if(log) {
			return RestAssured.given(createRequestSpec(headersMap)).log().all()
			.when()
			.get(seriveUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap))
				.when()
				.get(seriveUrl);
	}

	/**
	 * 
	 * @param seriveUrl
	 * @param headersMap
	 * @param queryParams
	 * @param log
	 * @return
	 */
	public Response get(String seriveUrl, 
						Map<String, String> headersMap, 
						Map<String, String> queryParams, 
						boolean log) {
		
		if(log) {
			return RestAssured.given(createRequestSpec(headersMap, queryParams)).log().all()
			.when()
			.get(seriveUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap, queryParams))
				.when()
				.get(seriveUrl);
	}

				/**POST Call Methods*/

	/**
	 * 
	 * @param seriveUrl
	 * @param contentType
	 * @param requestBody
	 * @param log
	 * @return
	 */
	public Response post(String seriveUrl, 
			String contentType, 
			Object requestBody,
			boolean log) {

	if(log) {
		return RestAssured.given(createRequestSpec(requestBody,contentType)).log().all()
	.when()
	.post(seriveUrl);
  }
		return RestAssured.given(createRequestSpec(requestBody,contentType)).log().all()
		.when()
		.post(seriveUrl);
  }
	
	/**
	 * 
	 * @param seriveUrl
	 * @param contentType
	 * @param headersMap
	 * @param requestBody
	 * @param log
	 * @return
	 */
	public Response post(String seriveUrl, 
						String contentType,
					Map<String, String> headersMap, 
					Object requestBody,
					boolean log) {
		
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap)).log().all()
			.when()
			.post(seriveUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap)).log().all()
				.when()
				.post(seriveUrl);
	}
	
	/**PUT Call Methods*/
	
	/**
	 * 
	 * @param seriveUrl
	 * @param contentType
	 * @param requestBody
	 * @param log
	 * @return
	 */
	public Response put(String seriveUrl, 
			String contentType, 
			Object requestBody,
			boolean log) {

	if(log) {
		return RestAssured.given(createRequestSpec(requestBody,contentType)).log().all()
	.when()
	.put(seriveUrl);
  }
		return RestAssured.given(createRequestSpec(requestBody,contentType)).log().all()
		.when()
		.put(seriveUrl);
  }
	/**
	 * 
	 * @param seriveUrl
	 * @param contentType
	 * @param headersMap
	 * @param requestBody
	 * @param log
	 * @return
	 */
	public Response put(String seriveUrl, 
						String contentType,
						Map<String, String> headersMap, 
						Object requestBody,
		boolean log) {

		if(log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap)).log().all()
					.when()
					.put(seriveUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap)).log().all()
	.when()
	.put(seriveUrl);
	}
	
	/**PATCH Methods*/
	/**
	 * 
	 * @param seriveUrl
	 * @param contentType
	 * @param requestBody
	 * @param log
	 * @return
	 */
	public Response patch(String seriveUrl, 
			String contentType, 
			Object requestBody,
			boolean log) {

	if(log) {
		return RestAssured.given(createRequestSpec(requestBody,contentType)).log().all()
	.when()
	.patch(seriveUrl);
  }
		return RestAssured.given(createRequestSpec(requestBody,contentType)).log().all()
		.when()
		.patch(seriveUrl);
  }
	
	/**
	 * 
	 * @param seriveUrl
	 * @param contentType
	 * @param headersMap
	 * @param requestBody
	 * @param log
	 * @return
	 */
	public Response patch(String seriveUrl, 
			String contentType,
			Map<String, String> headersMap, 
			Object requestBody,
			boolean log) {

		if(log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap)).log().all()
		.when()
		.patch(seriveUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap)).log().all()
				.when()
				.patch(seriveUrl);
	}
	
	/**PUT Call Methods*/
	
	/**
	 * 
	 * @param seriveUrl
	 * @param log
	 * @return
	 */
	public Response delete(String seriveUrl, boolean log) {
		
		if(log) {
			return RestAssured.given(addAuthorizationHeader()).log().all()
			.when()
			.delete(seriveUrl);
		}
		return RestAssured.given(addAuthorizationHeader())
		.when()
		.delete(seriveUrl);
	}
	
}
