package testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features= "src/test/resources/",
        glue =  {"org.stepDefinations","hooks"},
        plugin = {"pretty"},
        tags="@Order_DryRun",
        dryRun = true
)
public class CukeTest_DryRun {
}
