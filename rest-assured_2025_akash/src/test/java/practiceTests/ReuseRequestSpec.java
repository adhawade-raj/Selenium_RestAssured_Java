package practiceTests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ReuseRequestSpec {

    private final RequestSpecification requestSpecification = RestAssured.given()
                                                                         .and().baseUri("https://restful-booker.herokuapp.com")
                                                                         .and().filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    @Test
    public void prettyPrintResponse() {
        //Single URL param
        Response response = this.requestSpecification
                .basePath("/booking/{bookingId}")
                .pathParam("bookingId", 20)
                .get();
        //Pretty print the response
        response.prettyPrint();
    }

    @Test
    public void restAssuredFiltersResponse() {
        //Single URL param
        Response response = this.requestSpecification
                .basePath("/booking/{bookingId}")
                .pathParam("bookingId", 20)
                .get();
    }

    @Test
    public void getApiWithQueryParams() {
        //Single query params as key value
        Response response = this.requestSpecification
                .basePath("/booking")
                .queryParam("firstname", "Elon")
                .get();
        System.out.println(response.asString());

        //Using the map for multiple query params
        Map<String, Object> queryParamsMap = new HashMap<>();
        queryParamsMap.put("firstname", "John");
        queryParamsMap.put("lastname", "Doe");

        Response responseTwo = this.requestSpecification
                .basePath("/booking")
                .queryParams(queryParamsMap)
                .get();
        System.out.println(responseTwo.asString());
    }
}
