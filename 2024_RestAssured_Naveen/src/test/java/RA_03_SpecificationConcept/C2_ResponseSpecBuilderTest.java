package RA_03_SpecificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class C2_ResponseSpecBuilderTest {

	public static ResponseSpecification get_ReqSpec_200OK() {
		ResponseSpecification reqSpec_200OK = new ResponseSpecBuilder() 
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
				.build();
		return reqSpec_200OK;
	}
	
	public static ResponseSpecification get_ReqSpec_200OK_WithBody() {
		ResponseSpecification reqSpec_200OK_WithBody = new ResponseSpecBuilder() 
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectBody("$.size()",equalTo(10))
			.expectBody("id", hasSize(10))
				.build();
		return reqSpec_200OK_WithBody;
	}
	
	public static ResponseSpecification get_ReqSpec_401_AuthFail() {
		ResponseSpecification reqSpec_401_AuthFail = new ResponseSpecBuilder() 
			.expectStatusCode(401)
				.build();
		return reqSpec_401_AuthFail;
	}
	
	@Test(enabled=false)
	public void getUserSpec_200Test() {
		RestAssured.baseURI="https://gorest.co.in";
		given()
//		.header("Authorization", "Bearer ")
		.when()
		.get("/public/v2/users")
		.then()
		.assertThat()
		.spec(get_ReqSpec_200OK());

	}
	@Test
	public void getUserSpec_200_WithBodyTest() {
		RestAssured.baseURI="https://gorest.co.in";
		given()
//		.header("Authorization", "Bearer ")
		.when()
		.get("/public/v2/users")
		.then()
		.assertThat()
		.spec(get_ReqSpec_200OK_WithBody());

	}
	
	/**
	 * test for just a demo purpose
	 */
	@Test(enabled=false)
	public void getUserSpec_401_AuthFailTest() {
		RestAssured.baseURI="https://gorest.co.in";
		given()
		.header("Authorization", " 999")
		.when()
		.get("/public/v2/users")
		.then()
		.assertThat()
		.spec(get_ReqSpec_401_AuthFail());

	}
}
