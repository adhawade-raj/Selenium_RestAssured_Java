package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import utils.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CommonApiSteps {

    @Given("test data file {string} and testcase {string}")
    public void load_test_data(String file, String tc) {

        ScenarioContext.testcase = tc;
        ScenarioContext.dataFile =
                "src/test/resources/testdata/" + file;

        ScenarioContext.request =
                JsonPathUtil.getRequest(
                        ScenarioContext.dataFile,
                        tc
                );

        RestAssured.baseURI =
                ConfigReader.get("base.uri");
    }

    @When("user calls the api")
    public void call_api() {

        String endpoint =
                JsonPathUtil.getEndpoint(
                        ScenarioContext.dataFile,
                        ScenarioContext.testcase
                );

        String method =
                JsonPathUtil.getMethod(
                        ScenarioContext.dataFile,
                        ScenarioContext.testcase
                );

        if (method.equalsIgnoreCase("POST")) {
            ScenarioContext.response =
                    given()
                            .contentType("application/json")
                            .header(
                                    "Authorization",
                                    ConfigReader.get("auth.token")
                            )
                            .body(ScenarioContext.request)
                            .when()
                            .post(endpoint);
        }
    }

    @Then("response status should match expected")
    public void validate_response() {

        int expected =
                JsonPathUtil.getExpectedStatus(
                        ScenarioContext.dataFile,
                        ScenarioContext.testcase
                );

        assertEquals(
                expected,
                ScenarioContext.response.statusCode()
        );
    }
}
