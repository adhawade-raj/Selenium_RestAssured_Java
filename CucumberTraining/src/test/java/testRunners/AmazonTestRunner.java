package testRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.platform.engine.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@Cucumber(
		{features= {"src/test/java/appFeatures/Search.Feature"}
		glue= {"stepdefinations"}
}
)



public class AmazonTestRunner {

}
