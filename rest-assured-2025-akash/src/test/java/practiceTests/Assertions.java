package practiceTests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

/**
 * This class explains how to use assertions in RestAssured.
 * It covers examples of asserting HTTP status code and validating fields in response.
 * <p>
 * We can use {@link ValidatableResponse} to assert HTTP status code and validate fields in response.
 * We can also use {@link Response} to extract fields from response and validate them.
 * <p>
 * Refer to {@link ResponseExtraction} and {@link JSONPathExamples} for more examples on extracting fields from response.
 */

public class Assertions {

    private final RequestSpecification requestSpecification = RestAssured.given()
                                                                         .and().baseUri("https://restful-booker.herokuapp.com")
                                                                         .and().filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    @Test
    public void assertHttpStatusCode() {
        // Single URL param
        ValidatableResponse validatableResponse = this.requestSpecification
                .and().basePath("/booking/{bookingId}")
                .and().pathParam("bookingId", 20)
                .when().get()
                .then().assertThat().statusCode(200);

        // Extract HTTP status code
        var statusCode = validatableResponse.extract().statusCode();

    }

    @Test
    public void useJsonPathForFieldValidation() {
        // Single URL param
        ValidatableResponse validatableResponse = this.requestSpecification
                .and().basePath("/booking/{bookingId}")
                .and().pathParam("bookingId", 20)
                .when().get()
                .then().assertThat().body("totalprice", equalTo(111))
                .and().body("bookingdates.checkin", equalTo("2018-01-01"));

    }

    @Test
    public void nonNullFieldValidation() {
        // Single URL param
        ValidatableResponse validatableResponse = this.requestSpecification
                .and().basePath("/booking/{bookingId}")
                .and().pathParam("bookingId", 20)
                .when().get()
                .then().assertThat()
                .body("totalprice", is(equalTo(111)))
                .and().body("bookingdates.checkin", is(notNullValue()));

        Response response = validatableResponse.extract().response();

        Response inlineValidatedResponse = this.requestSpecification
                .and().basePath("/booking/{bookingId}")
                .and().pathParam("bookingId", 20)
                .when().get()
                .then().assertThat()
                .body("totalprice", is(equalTo(111)))
                .and().body("bookingdates.checkin", is(notNullValue()))
                .extract().response();
    }
}
