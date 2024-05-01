package training_Concepts;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

public class DiffMethodsOfPostReq {

	
/*
 * 1) Validation Using HASHMAP
 */

	@Test(priority=1, enabled = false)
	void hashMapMethod() {
		
		HashMap map =new HashMap();
		map.put("name", "morpheus");
		map.put("job", "leader");
		
		given()
		.contentType("application/json")
		.body(map)
		
		.when()
		.post("https://reqres.in/api/users")
		
		.then()
		.statusCode(201)
		.body("job", equalTo("leader"))
		.header("content-Type", "application/json; charset=utf-8")
		.log().all();
		
	}

	
	/*
	 * 2) POST Request using org.json Library
	 */
	@Test(priority=2, enabled = false)
	void postUsingJsonLibrary() {
	
		JSONObject data = new JSONObject();
		data.put("name", "morpheus");
		data.put("job", "leader");
		
		String courseArr[] = {"c", "c++"};
		data.put("courses", courseArr);
		
		
		given()
		.contentType("application/json")
		.body(data.toString())
		
		.when()
		.post("https://reqres.in/api/users")
		
		.then()
		.statusCode(201)
		.log().all();
				
	}
	
	/*
	 * 3) POST Request using POJO Class
	 */
	@Test(priority=3, enabled = false)
	void postUsingPojoClass() {
	
		PojoClass data = new PojoClass();
		data.setName("raj");
		data.setJob("leader");
		
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		
		.then()
		.statusCode(201)
		.log().all();
				
	}
	
	
	/*
	 * 4) POST Request using external JSON file --- Currently not running
	 */
	@Test(priority=4)
	void postUsingexternalJSonFile() throws FileNotFoundException {
		
		
		File f =new File("E:\\Eclipse\\WorkSpace\\RestAssured_Training\\src\\body");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		given()
		.contentType("apllication/json")
		.body(data.toString())
		
		.when()
		.post("https://reqres.in/api/users")
		
		.then()
		.statusCode(201)
		.log().all();
				
		
		
	}
	
	
	
	
	
}
