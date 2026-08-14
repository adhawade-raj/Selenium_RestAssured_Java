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

/**
 * This class explains how to use HTTP PUT Calls with RestAssured.
 * It covers example using request payload as a Map<String,Object.
 * <p>
 * Refer to {@link SimplePostApi} for related HTTP POST Call.
 */

public class SimplePutApi {

    private final RequestSpecification requestSpecification = RestAssured.given()
                                                                         .and().baseUri("https://restful-booker.herokuapp.com")
                                                                         .and().filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    /**
     * Update an already created booking with a request payload, path params and
     * basic auth.
     * The booking created in {@link SimplePostApi#simplePostApiToCreateBooking()}
     * can be used for
     * updating.
     * <p>
     * Use new booking ID for practicing this example as the old IDs might not work again.
     **/
    @Test
    public void simplePutApiToUpdateBooking() {
        Map<String, Object> requestBody = this.getCreateBookingPayload("Sam", "Alton", 1000);
        Response createBookingApiResponse = this.requestSpecification.basePath("/booking/{bookingId}")
                                                                     // Always use ContentType enum for defining contentType
                                                                     .and().contentType(ContentType.JSON)
                                                                     .and().body(requestBody)
                                                                     .and().pathParam("bookingId", 1418)
                                                                     .and().auth().preemptive().basic("admin", "password123")
                                                                     .when().put()
                                                                     .then().assertThat().statusCode(200)
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
