package RA_07_MultiBody;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class G9_BodyWithBinaryTest {
	
	@Test(enabled=false)
	public void bodyWithBinaryPDFTest() {
		RestAssured.baseURI="http://httpbin.org";
		
		io.restassured.response.Response response = RestAssured.given()
				.header("Content-Type","application/pdf")
				.body(new File("./src/test/resources/DifferentBodyData/multipart form data.pdf"))
				.when()
				.post("/post");
		response.prettyPrint();
		System.out.println(response.statusCode());
	}
	
	@Test(enabled=false)
	public void bodyWithBinaryTextTest() {
		RestAssured.baseURI="http://httpbin.org";
		
		io.restassured.response.Response response = RestAssured.given()
				.header("Content-Type","application/txt")
				.body(new File("./src/test/resources/DifferentBodyData/BinaryData.txt"))
				.when()
				.post("/post");
		response.prettyPrint();
		System.out.println(response.statusCode());
	}
	
	@Test(enabled=false)
	public void bodyWithBinaryPNGTest() {
		RestAssured.baseURI="http://httpbin.org";
		
		io.restassured.response.Response response = RestAssured.given()
				.header("Content-Type","application/png")
				.body(new File("./src/test/resources/DifferentBodyData/just_do_it.jpg"))
				.when()
				.post("/post");
		response.prettyPrint();
		System.out.println(response.statusCode());
	}
	
	@Test(enabled=true)
	public void bodyWithBinaryExcelTest() {
		RestAssured.baseURI="http://httpbin.org";
		
		io.restassured.response.Response response = RestAssured.given()
				.header("Content-Type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
				.body(new File("./src/test/resources/DifferentBodyData/BinaryDataExcel.xlsx"))
				.when()
				.post("/post");
		response.prettyPrint();
		System.out.println(response.statusCode());
	}
}
