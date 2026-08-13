package testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features= "src/test/resources/",
        glue =  {"org.stepDefinations","hooks"},
        plugin = {"pretty",
                "json:target/MyReports/report.json",
                "junit:target/MyReports/report.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        tags="@Smoke")
public class CukeTest_Reports {
}
