package practiceTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class explains usage of simple GET API with RestAssured. It also
 * explains how to define headers, URL params and query params.
 */

public class SimpleGetApi {

    @Test
    public void simpleProductListApi() {
        // Init the request spec class
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.baseUri("https://automationexercise.com")
                                                .basePath("/api/productsList")
                                                .contentType(ContentType.JSON)
                                                .get();
        System.out.println(response.asString());
    }

    /**
     * This method explains how to define headers in RestAssured. It covers examples
     * of defining headers using
     * key value pairs, using hashmap, using inbuilt Header class and using multiple
     * headers.
     */

    @Test
    public void defineHeaders() {
        // Init the request spec class
        RequestSpecification requestSpecification = RestAssured.given();
        Map<String, Object> headersMap = new HashMap<>();
        headersMap.put("header-one", "value");

        // Use the inbuilt Header class of RestAssured
        Header header = new Header("header-name", "header-value");
        Header headerTwo = new Header("header-name", "header-value");

        List<Header> headerList = List.of(header, headerTwo);

        // Multiple header passed to constructor
        Headers headers = new Headers(header, headerTwo);
        // List of headers passed to constructor
        Headers headersUsingList = new Headers(headerList);

        Response response = requestSpecification.baseUri("https://automationexercise.com")
                                                .basePath("/api/productsList")
                                                .contentType(ContentType.JSON)
                                                // Custom name and value
                                                .header("test-header", "test-value")
                                                // multiple headers with key value
                                                .headers("test-header-1", "test-value", "test-header-2", "test-value")
                                                // using hashmap
                                                .headers(headersMap)
                                                // inbuilt header class
                                                .header(header)
                                                // using the multiple headers method
                                                .headers(headers)
                                                // headers as a list
                                                .headers(headersUsingList)
                                                .get();
        System.out.println(response.asString());
    }

    /**
     * This method explains how to define URL params using RestAssured. It covers
     * examples of defining single URL param. It also covers examples of defining
     * multiple URL params using map.
     * <p>
     * * Refer to {@link RequestSpecification#pathParam(String, Object)} (String, Object...)} and
     * * {@link RequestSpecification#pathParams(Map)} method.
     */

    @Test
    public void getApiWithUrlParams() {
        RequestSpecification requestSpecification = RestAssured.given();
        // Single URL param
        Response response = requestSpecification
                .and().baseUri("https://restful-booker.herokuapp.com")
                .and().basePath("/booking/{bookingId}")
                .and().pathParam("bookingId", 20)
                .when().get();
        System.out.println(response.asString());

        // Using the map for URL params
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("bookingId", 20);

        Response responseTwo = requestSpecification.baseUri("https://restful-booker.herokuapp.com")
                                                   .basePath("/booking/{bookingId}")
                                                   .pathParams(paramsMap)
                                                   .get();
        System.out.println(responseTwo.asString());
    }

    /**
     * This method explains how to define query params using RestAssured. It covers
     * examples of defining single query param. It also covers examples of defining
     * multiple query params using map.
     * <p>
     * Refer to {@link RequestSpecification#queryParam(String, Object...)} and
     * {@link RequestSpecification#queryParams(Map)} method.
     */

    @Test
    public void getApiWithQueryParams() {
        RequestSpecification requestSpecification = RestAssured.given();
        // Single query params as key value
        Response response = requestSpecification.baseUri("https://restful-booker.herokuapp.com")
                                                .basePath("/booking")
                                                .queryParam("firstname", "Elon")
                                                .get();
        System.out.println(response.asString());

        // Using the map for multiple query params
        Map<String, Object> queryParamsMap = new HashMap<>();
        queryParamsMap.put("firstname", "John");
        queryParamsMap.put("lastname", "Doe");

        Response responseTwo = requestSpecification.baseUri("https://restful-booker.herokuapp.com")
                                                   .basePath("/booking")
                                                   .queryParams(queryParamsMap)
                                                   .get();
        System.out.println(responseTwo.asString());
    }
}
