package training_Concepts;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ParsingJsonResponse {
	
	
	@Test(priority=1,enabled =false)
	void ParsingJSONResponseData() {
		
		given()
		.contentType("ContentType.JSON")

		.when()
		.get("https://reqres.in/api/users")

	.then()
	.statusCode(200)
	.body("page", equalTo(1))
	.log().all();
	}

		
		@Test(priority=2)
		void ParsingJSONResponseData2() {
			
			Response res=given()
			.contentType("ContentType.JSON")

			.when()
			.get("https://reqres.in/api/users");

			Assert.assertEquals(res.getStatusCode(), 200);
			Assert.assertEquals(res.header("content-type"), "application/json; charset=utf-8");
		
			String name =res.jsonPath().get("id[1]").toString();
			System.out.println("------------------------------");
			System.out.println(name);
			
		
		
	}

}
