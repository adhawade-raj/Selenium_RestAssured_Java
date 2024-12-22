package GraphQL;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RickAndMortinAPI {

	@Test
    public void testGraphQLAPI(){
        RestAssured.baseURI = "https://rickandmortyapi.com/graphql";

        String query = "query character($id: ID!) { character(id: $id) { origin { id } location { id } created } }";
        String variables = "{\"id\":\"3\"}";

       Response response =  RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body("{\"query\":\"query character($id: ID!) { character(id: $id) { origin { id } location { id } created name } }\",\"variables\":{\"id\":\"1\"}}")
                    .when()
                    .post()
                    .then()
                    .extract().response();

        response.prettyPrint();

    }
}
