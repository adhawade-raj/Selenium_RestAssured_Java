package section3_request_specification;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class Part1_ProductList {

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

}
