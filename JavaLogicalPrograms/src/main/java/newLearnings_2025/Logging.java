package newLearnings_2025;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Logging {

	// Create a logger instance
	static final Logger logger = Logger.getLogger(Logging.class);

	public static void main(String[] args) {

		System.setProperty("log4j.configurationFile", "src/test/resources/log4j.properties");

		// Now configure Log4j
		PropertyConfigurator.configure(System.getProperty("log4j.configurationFile"));

		logger.info("Logging execution started.");

		logger.debug("This is a sample debug message.");
		logger.info("This is a sample info message.");
		logger.warn("This is a sample warning message.");
		logger.error("This is a sample error message.");
		logger.fatal("This is a sample fatal message.");

		logger.info("Logging execution ended.");
	}
}
