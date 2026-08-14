package practiceTests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class explains the usage of Logging and Filters in RestAssured. 
 * It covers examples of pretty printing the response and using filters to log request and response.
 * <p>
 * We can use {@link RequestLoggingFilter} and {@link ResponseLoggingFilter} to log request and response.
 * We can also use {@link LogDetail} enum to log only specific details of request and response.
 * <p>
 */

public class LoggingAndFilters {


    @Test
    public void prettyPrintResponse() {
        RequestSpecification requestSpecification = RestAssured.given();
        //Single URL param
        Response response = requestSpecification.baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/{bookingId}")
                .pathParam("bookingId", 20)
                .get();
        //Pretty print the response
        response.prettyPrint();
    }

    /**
     * RestAssured provides inbuilt {@link io.restassured.filter.Filter} that can
     * be used to filter logging options. We can log complete request and response
     * as well as we can choose what to log on-demand. Refer to {@link RequestLoggingFilter}
     * and {@link ResponseLoggingFilter} classes which have parameterised constructor that
     * takes values from {@link LogDetail} enum.
     */

    @Test
    public void restAssuredFiltersResponse() {
        RequestSpecification requestSpecification = RestAssured.given();
        //Create a new request logging filter
        RequestLoggingFilter requestLoggingFilter = new RequestLoggingFilter();
        //Create a new response logging filter
        ResponseLoggingFilter responseLoggingFilter = new ResponseLoggingFilter();
        //Single URL param
        Response response = requestSpecification.baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/{bookingId}")
                .pathParam("bookingId", 20)
                .filters(requestLoggingFilter, responseLoggingFilter)
                .get();
    }

    /**
     * Log only request URI and Response Body using {@link LogDetail#URI} and {@link LogDetail#BODY} options.
     */
    @Test
    public void restAssuredFilterWithLogDetailsResponse() {
        RequestSpecification requestSpecification = RestAssured.given();
        //Create a new request logging filter
        RequestLoggingFilter requestLoggingFilter = new RequestLoggingFilter(LogDetail.URI);
        //Create a new response logging filter
        ResponseLoggingFilter responseLoggingFilter = new ResponseLoggingFilter(LogDetail.BODY);
        //Single URL param
        Response response = requestSpecification.baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/{bookingId}")
                .pathParam("bookingId", 20)
                .filters(requestLoggingFilter, responseLoggingFilter)
                .get();
    }
}
