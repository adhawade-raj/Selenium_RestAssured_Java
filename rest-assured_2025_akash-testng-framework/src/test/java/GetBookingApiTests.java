import apis.GetBookingApi;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GetBookingApiTests {

    @Parameters("testParam")
    @Test(description = "Basic HTTP Status check for get booking ids API")
    public void validateStatusCodeForGetBookingIdApi(@Optional String testParam) {
        System.out.println("Test Param Value: " + testParam);
        var getBookingIdsResponse = new GetBookingApi().getAllBookingIds()
                                                       .then().assertThat().statusCode(200);
    }

    @Test(description = "Basic HTTP Status check for get booking by ID API")
    public void validateStatusCodeForGetBookingByIdApi() {
        var getBookingByIdApiResponse = new GetBookingApi().getBookingById(20)
                                                           .then().assertThat().statusCode(200);
    }
}
