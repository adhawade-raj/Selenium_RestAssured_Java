package RA_01_02_GetAPIs;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class B6_UserDataAPI_ExtractBody_Extract {

//	this extract function will be used to fetch only one value
//	e.g  we will be fetching token with this approach
//	So that we can pass the token id in another function
	
	@Test()
	public void getProductDataAPI_WIthExtractBodyTest3() {
		RestAssured.baseURI="https://gorest.co.in";
		int userID = given().log().all()
				.when().log().all()
				.get("/public/v2/users/6880165")
				.then()
				.extract().path("id");
				System.out.println(userID);
}
}
