package practiceTests.auth;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

/**
 * This class demonstrates how to use basic and digest auth with RestAssured.
 * <p>
 * Related Class  {@link io.restassured.specification.AuthenticationSpecification}
 *
 * @see <a href="https://the-internet.herokuapp.com/basic_auth">Basic Authorization Practice Link</a>
 * @see <a href="https://the-internet.herokuapp.com/digest_auth">Basic Authorization Practice Link</a>
 * <p>
 **/

public class BasicAndDigestAuthTests {

    private final RequestSpecification requestSpecification = RestAssured.given()
                                                                         .and().baseUri("https://restful-booker.herokuapp.com")
                                                                         .and().filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    @Test
    public void simpleHttpDeleteWithPreemptiveBasicAuth() {
        Response deleteApiResponse = this.requestSpecification
                .and().basePath("/booking/{bookingId}")
                //We have to pass credentials to the api but usually APIs do not
                //challenge our capability to login.
                .and().auth().preemptive().basic("admin", "password123")
                .and().pathParam("bookingId", 10)
                .when().delete();
    }

    @Test
    public void simpleHttpDeleteWithCustomAuthHeader() {
        Response deleteApiResponse = this.requestSpecification
                .and().basePath("/booking/{bookingId}")
                .and().header("Cookie", "token=4f3d36dd21dd53c")
                .and().header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .and().pathParam("bookingId", 12)
                .when().delete();
    }

    @Test
    public void challengedBasicAuth() {
        Response challengedAuthApiResponse = RestAssured.given()
                                                        .and().baseUri("https://the-internet.herokuapp.com/")
                                                        .and().basePath("basic_auth")
                                                        //RestAssured will provide credentials when server will ask for it.
                                                        .and().auth().basic("admin", "admin")
                                                        .when().get();

        System.out.println(challengedAuthApiResponse.asString());
    }

    @Test
    public void challengedDigestAuth() {
        Response challengedAuthApiResponse = RestAssured.given()
                                                        .and().baseUri("https://the-internet.herokuapp.com/")
                                                        .and().basePath("digest_auth")
                                                        //RestAssured will provide credentials when server will ask for it.
                                                        .and().auth().digest("admin", "admin")
                                                        .when().get();

        System.out.println(challengedAuthApiResponse.asString());
    }
}
