import apis.CreateBookingApi;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.ApiRequestHelper;
import util.TestDataHelper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.hamcrest.Matchers.*;

public class CreateBookingApiTests {

    private CreateBookingApi createBookingApi;
    private final Faker faker = TestDataHelper.getFaker();

    @BeforeClass
    public void initApi() {
        this.createBookingApi = new CreateBookingApi();
    }

    @DataProvider(name = "bookingDataWithForLoop")
    public Object[][] bookingDataProviderWithLoop() {
        var faker = this.faker;
        var name = faker.name();
        var dateFormatter = DateTimeFormatter.ISO_DATE;
        var list = new ArrayList<Object[]>();
        for (int i = 0; i < 3; i++) {
            var numberOfPlusDays = TestDataHelper.getRandomInt(2);
            var objects = new Object[]{name.firstName(), name.lastName(), faker.bool().bool(),
                    faker.food().dish(), TestDataHelper.getRandomInt(3),
                    TestDataHelper.getFutureDate(numberOfPlusDays, dateFormatter),
                    TestDataHelper.getFutureDate(numberOfPlusDays + 4, dateFormatter)};
            list.add(objects);
        }
        return list.toArray(new Object[0][]);
    }

    @Test(description = "Create a new booking and validate HTTP Status code", dataProvider = "bookingDataWithForLoop")
    public void createAndValidateStatusCode(String firstName, String lastName, Boolean depositPaid,
                                            String additionalNeeds, Integer totalPrice, String checkInDate, String checkOutDate) {
        var createBookingPayload = ApiRequestHelper.getCreateBookingApiRequest(firstName, lastName, totalPrice,
                depositPaid, additionalNeeds, checkInDate, checkOutDate);
        this.createBookingApi.createNewBooking(createBookingPayload)
                             .then().assertThat().statusCode(200)
                             .and().body("bookingid", is(not(equalTo(0))));

    }
}
