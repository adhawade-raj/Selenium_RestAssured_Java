package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Bdd01_Search.feature",
        glue = {"org.stepDefinations","hooks"},
        plugin = {"pretty"})

public class Bdd01_amazonTest {
}
