package runner;

import org.junit.runner.RunWith;

@RunWith(io.cucumber.junit.Cucumber.class)
@io.cucumber.junit.CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        tags = "@createUser",
        plugin = {"pretty", "html:target/cucumber.html"},
        monochrome = true
)

public class TestRunner {
}
