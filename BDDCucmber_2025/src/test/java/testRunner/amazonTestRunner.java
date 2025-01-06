package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/appFeatures/Search.feature", glue = "stepDefinations", plugin = {
		"pretty" }, // Ensures that the output is printed to the console
		strict = true)
public class amazonTestRunner {

}
