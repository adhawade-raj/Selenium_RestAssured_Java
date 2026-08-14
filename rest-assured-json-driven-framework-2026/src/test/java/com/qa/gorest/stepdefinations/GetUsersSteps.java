package com.qa.gorest.stepdefinations;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.UserIdsData;
import com.qa.gorest.utils.JsonDataReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GetUsersSteps extends BaseTest {

    private RestClient restClient;
    Properties prop;
    String baseUri;
    private String serviceUrl;
    private Response response;
    private String endpoint;

    private UserIdsData userIdsData;
    private Map<String, String> queryParams = new HashMap<>();

    @Given("the API base URI is loaded from configuration")
    public void load_base_uri_from_config() {

        prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(
                    "src/test/resources/config/config.properties"
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String baseURI = prop.getProperty("baseURI");
        restClient = new RestClient(prop, baseURI);
    }

    // ---------------- Given ----------------

    @Given("the API endpoint is {string}")
    public void the_api_endpoint_is(String endpoint) {
        this.serviceUrl = endpoint;
    }

    @Given("the following query parameters:")
    public void the_following_query_parameters(DataTable dataTable) {
        queryParams = new HashMap<>();
        queryParams.putAll(dataTable.asMap(String.class, String.class));
    }
    // ---------------- When ----------------
    @When("I send a GET request without authorization")
    public void send_get_without_authorization() {
        response = restClient.get(
                serviceUrl,
                false,   // includeAuth
                true     // log
        );
    }

    @When("I send a GET request with authorization")
    public void send_get_with_authorization() {
        response = restClient.get(
                serviceUrl,
                true,    // includeAuth
                true     // log
        );
    }

    @When("I send a GET request with authorization and query parameters")
    public void send_get_with_authorization_and_query_params() {
        response = restClient.get(
                serviceUrl,
                null,        // headersMap
                queryParams,
                true,        // includeAuth
                true         // log
        );
    }

    // ---------------- Then ----------------

    @Then("the response status code should be {int}")
    public void validate_status_code(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should contain a list of users")
    public void validate_list_of_users() {
        System.out.println(response.asPrettyString());

        List<?> users = response.jsonPath().getList("$");
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() > 0);
    }

    @Then("the response should contain user with id {int}")
    public void validate_user_by_id(int userId) {
        int actualId = response.jsonPath().getInt("id");
        Assert.assertEquals(userId, actualId);
    }

    @Then("the response should contain users filtered by the query parameters")
    public void validate_filtered_users() {

        List<Map<String, Object>> users = response.jsonPath().getList("$");
        Assert.assertTrue(users.size() > 0);

        for (Map<String, Object> user : users) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                Assert.assertEquals(
                        entry.getValue(),
                        String.valueOf(user.get(entry.getKey()))
                );
            }
        }
    }



    @Given("user ids are loaded from {string}")
    public void load_user_ids(String fileName) {
        String path = "src/test/resources/testData/" + fileName;
        userIdsData = JsonDataReader.readJson(path, UserIdsData.class);
    }

    @When("I send GET request without authorization for each user")
    public void send_get_request() {

        for (Integer userId : userIdsData.getUserIds()) {

            response = given()
                            .baseUri("https://gorest.co.in")
                            .pathParam("userId", userId)
                            .when()
                            .get("/public/v2/users/{userId}")
                            .then()
                            .extract()
                            .response();

            System.out.println(response.prettyPrint());

            System.out.println("UserId: " + userId +
                    " | Status: " + response.getStatusCode());

            Assert.assertEquals(
                    "Status code mismatch for userId: " + userId,
                    200,
                    response.getStatusCode()
            );
        }
    }

    @Then("response status code should be 200")
    public void validate_response() {
        Assert.assertNotNull(response);
    }


}