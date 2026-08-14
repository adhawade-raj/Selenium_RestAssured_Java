import apis.GetBookingApi;
import org.awaitility.Awaitility;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Awaitility is a library that allows you to test asynchronous code more effectively.
 * This class demonstrates how to use Awaitility to wait for a condition to be met.
 * <p>
 * See Also:
 * <p>
 * Detailed documentation: <a href="<a href="<a href="https://github.com/awaitility/awaitility/wiki/Usage">Awaitility Usage</a>
 */

public class AwaitilityTests {


    /**
     * Wait until the single or multiple assertions are met.
     */

    @Test
    public void waitUntilAsserted() {
        var getBookingApi = new GetBookingApi();

        Awaitility.await()
                  .and().with().alias("My custom message")
                  .and().with().timeout(Duration.ofSeconds(5))
                  .then().untilAsserted(() -> {
                      getBookingApi.getBookingById(20)
                                   .then().assertThat().statusCode(200);

                      getBookingApi.getBookingById(20)
                                   .then().assertThat().statusCode(400);

                  });

    }

    /**
     * Wait until the code block returns true.
     */

    @Test
    public void waitUntil() {
        var getBookingApi = new GetBookingApi();

        Awaitility.await()
                  .and().with().alias("My custom message")
                  .and().with().timeout(Duration.ofSeconds(5))
                  .then().until(() -> {
                      var statusCode = getBookingApi.getBookingById(20).statusCode();
                      return statusCode == 400;
                  });

    }

    /**
     * Wait until the code block returns true while ignoring all exceptions.
     */

    @Test
    public void waitUntilAndIgnoreAllExceptions() {
        var getBookingApi = new GetBookingApi();

        Awaitility.await()
                  .and().with().alias("My custom message")
                  .and().with().timeout(Duration.ofSeconds(5))
                  .and().ignoreExceptions()
                  .then().until(() -> {
                      getBookingApi.getBookingById(20)
                                   .then().assertThat().statusCode(400);
                      return true;
                  });

    }

    /**
     * Wait until the code block returns true while ignoring specific exception
     * which is in this case AssertionError.
     */

    @Test
    public void waitUntilAndIgnoreSpecificException() {
        var getBookingApi = new GetBookingApi();

        Awaitility.await()
                  .and().with().alias("My custom message")
                  .and().with().timeout(Duration.ofSeconds(5))
                  .and().ignoreExceptionsInstanceOf(AssertionError.class)
                  .then().until(() -> {
                      getBookingApi.getBookingById(20)
                                   .then().assertThat().statusCode(400);
                      return true;
                  });

    }

    /**
     * Define the polling delay to wait between each poll. This might be useful
     * when you want to reduce the number of requests to the server.
     */

    @Test
    public void definePollingDelay() {
        var getBookingApi = new GetBookingApi();

        Awaitility.await()
                  .and().with().alias("My custom message")
                  .and().with().timeout(Duration.ofSeconds(5))
                  .and().ignoreExceptionsInstanceOf(AssertionError.class)
                  .and().pollDelay(Duration.ofMillis(1000))
                  .then().until(() -> {
                      getBookingApi.getBookingById(20)
                                   .then().assertThat().statusCode(400);
                      return true;
                  });

    }


}
