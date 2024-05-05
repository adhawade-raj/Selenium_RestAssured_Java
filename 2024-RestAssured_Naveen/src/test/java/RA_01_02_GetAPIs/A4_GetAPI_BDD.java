package RA_01_02_GetAPIs;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class A4_GetAPI_BDD {
	@Test()
	public void getProductsTest() {
		//Here nothing will be printed on console
		given()
		.when()
		.get("https://fakestoreapi.com/products")
		.then()
		.assertThat()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.header("Connection", "keep-alive");
	}
	@Test()
	public void getProducts_WithLogsTest() {
		//Here nothing will be printed on console
		given().log().all()
		.when().log().all()
		.get("https://fakestoreapi.com/products")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.header("Connection", "keep-alive");
	}
}
