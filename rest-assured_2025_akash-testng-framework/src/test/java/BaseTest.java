import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import util.TestDataHelper;

import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

public class BaseTest {
    protected final Faker faker = TestDataHelper.getFaker();

    @DataProvider(name = "bookingDataWithStreams", parallel = true)
    public Object[][] bookingDataProviderWithStreams() {
        var faker = this.faker;
        var name = faker.name();
        var dateFormatter = DateTimeFormatter.ISO_DATE;
        return IntStream.range(0, 5)
                        .mapToObj(i -> {
                            var numberOfPlusDays = TestDataHelper.getRandomInt(2);
                            return new Object[]{name.firstName(), name.lastName(), faker.bool().bool(),
                                    faker.food().dish(), TestDataHelper.getRandomInt(3),
                                    TestDataHelper.getFutureDate(numberOfPlusDays, dateFormatter),
                                    TestDataHelper.getFutureDate(numberOfPlusDays + 4, dateFormatter)
                            };
                        })
                        .toArray(Object[][]::new);
    }
}
