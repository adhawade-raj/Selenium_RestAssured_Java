package training_Concepts;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class Http_Requests {
	
	int id;
	
	/*
	 * Get Method
	 */
	@Test(enabled=false, priority=1)
	void getUSer() {
		given()
		
		.when()
		.get("https://reqres.in/api/users?page=2")
		
		
		.then()
		.statusCode(200)
		.body("page",equalTo(2))
		.log().all();
	
	}
	
	/*
	 * Post Request1
	 */
	@Test(priority=2, enabled=false)
	void updateUser() {
		
		
		HashMap data = new HashMap();
		data.put("name", "morpheus");
		data.put("job","leader");
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users?page=2")
		
		
		.then()
		.statusCode(201)
		.body("name",equalTo("morpheus"))
		.log().all();
	
	}
	
	/*
	 * POST Request2
	 */
	@Test(priority=3)
	void updateUser2() {
		
		
		HashMap data = new HashMap();
		data.put("name", "morpheus");
		data.put("job","leader");
		
		id=given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users?page=2")
		.jsonPath().getInt("id");
		
		
//		.then()
//		.statusCode(201)
//		.body("name",equalTo("morpheus"))
//		.log().all();
	
	}
	
	/*
	 * PUT Request
	 */
	@Test(priority=4,dependsOnMethods= {"updateUser2"})
	void UpdateUser() {
		
		
		HashMap data = new HashMap();
		data.put("name", "raj");
		data.put("job","leader");
	
	given()
	.contentType("application/json")
	.body(data)
	
	.when()
	.put("https://reqres.in/api/users/"+id)
	
	
	
	.then()
	.statusCode(200)
	.body("name",equalTo("raj"))
	.log().all();
	}
	
	@Test(priority=5)
	void deleteUse() {
		given()
		
		.when()
		.delete("https://reqres.in/api/users/2")
		
		.then()
		.statusCode(204)
		.log().all();
	}
	
	
}
