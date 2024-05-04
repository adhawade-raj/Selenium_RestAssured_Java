package GetAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;

public class B9_F1DataAPI_withPathParam_DataProvider {

	@DataProvider
	public Object[][] getCircuitYearData(){
		return new Object[][] {
			
			{"2016", 21},
			{"2017", 20},
			{"1966", 9},
			{"2023", 22}
	};
	}
	
	
	@Test(dataProvider = "getCircuitYearData")
	public void getProductDataAPIWithQueryParamTest(String seasonYear, int totalCircuits) {
	
		RestAssured.baseURI = "https://ergast.com";
		
		given().log().all()
		.pathParam("year", seasonYear)
		.when().log().all()
		.get("/api/f1/{year}/circuits.json")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("MRData.CircuitTable.season", equalTo(seasonYear))
		.body("MRData.CircuitTable.Circuits.circuitId", hasSize(totalCircuits));
		
	}
}
