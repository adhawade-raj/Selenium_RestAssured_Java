package practiceTests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.File;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class explains how to extract fields from a response using RestAssured.
 * It covers examples of extracting fields using jsonPath and validating json schema.
 * <p>
 * We can use {@link Response#jsonPath()} to extract fields from a response.
 * We can also validate the response schema using {@link JsonSchemaValidator}.
 * <p>
 * Refer to {@link JSONPathExamples} for more examples on jsonPath usage with nested and complex jsonpath.
 */

public class ResponseExtraction {

    private final RequestSpecification requestSpecification = RestAssured.given()
                                                                         .and().baseUri("https://restful-booker.herokuapp.com")
                                                                         .and().filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    @Test
    public void extractJsonPathFieldsFromResponse() {
        Response inlineValidatedResponse = this.requestSpecification
                .and().basePath("/booking/{bookingId}")
                .and().pathParam("bookingId", 20)
                .when().get()
                .then().assertThat()
                .body("totalprice", is(equalTo(111)))
                .and().body("bookingdates.checkin", is(notNullValue()))
                .extract().response();

        int totalPrice = inlineValidatedResponse.jsonPath().getInt("totalprice");
        String checkinDate = inlineValidatedResponse.jsonPath().getString("bookingdates.checkin");
        Map<String, Object> bookingDatesMap = inlineValidatedResponse.jsonPath().getMap("bookingdates");

        System.out.printf("Checkin date is %s\n", checkinDate);
        System.out.printf("Total Price is %d\n", totalPrice);

        System.out.println("Booking dates map: " + bookingDatesMap);
    }

    @Test
    public void jsonSchemaValidations() {
        Response inlineValidatedResponse = this.requestSpecification
                .and().basePath("/booking/{bookingId}")
                .and().pathParam("bookingId", 20)
                .when().get()
                .then().assertThat()
                .body("totalprice", is(equalTo(111)))
                .and().body("bookingdates.checkin", is(notNullValue()))
                //Validate json response schema
                .and().body(JsonSchemaValidator
                        .matchesJsonSchema(new File("src/test/resources/schema/getBookingByIdSchema.json")))
                .extract().response();
    }

    @Test
    public void setRootPath() {
        Response inlineValidatedResponse = this.requestSpecification
                .and().basePath("/booking/{bookingId}")
                .and().pathParam("bookingId", 20)
                .when().get()
                .then().assertThat()
                //Set the root path so that we can validate child booking dates
                //without using `bookingdates` json path again and again
                .and().rootPath("bookingdates")
                .and().body("checkin", is(notNullValue()))
                .and().body("checkout", is(notNullValue()))
                //Detach the root path so that we can do validations outside
                // the 'bookingdates' rootpath
                .and().detachRootPath("bookingdates")
                //Total price is outside `bookingdates`
                .body("totalprice", is(equalTo(111)))
                .extract().response();
    }
}
