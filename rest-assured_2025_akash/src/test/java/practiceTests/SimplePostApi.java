package practiceTests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import pojo.request.BookingDates;
import pojo.request.CreateBookingRequest;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

/**
 * This class explains how to use HTTP POST Calls with RestAssured.
 * It covers examples using request payload as a {@link Map} as
 * well as a DTO Class {@link CreateBookingRequest}.
 * <p>
 * Refer to {@link SimplePutApi} for related HTTP PUT Calls
 */

public class SimplePostApi {

    private final RequestSpecification requestSpecification = RestAssured.given()
                                                                         .and().baseUri("https://restful-booker.herokuapp.com")
                                                                         .and().filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    /**
     * Simple POST API to create a new Booking with a request payload. This test
     * uses
     * Java {@link Map} as request payload.
     * The crated booking ID can be used in
     * {@link SimplePutApi#simplePutApiToUpdateBooking()} method for
     * updating a valid booking.
     */
    @Test()
    public void simplePostApiToCreateBooking() {
        Map<String, Object> requestBody = getCreateBookingPayload("Sam", "Alton", 500);
        Response createBookingApiResponse = this.requestSpecification.basePath("/booking")
                                                                     // Always use ContentType enum for defining contentType
                                                                     .and().contentType(ContentType.JSON)
                                                                     .and().body(requestBody)
                                                                     .when().post()
                                                                     .then().assertThat().statusCode(200)
                                                                     .and().body("bookingid", is(not(equalTo(0))))
                                                                     .extract().response();
    }

    /**
     * This test uses simple POJO class for creating a booking.
     * Refer to the request POJO class {@link CreateBookingRequest}
     */

    @Test
    public void simplePostApiWithPojoToCreateBooking() {
        CreateBookingRequest requestBody = this.getCreateBookingRequestPojo("Sam", "Alton", 500);
        Response createBookingApiResponse = this.requestSpecification.basePath("/booking")
                                                                     // Always use ContentType enum for defining contentType
                                                                     .and().contentType(ContentType.JSON)
                                                                     .and().body(requestBody)
                                                                     .when().post()
                                                                     .then().assertThat().statusCode(200)
                                                                     .and().body("bookingid", is(not(equalTo(0))))
                                                                     .extract().response();

    }

    private Map<String, Object> getCreateBookingPayload(String firstName, String lastName, int totalPrice) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstname", firstName);
        requestBody.put("lastname", lastName);
        requestBody.put("totalprice", totalPrice);
        requestBody.put("depositpaid", false);
        requestBody.put("additionalneeds", "Nothing else");

        // Create booking date map
        Map<String, Object> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", "2024-01-01");
        bookingDatesMap.put("checkout", "2024-02-01");
        requestBody.put("bookingdates", bookingDatesMap);
        return requestBody;
    }

    private CreateBookingRequest getCreateBookingRequestPojo(String firstName, String lastName, int totalPrice) {
        CreateBookingRequest requestBody = new CreateBookingRequest();
        requestBody.setFirstName(firstName);
        requestBody.setLastName(lastName);
        requestBody.setTotalPrice(totalPrice);
        requestBody.setDepositPaid(false);
        requestBody.setAdditionalNeeds("Nothing else");
        // Create booking date object
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckIn("2024-01-01");
        bookingDates.setCheckOut("2024-02-01");
        requestBody.setBookingDates(bookingDates);
        return requestBody;
    }
}
