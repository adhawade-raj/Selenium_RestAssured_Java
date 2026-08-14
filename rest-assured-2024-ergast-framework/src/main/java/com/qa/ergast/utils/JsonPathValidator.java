package com.qa.ergast.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.ergast.frameworkException.APIFrameworkException;

import io.restassured.response.Response;

public class JsonPathValidator {

	/**
	 * 
	 */
	public <T> T read(Response response, String jsonPath) {
		
		String jsonResponse = response.getBody().asString();
		try {
		return JsonPath.read(jsonResponse, jsonPath);
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new APIFrameworkException(jsonPath+ " is not found");
		}
	}
	
	/**
	 * 
	 * @param <T>
	 * @param response
	 * @param jsonPath
	 * @return
	 */

	public <T> List<T> readList(Response response, String jsonPath) {
		String jsonResponse = response.getBody().asString();
		try {
			return JsonPath.read(jsonResponse, jsonPath);			
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new APIFrameworkException(jsonPath+ " is not found");
		}
	}
	
	/**
	 * 
	 * @param <T>
	 * @param response
	 * @param jsonPath
	 * @return
	 */
	public <T> List<Map<String,T>> readMapList(Response response, String jsonPath) {
		String jsonResponse = response.getBody().asString();
		
		try {
			return JsonPath.read(jsonResponse, jsonPath);
		}catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new APIFrameworkException(jsonPath+ " is not found");
		}
	}
	
}
