import apis.CreateBookingApi;
import apis.DeleteBookingApi;
import apis.UpdateBookingApi;
import org.testng.annotations.Test;
import util.ApiRequestHelper;

import static org.hamcrest.Matchers.*;

public class UpdateBookingApiTests extends BaseTest {

    @Test(description = "Update a newly created booking and validate HTTP Status code", dataProvider = "bookingDataWithStreams")
    public void updateAndValidateStatusCode(String firstName, String lastName, Boolean depositPaid,
                                            String additionalNeeds, Integer totalPrice, String checkInDate, String checkOutDate) {

        var updateBookingApi = new UpdateBookingApi();
        var createBookingPayload = ApiRequestHelper.getCreateBookingApiRequest(firstName, lastName, totalPrice,
                depositPaid, additionalNeeds, checkInDate, checkOutDate);
        var createBookingApi = new CreateBookingApi();
        var deleteBookingApi = new DeleteBookingApi();

        var createBookingApiResponse = createBookingApi.createNewBooking(createBookingPayload)
                                                       .then().assertThat().statusCode(200)
                                                       .and().body("bookingid", is(not(equalTo(0))));

        var bookingId = createBookingApiResponse.extract().jsonPath().getInt("bookingid");

        createBookingPayload.replace("lastname", this.faker.name().lastName());
        createBookingPayload.replace("totalprice", this.faker.number().randomNumber(3, true));
        createBookingPayload.replace("depositpaid", this.faker.bool().bool());

        var username = System.getenv("RESTBOOKER_USERNAME");
        var password = System.getenv("RESTBOOKER_PASSWORD");
        var updateBookingApiResponse = updateBookingApi.updateBooking(createBookingPayload, bookingId, username, password)
                                                       .then().assertThat().statusCode(200);

        var deleteBookingApiResponse = deleteBookingApi.deleteBookingById(bookingId, username, password)
                                                       .then().assertThat().statusCode(201);

    }
}
