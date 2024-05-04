package RA_03_SpecificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class C1_RequestSpecBuilderTest {

	
	public static RequestSpecification userSpecReq() {
		RequestSpecification requestSpec = new RequestSpecBuilder() 
				.setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
//				.addHeader("Authorization", "Bearer ")
				.build();
		return requestSpec;
	}
	
	@Test()
	public void getUserData_WithRequestSpec() {
	RestAssured.given().log().all()
	.spec(userSpecReq())
	.get("/public/v2/users")
	.then().log().all()
	.statusCode(200);
	}
	
	@Test()
	public void getUserData_WithRequestSpec2() {

	RestAssured.given().log().all()
	.queryParam("name", "Darshan")
	.queryParam("status", "active")
	.spec(userSpecReq())
	.get("/public/v2/users")
	.then().log().all()
	.statusCode(200);
	}
}
