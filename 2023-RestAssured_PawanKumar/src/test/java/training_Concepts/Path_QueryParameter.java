package training_Concepts;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class Path_QueryParameter {

	@Test(priority=1)
	void testQueryAndPathParameter() {
		
		
		given()
		.pathParam("mypath", "users")
		.queryParam("users", 2)
		
		
		.when()
		.get("https://reqres.in/api/{mypath}")
		
		.then()
		.statusCode(200)
		.log().all();
	}
	
}
