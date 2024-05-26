package com.qa.gorest.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.frameworkException.APIFrameworkException;

import io.restassured.response.Response;

public class JsonPathValidator {

	/**
	 * For single JSON response
	 * @param response
	 * @param jsonPath
	 * @return
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
	 * For List of JSON response
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
	 * For list of maps.. to fetch following type of data 
	 * which is in key & value pair
	 * e.g "name" : "raj",
	 * 		"id" : 12
	 * @param response
	 * @param jsonPath
	 * @return
	 */
	public <T> List<Map<String,T>> readListofMap(Response response, String jsonPath) {
		String jsonResponse = response.getBody().asString();
		try {
			return JsonPath.read(jsonResponse, jsonPath);			
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new APIFrameworkException(jsonPath+ " is not found");
		}
	}
}
