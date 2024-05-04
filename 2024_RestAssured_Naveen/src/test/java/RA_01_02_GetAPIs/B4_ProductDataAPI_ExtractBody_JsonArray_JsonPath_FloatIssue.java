package RA_01_02_GetAPIs;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class B4_ProductDataAPI_ExtractBody_JsonArray_JsonPath_FloatIssue {

	@Test(priority=1, enabled = false)
	public void getProductDataAPI_WIthExtractBodyTest() {
		RestAssured.baseURI="https://fakestoreapi.com";
		io.restassured.response.Response response = given().log().all()
				.queryParam("limit", 10)
				.given().log().all()
				.get("/products");
				
				JsonPath js = response.jsonPath();
				
				List<Integer> idList = js.getList("id");
				List<String> titleList = js.getList("title");
				List<Float> rateList = js.getList("rating.rate"); 
//This float will give the exception
//java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.Float
//As Rate found to be 3 instead of float value = 3.0
				
				System.out.println("_______Problem Defination______");
				for(int i=0; i<idList.size(); i++) {
					int id =idList.get(i);
					String title =titleList.get(i);
					float rate =rateList.get(i);
					System.out.println("ID : "+id+"Title : "+title+"Rate : "+rate);
				}
}
	
	@Test(priority=2, enabled = true)
	public void getProductDataAPI_WIthExtractBodyTest2() {
		RestAssured.baseURI="https://fakestoreapi.com";
		io.restassured.response.Response response = given().log().all()
				.queryParam("limit", 10)
				.given().log().all()
				.get("/products");
				
				JsonPath js = response.jsonPath();
				
				List<Integer> idList = js.getList("id");
				List<String> titleList = js.getList("title");
				List<Object> rateList = js.getList("rating.rate"); 
//This Object will not give the exception
//As We changed to Object type which accepts any value
//And 3.0 will be printed instead of 3
				
				System.out.println("_______Approach 1 Using Object Type______");
				for(int i=0; i<idList.size(); i++) {
					int id =idList.get(i);
					String title =titleList.get(i);
					Object rate =rateList.get(i);
					System.out.println("ID : "+id+"Title : "+title+"Rate : "+rate);
				}
}
	
	
	@Test(priority=3, enabled = false)
	public void getProductDataAPI_WIthExtractBodyTest3() {
		RestAssured.baseURI="https://fakestoreapi.com";
		io.restassured.response.Response response = given().log().all()
				.queryParam("limit", 10)
				.given().log().all()
				.get("/products");
				
				JsonPath js = response.jsonPath();
				
				List<Integer> idList = js.getList("id");
				List<String> titleList = js.getList("title");
				List<Float> rateList = js.getList("rating.rate", Float.class); 
//This float will not give the exception
//As We explicitly mentioned the o/p should be converted to be float
//Even if api contains 3 (int value) it will be converted to 3.0
//And 3.0 will be printed instead of 3
				
		System.out.println("_______Approach 2 Using float Type explicitly in argument______");
				for(int i=0; i<idList.size(); i++) {
					int id =idList.get(i);
					String title =titleList.get(i);
					Float rate =rateList.get(i);
					System.out.println("ID : "+id+"Title : "+title+"Rate : "+rate);
				}
}
	
}
