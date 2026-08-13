package section3_request_specification;

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

public class Part1_ProductListHeaders {

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

}
