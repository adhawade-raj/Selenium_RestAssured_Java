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
	
	/** This is to run diff baseURI from testng.xml*/
//	@Parameters({"baseURI"})
//	@BeforeTest
//	public void setup(String baseURI) {
//		RestAssured.filters(new AllureRestAssured());
//		config = new ConfigurationManager();
//		prop = config.initProp();
//		restClient = new RestClient(prop, baseURI);
//	}
	
	/** This is to run same single baseURI from config.properties file*/
	@BeforeTest
	public void setup() {
	RestAssured.filters(new AllureRestAssured());
		config = new ConfigurationManager();
		prop = config.initProp();
		String baseURI=prop.getProperty("baseURI");
		this.baseURI=baseURI;
		restClient = new RestClient(prop, baseURI);
	}
	
}
