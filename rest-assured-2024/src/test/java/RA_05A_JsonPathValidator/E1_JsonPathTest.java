package RA_05A_JsonPathValidator;

import static io.restassured.RestAssured.given;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;


public class E1_JsonPathTest {

	
	@Test(enabled=false)
	public void getCircuitsDataAPIWith_YearTest() {
		RestAssured.baseURI = "https://ergast.com";
		
		io.restassured.response.Response response = given().log().all()
		.pathParam("year", "2017")
		.when().log().all()
		.get("/api/f1/{year}/circuits.json");

		String jsonResponse = response.asString();
		List<String> countryList = JsonPath.read(jsonResponse, "$..CircuitTable..country");
		System.out.println(countryList);
	}
	
	@Test()
	public void getProductsTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		io.restassured.response.Response response = given().log().all()
		.when().log().all()
		.get("/products");

		String jsonResponse = response.asString();
		
//		1.
		List<Float> rateLessThanThree = JsonPath.read(jsonResponse, "$[?(@.rating.rate<3)].rating.rate");
		System.out.println(rateLessThanThree);
		
//		2. This will throw an exception
//		com.jayway.jsonpath.InvalidPathException:
//		List<Map<String,Object>> jewleryListJsonPath1 = JsonPath.read(jsonResponse, "$[?(@.category='jewelery')].[title,price]");
//		System.out.println(jewleryListJsonPath1);
		
		List<Map<String,Object>> jewleryList = JsonPath.read(jsonResponse, "$[?(@.category=='jewelery')].[\"title\",\"price\"]");
		System.out.println(jewleryList);
		for(Map<String, Object> product: jewleryList)
		{
			String title = (String) product.get("title");
			Object price = (Object) product.get("price");
			System.out.println("title : "+title);
			System.out.println("title : "+price);
			System.out.println("----------------------------------");
		}
		
		System.out.println("__________Approach 2 for # or More than keys________");
//		3.With Three or more than three keys
		List<Map<String,Object>> jewleryList2 = JsonPath.read(jsonResponse, "$[?(@.category=='jewelery')].[\"title\",\"price\"]");
		System.out.println(jewleryList2);
		for(Map<String, Object> product: jewleryList2)
		{
			String title = (String) product.get("title");
			Object price = (Object) product.get("price");
			Integer id = (Integer) product.get("id");
			System.out.println("title : "+title);
			System.out.println("title : "+price);
			System.out.println("title : "+id);
			System.out.println("----------------------------------");
		}
		
	}
	
}
