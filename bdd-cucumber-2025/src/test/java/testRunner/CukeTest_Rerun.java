package testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features= "@target/failed_Rerun.txt",
        glue =  {"org.stepDefinations","hooks"},
        plugin = {"pretty","rerun:target/failed_Rerun.txt"},
        tags="@Rerun")
public class CukeTest_Rerun {

}
