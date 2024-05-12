package RA_05B_com.fakestore.api;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class E3_ProductAPIPojo_WithLombokTest {

	@Test
	public void getProduct_WithLobokPojoTest() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response = given().log().all()
		.when().log().all()
		.get("/products");
		
//		JSON to Pojo mapping --> Deseralization
		
		ObjectMapper objMapper = new ObjectMapper();
		try {
E3_Product_Lombok[] product_Lombok = objMapper.readValue(response.getBody().asString(), E3_Product_Lombok[].class);
		
	for(E3_Product_Lombok p : product_Lombok) {
		
		
		System.out.println("ID : "+p.getId());
		System.out.println("Title : "+p.getTitle());
		System.out.println("Price : "+p.getPrice());
		System.out.println("Description :"+p.getDescription());
		System.out.println("Category : "+p.getCategory());
		System.out.println("Category : "+p.getImage());
		System.out.println("Rate : "+p.getRating().getRate());
		System.out.println("Count : "+p.getRating().getCount());
				System.out.println("--------------------------------------");
			}
		
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	
}
}
