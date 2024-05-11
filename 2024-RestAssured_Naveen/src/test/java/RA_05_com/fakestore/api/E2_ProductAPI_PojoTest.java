package RA_05_com.fakestore.api;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class E2_ProductAPI_PojoTest {


	@Test
	public void getProduct_POJOTest() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given().log().all()
		.when().log().all()
		.get("/products");
		
//		JSON to Pojo mapping --> Deseralization
		
		ObjectMapper objMapper = new ObjectMapper();
		try {
			E2_Products[] product = objMapper.readValue(response.getBody().asString(), E2_Products[].class);
		
			for(E2_Products p : product) {
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
