package RA_03A_ConceptOfSpecification;

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
				.addHeader("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
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
