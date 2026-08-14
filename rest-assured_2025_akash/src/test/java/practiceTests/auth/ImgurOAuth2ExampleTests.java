package practiceTests.auth;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;

import static java.io.File.separator;

/**
 * This class demonstrates how to use OAuth2 authentication with RestAssured. We
 * have used Imgur API for this example.
 * <p>
 * Related
 * {@link io.restassured.specification.AuthenticationSpecification#oauth2(String)}
 * <p>
 * For generating access token, you can follow the video lecture on OAuth2.0
 * from course. You can also refer to the official documentation for generating
 * access token here: [Registrations
 * Quickstart](<a href=
 * "https://apidocs.imgur.com/#:~:text=Register%20an%20Application%20(IMPORTANT)">...</a>)
 *
 * @see <a href="https://apidocs.imgur.com/#authorization-and-oauth/">Imgur API
 * Authorization</a>
 */

public class ImgurOAuth2ExampleTests {

    private final RequestSpecification requestSpecification = RestAssured.given()
                                                                         .and().baseUri("https://api.imgur.com/3")
                                                                         .and().filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    private final String uploadPngFilePath = "src".concat(separator)
                                                  .concat("main").concat(separator)
                                                  .concat("resources").concat(separator)
                                                  .concat("playwright-nodejs.png");

    private String deleteImageHash;

    /**
     * This test demonstrates how to upload an image to Imgur using OAuth2
     * authentication.
     * <p>
     * We are using
     * {@link io.restassured.specification.AuthenticationSpecification#oauth2(String)}
     * to authenticate with OAuth2.
     * <p>
     * This examples also demonstrates how to use
     * {@link io.restassured.specification.RequestSpecification#multiPart(String, File)}
     * and
     * {@link io.restassured.specification.RequestSpecification#multiPart(String, String)}
     * methods to handle multipart form data.
     */

    @Test
    public void uploadImageWithOauth2() {
        String accessToken = System.getenv("IMGUR_TOKEN");
        Response uploadApiResponse = this.requestSpecification.basePath("/image")
                                                              .and().auth().oauth2(accessToken)
                                                              .and().contentType(ContentType.MULTIPART)
                                                              .and().multiPart("image", new File(uploadPngFilePath))
                                                              .and().multiPart("type", "image")
                                                              .and().multiPart("title", "playwright-with-nodejs")
                                                              .and().multiPart("description", "This is an upload from RestAssured")
                                                              .when().post()
                                                              .then().assertThat().statusCode(200)
                                                              .extract().response();

        this.deleteImageHash = uploadApiResponse.jsonPath().getString("data.deletehash");
    }

    /**
     * This test demonstrates how to delete an image from Imgur using OAuth2
     * authentication.
     */

    @Test(dependsOnMethods = "uploadImageWithOauth2")
    public void deleteImageWithOauth2() {
        String accessToken = System.getenv("IMGUR_TOKEN");
        Response uploadApiResponse = RestAssured.given()
                                                .and().baseUri("https://api.imgur.com/3")
                                                .and().filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                                                .and().basePath("/image/{imageHash}")
                                                .and().auth().oauth2(accessToken)
                                                .and().pathParam("imageHash", this.deleteImageHash)
                                                .and().contentType(ContentType.JSON)
                                                .when().delete()
                                                .then().assertThat().statusCode(200)
                                                .extract().response();
    }

    /**
     * Create new access token using refresh tokens. Please use Imgur credentials and
     * personal data from env variables and do NOT expose them in code.
     * <p>
     * The required environment variables on your machine are: IMGUR_REFRESH_TOKEN, IMGUR_CLIENT_ID and IMGUR_CLIENT_SECRET.
     **/
    @Test
    public void generateNewAccessToken() {
        RestAssured.given().baseUri("https://api.imgur.com/")
                   .and().basePath("oauth2/token")
                   .and().contentType(ContentType.MULTIPART)
                   .and().filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                   .and().multiPart("refresh_token", System.getenv("IMGUR_REFRESH_TOKEN"))
                   .and().multiPart("client_id", System.getenv("IMGUR_CLIENT_ID"))
                   .and().multiPart("client_secret", System.getenv("IMGUR_CLIENT_SECRET"))
                   .and().multiPart("grant_type", "refresh_token")
                   .when().post()
                   .then().assertThat().statusCode(200)
                   .extract().response();

    }
}
