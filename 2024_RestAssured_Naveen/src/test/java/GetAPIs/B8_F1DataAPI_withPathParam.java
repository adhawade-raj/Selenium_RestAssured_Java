package GetAPIs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static org.hamcrest.Matchers.*;

public class B8_F1DataAPI_withPathParam {

	@Test()
	public void getProductDataAPIWithQueryParamTest() {
	
		RestAssured.baseURI = "https://ergast.com";
		
		given().log().all()
		.pathParam("year", "2017")
		.when().log().all()
		.get("/api/f1/{year}/circuits.json")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("MRData.CircuitTable.season", equalTo("2017"))
		.body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));
		
	}

	
}
