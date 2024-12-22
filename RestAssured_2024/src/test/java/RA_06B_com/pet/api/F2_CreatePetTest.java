package RA_06B_com.pet.api;

import org.asynchttpclient.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import RA_06B_com.pet.api.F2_Pet_LombokPojo.Category;
import RA_06B_com.pet.api.F2_Pet_LombokPojo.Tag;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.List;

public class F2_CreatePetTest {

	
	@Test(enabled = false)
	public void petLombokTest_WithoutBuilder() {
		
		RestAssured.baseURI="https://petstore.swagger.io";
		
		Category category = new Category(02,"StreetDog");
		List<String> photoUrls = Arrays.asList("https://www/dog1.com","https://www/dog2.com");
		
		Tag tag1 = new Tag(02,"red");
		Tag tag2 = new Tag(03,"black");
		List<Tag> tags = Arrays.asList(tag1, tag2);
		
F2_Pet_LombokPojo f2_Pet_LombokPojo = new F2_Pet_LombokPojo(201, category, "Ron", photoUrls, tags, "Available");
		
		
		io.restassured.response.Response response = given()
		.contentType(ContentType.JSON)
		.body(f2_Pet_LombokPojo)
		.when()
		.post("/v2/pet");
		
		System.out.println("Status Code : "+response.statusCode());
		response.prettyPrint();
		
		//De-Serialization
		
		ObjectMapper mapper = new ObjectMapper();
	try {
		F2_Pet_LombokPojo f2_ResponsePet_LombokPojo =mapper.readValue(response.getBody().asString(), F2_Pet_LombokPojo.class);
	
		System.out.println("---------------------Approach 1 Without Builder----------------------------");
		System.out.println("Id: "+f2_ResponsePet_LombokPojo.getId());
		System.out.println("Category Id: "+f2_ResponsePet_LombokPojo.getCategory().getId());
		System.out.println("Category Name : "+f2_ResponsePet_LombokPojo.getCategory().getName());
		
		System.out.println("Name : "+f2_ResponsePet_LombokPojo.getName());
		
		System.out.println("PhotUrls : "+f2_ResponsePet_LombokPojo.getPhotoUrls());
		
		System.out.println("Tag Id1 : "+f2_ResponsePet_LombokPojo.getTags().get(0).getId());
		System.out.println("Tag Name1 : "+f2_ResponsePet_LombokPojo.getTags().get(0).getName());
		
		System.out.println("Tag Id2 : "+f2_ResponsePet_LombokPojo.getTags().get(1).getId());
		System.out.println("Tag Name2 : "+f2_ResponsePet_LombokPojo.getTags().get(1).getName());
		
		System.out.println("Status : "+f2_ResponsePet_LombokPojo.getStatus());
		System.out.println("----------------------------------------------------------------------");
		
		/**
		 * Assertions
		 */
		
Assert.assertEquals(f2_Pet_LombokPojo.getId(), f2_ResponsePet_LombokPojo.getId());
Assert.assertEquals(f2_Pet_LombokPojo.getName(), f2_ResponsePet_LombokPojo.getName());
Assert.assertEquals(f2_Pet_LombokPojo.getCategory().getName(), f2_ResponsePet_LombokPojo.getCategory().getName());
		
		
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
		
		
	}
	
	
	
	@Test(enabled = true)
	public void petLombokTest_WithBuilder() {
		
		RestAssured.baseURI="https://petstore.swagger.io";
		
		 Category category = new Category.CategoryBuilder()
				.id(201)
				.name("Street Dog")
				.build();
		Tag tag1 = new Tag.TagBuilder()
				.id(201)
				.name("Red")
				.build();
		
		Tag tag2 = new Tag.TagBuilder()
				.id(201)
				.name("Black")
				.build();
		
		F2_Pet_LombokPojo f2_Pet_LombokPojo =  new F2_Pet_LombokPojo.F2_Pet_LombokPojoBuilder()
		 .id(201)
		 .category(category)
		 .name("Ronney")
		 .photoUrls(Arrays.asList("abc.com", "xyz.com"))
		 .tags(Arrays.asList(tag1, tag2))
		 .Status("available")
		 .build();
		 
					
					
		io.restassured.response.Response response = given()
		.contentType(ContentType.JSON)
		.body(f2_Pet_LombokPojo)
		.when()
		.post("/v2/pet");
		
		System.out.println("Status Code : "+response.statusCode());
		response.prettyPrint();
		
		//De-Serialization
		
		ObjectMapper mapper = new ObjectMapper();
	try {
		F2_Pet_LombokPojo f2_ResponsePet_LombokPojo =mapper.readValue(response.getBody().asString(), F2_Pet_LombokPojo.class);
	
		System.out.println("---------------------Approach 2 With Builder----------------------------");
		System.out.println("Id: "+f2_ResponsePet_LombokPojo.getId());
		System.out.println("Category Id: "+f2_ResponsePet_LombokPojo.getCategory().getId());
		System.out.println("Category Name : "+f2_ResponsePet_LombokPojo.getCategory().getName());
		
		System.out.println("Name : "+f2_ResponsePet_LombokPojo.getName());
		
		System.out.println("PhotUrls : "+f2_ResponsePet_LombokPojo.getPhotoUrls());
		
		System.out.println("Tag Id1 : "+f2_ResponsePet_LombokPojo.getTags().get(0).getId());
		System.out.println("Tag Name1 : "+f2_ResponsePet_LombokPojo.getTags().get(0).getName());
		
		System.out.println("Tag Id2 : "+f2_ResponsePet_LombokPojo.getTags().get(1).getId());
		System.out.println("Tag Name2 : "+f2_ResponsePet_LombokPojo.getTags().get(1).getName());
		
		System.out.println("Status : "+f2_ResponsePet_LombokPojo.getStatus());
		System.out.println("----------------------------------------------------------------------");
		
		/**
		 * Assertions
		 */
		
Assert.assertEquals(f2_Pet_LombokPojo.getId(), f2_ResponsePet_LombokPojo.getId());
Assert.assertEquals(f2_Pet_LombokPojo.getName(), f2_ResponsePet_LombokPojo.getName());
Assert.assertEquals(f2_Pet_LombokPojo.getCategory().getName(), f2_ResponsePet_LombokPojo.getCategory().getName());
		
		
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
		
		
	}
}
