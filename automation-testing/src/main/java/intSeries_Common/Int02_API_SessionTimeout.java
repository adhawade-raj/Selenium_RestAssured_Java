package intSeries_Common;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Int02_API_SessionTimeout {

    public class RequestSpec {


        private static String token;
        private static long tokenGeneratedTime;

        private static final long TOKEN_VALIDITY =
                4 * 60 * 1000; // 4 minutes

        public static String getToken() {

            if (token == null || isTokenExpired()) {
                generateToken();
            }

            return token;
        }

        private static boolean isTokenExpired() {

            return System.currentTimeMillis() - tokenGeneratedTime
                    >= TOKEN_VALIDITY;
        }

        private static void generateToken() {

            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .body("""
                          {
                            "username": "testuser",
                            "password": "password"
                          }
                          """)
                            .when()
                            .post("/login");

            token = response
                    .jsonPath()
                    .getString("accessToken");

            tokenGeneratedTime = System.currentTimeMillis();
        }
    }


public static RequestSpecification getRequestSpec() {

    return new RequestSpecBuilder()
            .setBaseUri("https://your-api.com")
            .setContentType(ContentType.JSON)
            .addHeader(
                    "Authorization",
                    "Bearer " + RequestSpec.getToken()
            )
            .build();
}
}
