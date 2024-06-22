package com.qa.ergast.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;

import com.qa.ergast.client.RestClient;
import com.qa.ergast.configurations.ConfigurationManager;

public class BaseTest {

	protected Properties prop;
	protected RestClient restClient;
	protected ConfigurationManager config;
	protected String baseURI;
	
	//ServiceURL
	public static final String CIRCUIT_ENDPOINT="/api/f1";
	
	@BeforeTest()
	public void setup() {
		config = new ConfigurationManager();
		prop = config.initProp();
		String baseURI = prop.getProperty("baseURI");
		this.baseURI=baseURI;
		restClient = new RestClient(prop, baseURI);
	}
}
