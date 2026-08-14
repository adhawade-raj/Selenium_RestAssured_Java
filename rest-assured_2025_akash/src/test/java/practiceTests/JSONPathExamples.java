package practiceTests;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;

/**
 * This class explains how to use Jayway JSONPath. 
 * It covers examples of filtering JSON objects based on conditions. 
 * Using JSONPath we can do complex filtering of JSON objects before extracting fields. 
 * It will save manual iteration handling of JSON objects in Java.
 **/

public class JSONPathExamples {


    public static void main(String[] args) {

        var responseString = RestAssured.given().baseUri("https://bookcart.azurewebsites.net/api")
                                        .and().basePath("/book")
                                        .when().get().then().assertThat().statusCode(200)
                                        .extract().asString();

        //Filter a book with title containing 'Ruin'
        var filteredBooksWithTitle = JsonPath.read(responseString, "$[?(@.title =~ /.*Ruin.*/i)]");
        System.out.println("Books containing title:" + filteredBooksWithTitle);

        //Filter books for category 'Fiction' and price less than 500
        var filteredBookForCategoryAndPrice = JsonPath.read(responseString,
                "$[?(@.price < 500 && @.category == 'Fiction')]");
        System.out.println("Books with price < 500 and category 'Fiction':" + filteredBookForCategoryAndPrice);

        //Filter books with title not containing 'Harry' and price not equals or less than 214
        var booksWithTitleNotHarryAndPriceNegation = JsonPath.read(responseString,
                "$[?(!(@.price < 214 || @.title =~ /.*Harry.*/))]");
        System.out.println("Books with negated filters:" + booksWithTitleNotHarryAndPriceNegation);


        var nestedJson = """
                {
                  "firstName": "John",
                  "lastName": "doe",
                  "age": 26,
                  "address": {
                    "streetAddress": "naist street",
                    "city": "Nara",
                    "postalCode": "630-0192"
                  },
                  "phoneNumbers": [
                    {
                      "type": "iPhone",
                      "number": "0123-4567-8888"
                    },
                    {
                      "type": "home",
                      "number": "0123-4567-8910"
                    }
                  ]
                }
                """;

        //Fetch 'number' if 'type' is 'iPhone'
        var numberForTypeIphone = JsonPath.read(nestedJson, "$..phoneNumbers[?(@.type=='iPhone')].number");
        System.out.println("Object for iPhone: " + numberForTypeIphone);

        //Flatten array of nested 'phonenumbers' objects
        var flattenPhoneNumbers = JsonPath.read(nestedJson, "$..phoneNumbers[*][*]");
        System.out.println("Flatten Phone Numbers:" + flattenPhoneNumbers);
    }
}
