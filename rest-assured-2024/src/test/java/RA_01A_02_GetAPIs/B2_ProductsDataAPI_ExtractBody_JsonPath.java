package RA_01A_02_GetAPIs;

import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;


public class B2_ProductsDataAPI_ExtractBody_JsonPath {

	@Test()
	public void getProductDataAPI_WIthExtractBodyTest() {
	
		RestAssured.baseURI="https://fakestoreapi.com";
		
		io.restassured.response.Response response = given().log().all()
				.queryParam("limit", 5)
				.given().log().all()
				.get("/products");
				
				JsonPath js = response.jsonPath();
				
				int firstProductId = js.getInt("[0].id");
				System.out.println(firstProductId);
				
				String firstProductTitle = js.getString("[0].title");
				System.out.println(firstProductTitle);
				
				float price = js.getFloat("[0].price");
				System.out.println(price);
	}
	
}
