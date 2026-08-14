import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.testng.annotations.Test;

import apis.CreateBookingApi;
import apis.DeleteBookingApi;
import apis.GetBookingApi;
import apis.UpdateBookingApi;
import io.restassured.response.Response;
import util.ApiRequestHelper;

public class CRUDTests extends BaseTest {


    @Test(description = "CRUD Operation on Restful Booker API resource", dataProvider = "bookingDataWithStreams")
    public void crudTest(String firstName, String lastName, Boolean depositPaid,
                         String additionalNeeds, Integer totalPrice, String checkInDate, String checkOutDate) {


        var updateBookingApi = new UpdateBookingApi();
        var createBookingPayload = ApiRequestHelper.getCreateBookingApiRequest(firstName, lastName, totalPrice,
                depositPaid, additionalNeeds, checkInDate, checkOutDate);
        var createBookingApi = new CreateBookingApi();
        var deleteBookingApi = new DeleteBookingApi();
        var getBookingApi = new GetBookingApi();

        //Create a new booking - Step 1
        var createBookingApiResponse = createBookingApi.createNewBooking(createBookingPayload)
                                                       .then().assertThat().statusCode(200)
                                                       .and().body("bookingid", is(not(equalTo(0))));

        var bookingId = createBookingApiResponse.extract().jsonPath().getInt("bookingid");

        //Retrieve this created booking using booking ID
        var getBookingByIdApiResponse = getBookingApi.getBookingById(bookingId);
        this.validateRetrievedBookingDataFromGetApi(firstName, lastName, depositPaid, additionalNeeds, totalPrice, checkInDate, checkOutDate, getBookingByIdApiResponse);

        //Update the booking using PUT Api
        var updatedLastName = this.faker.name().lastName();
        var updatedTotalPrice = Math.toIntExact(this.faker.number().randomNumber(3, true));
        var updatedDepositPaid = this.faker.bool().bool();

        createBookingPayload.replace("lastname", updatedLastName);
        createBookingPayload.replace("totalprice", updatedTotalPrice);
        createBookingPayload.replace("depositpaid", updatedDepositPaid);

        var username = System.getenv("RESTBOOKER_USERNAME");
        var password = System.getenv("RESTBOOKER_PASSWORD");
        var updateBookingApiResponse = updateBookingApi.updateBooking(createBookingPayload, bookingId, username, password)
                                                       .then().assertThat().statusCode(200)
                                                       .and().body("lastname", equalTo(updatedLastName))
                                                       .and().body("totalprice", equalTo(updatedTotalPrice))
                                                       .and().body("depositpaid", equalTo(updatedDepositPaid));

        var deleteBookingApiResponse = deleteBookingApi.deleteBookingById(bookingId, username, password)
                                                       .then().assertThat().statusCode(201);
        getBookingApi.getBookingById(bookingId).then().assertThat().statusCode(404);
    }

    private void validateRetrievedBookingDataFromGetApi(String firstName, String lastName, Boolean depositPaid,
                                                        String additionalNeeds, Integer totalPrice, String checkInDate,
                                                        String checkOutDate, Response getBookingByIdApiResponse) {
        getBookingByIdApiResponse
                .then().assertThat().statusCode(200)
                .and().body("firstname", is(equalTo(firstName)))
                .and().body("lastname", is(equalTo(lastName)))
                .and().body("totalprice", is(equalTo(totalPrice)))
                .and().body("depositpaid", is(equalTo(depositPaid)))
                .and().body("additionalneeds", is(equalTo(additionalNeeds)))
                .and().rootPath("bookingdates")
                .and().body("checkin", equalTo(checkInDate))
                .and().body("checkout", equalTo(checkOutDate))
                .and().detachRootPath("bookingdates");
    }
}
