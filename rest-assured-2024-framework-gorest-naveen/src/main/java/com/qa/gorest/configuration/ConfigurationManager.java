package com.qa.gorest.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.gorest.frameworkexception.APIFrameworkException;

public class ConfigurationManager {

	private Properties prop;
	private FileInputStream ip;

	public Properties initProp() {
		prop = new Properties();

		// maven: cmd line argument:
		// mvn clean install -Denv="qa"
		// mvn clean install

		String envName = System.getProperty("env");

		try {
			if (envName == null) {
				System.out.println("no env is given...hence running tests on QA env... ");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {
				System.out.println("Running tests on env: " + envName);

				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("Please pass the right env name..." + envName);
					throw new APIFrameworkException("WRONG ENV IS Given");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try

		{
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

}
