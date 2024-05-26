package com.qa.gorest.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configurations.ConfigurationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {

	protected ConfigurationManager config;
	protected Properties prop;
	protected RestClient restClient;
	protected String baseURI;
	
	//ServiceUrl
	public static final String GOREST_ENDPOINT="/public/v2/users";
	public static final String CIRCUIT_ENDPOINT="/api/f1";
	
	/** This is to run diff baseURI from testng.xml*/
	@Parameters({"baseURI"})
	@BeforeTest
	public void setup(String baseURI) {
		RestAssured.filters(new AllureRestAssured());
		config = new ConfigurationManager();
		prop = config.initProp();
		this.baseURI=baseURI;
//		restClient = new RestClient(prop, baseURI);
	}
	
	/** This is to run same single baseURI from config.properties file*/
	@BeforeTest
	public void setup() {
	RestAssured.filters(new AllureRestAssured());
		config = new ConfigurationManager();
		prop = config.initProp();
		String baseURI=prop.getProperty("baseURI");
		this.baseURI=baseURI;
//		restClient = new RestClient(prop, baseURI);
	}
	
	
}
