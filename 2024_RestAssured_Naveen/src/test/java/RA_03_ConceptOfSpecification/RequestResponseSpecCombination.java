package RA_03_ConceptOfSpecification;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RequestResponseSpecCombination {
	public static RequestSpecification userSpecReq() {
		RequestSpecification requestSpec = new RequestSpecBuilder() 
				.setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
				.build();
		return requestSpec;
	}
	
	public static ResponseSpecification get_ReqSpec_200OK() {
		ResponseSpecification reqSpec_200OK = new ResponseSpecBuilder() 
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
				.build();
		return reqSpec_200OK;
	}
	
	
	@Test()
	public void reqResSpecTest() {
		given().log().all()
		.spec(userSpecReq())
		.get("/public/v2/users")
		.then()
		.assertThat()
		.spec(get_ReqSpec_200OK());
	
	}
}
