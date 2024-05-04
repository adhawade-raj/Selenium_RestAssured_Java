package RA_01_02_GetAPIs;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class B3_ProductDataAPI_ExtractBody_JsonArray_JsonPath {

	@Test()
	public void getProductDataAPI_WIthExtractBodyTest() {
	
		RestAssured.baseURI="https://fakestoreapi.com";
		
		io.restassured.response.Response response = given().log().all()
				.queryParam("limit", 5)
				.given().log().all()
				.get("/products");
				
				JsonPath js = response.jsonPath();
				
				List<Integer> idList = js.getList("id");
				List<String> titleList = js.getList("title");
				List<Float> rateList = js.getList("rating.rate");
				
				//Approach 1 of printing List
				System.out.println("_______Approach 1  To Retrieve List Directly print with ref variable______");
				System.out.println(idList);
				System.out.println(titleList);
				System.out.println(rateList);
				
				//Approach 2
				System.out.println("_______Approach 2 To Retrieve List using for Loop______");
				for(int i=0; i<idList.size(); i++) {
					
					int id =idList.get(i);
					String title =titleList.get(i);
					float rate =rateList.get(i);
					
					System.out.println("ID : "+id+"Title : "+title+"Rate : "+rate);
				}
				
	}
}
