package practiceTests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.request.BookingDates;
import pojo.request.CreateBookingRequest;
import pojo.response.CreateBookingResponse;

/**
 * This class explains how to parse response into a POJO class using RestAssured.
 * It covers examples of creating a booking and parsing the response into a POJO class.
 * <p>
 * We can use {@link io.restassured.response.ResponseBodyExtractionOptions#as(Class)}
 * to parse response as Java class.
 */

public class ResponseParsing {

    private final RequestSpecification requestSpecification = RestAssured.given()
            .and().baseUri("https://restful-booker.herokuapp.com")
            .and().filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    /**
     * We are using RestAssured's {@link io.restassured.response.ResponseBodyExtractionOptions#as(Class)}
     * to parse response as Java class.
     */
    @Test
    public void parseResponseIntoPojo() {
        CreateBookingRequest requestBody = this.getCreateBookingRequestPojo("Sam", "Alton", 500);
        Response createBookingApiResponse = this.requestSpecification.basePath("/booking")
                //Always use ContentType enum for defining contentType
                .and().contentType(ContentType.JSON)
                .and().body(requestBody)
                .when().post()
                .then().assertThat().statusCode(200)
                .and().body("bookingid", is(not(equalTo(0))))
                .extract().response();

        //Parse response as a class.
        CreateBookingResponse createBookingResponse = createBookingApiResponse.as(CreateBookingResponse.class);
        Assert.assertEquals(createBookingResponse.getBooking().getFirstName(), "Sam",
                "The firstname is not correct");
        Assert.assertEquals(createBookingResponse.getBooking().getBookingDates().getCheckIn(), "2024-01-01",
                "The check-in date is not correct");

    }

    private CreateBookingRequest getCreateBookingRequestPojo(String firstName, String lastName, int totalPrice) {
        CreateBookingRequest requestBody = new CreateBookingRequest();
        requestBody.setFirstName(firstName);
        requestBody.setLastName(lastName);
        requestBody.setTotalPrice(totalPrice);
        requestBody.setDepositPaid(false);
        requestBody.setAdditionalNeeds("Nothing else");
        //Create booking date map
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckIn("2024-01-01");
        bookingDates.setCheckOut("2024-02-01");
        requestBody.setBookingDates(bookingDates);
        return requestBody;
    }
}
