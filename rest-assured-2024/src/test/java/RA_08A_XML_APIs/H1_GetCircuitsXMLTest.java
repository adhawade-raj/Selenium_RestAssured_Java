package RA_08A_XML_APIs;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class H1_GetCircuitsXMLTest {

	@Test()
	public void getXMLResponseTest() {

		RestAssured.baseURI = "https://ergast.com";
		Response response = RestAssured.given()
				.when()
				.get("/api/f1/2017/circuits.xml")
				.then()
				.extract().response();
		
		String responseBody = response.body().asString();
//		System.out.println(responseBody);
		
		XmlPath xmlPath = new XmlPath(responseBody);
		
		System.out.println("---------------CircuitName-------------------"); 
		List<String> circuitNames = xmlPath.getList("MRData.CircuitTable.Circuit.CircuitName");
		for(String e: circuitNames) {
			System.out.println(e);
		}
		
		System.out.println("---------------CircuitID-------------------"); 
		List<String> circuitIDs = xmlPath.getList("MRData.CircuitTable.Circuit.@circuitId");
		for(String e: circuitIDs) {
			System.out.println(e);
		}
		 
//		TO fetch attribute value based on condition
		System.out.println("---------------Locality-------------------"); 
	String locality = xmlPath.get("**.findAll{it.@circuitId=='americas'}.Location.Locality").toString();
		System.out.println(locality);
		
		System.out.println("---------------Lat & Long Values-------------------"); 
		String latValue = xmlPath.get("**.findAll{it.@circuitId=='americas'}.Location.@lat").toString();
		String longValue = xmlPath.get("**.findAll{it.@circuitId=='americas'}.Location.@long").toString();
		System.out.println(latValue);
		System.out.println(longValue);
		
		System.out.println("--------------- && or || operator-------------------"); 
String data = xmlPath.get("**.findAll{it.@circuitId=='americas' || it.@circuitId=='bahrain'}.Location.Locality").toString();
			System.out.println(data);
		
	}
	
	
}
