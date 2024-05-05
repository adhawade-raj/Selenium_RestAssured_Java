package RA_01_02_GetAPIs;



import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class A5_GetAPI_AssertBody_BDD {

	
	
	@Test()
	public void getProducts_WithLogsTest() {
		//Here nothing will be printed on console
		given().log().all()
		.when().log().all()
		 .get("https://fakestoreapi.com/products")
		  .then().log().all()
		   .assertThat()
			.statusCode(200)
				.and()
				.contentType(ContentType.JSON)
					.and()
						.header("Connection", "keep-alive")
							.and()
								.body("$.size()", equalTo(20))
								.and()
								.body("id", is(notNullValue()))
								.and()
								.body("title", hasItem("Mens Cotton Jacket"));
						
	}
}
