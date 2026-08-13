package com.qa.gorest.client;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class RestClient {

    private RequestSpecBuilder specBuilder;
    private Properties prop;
    private String baseUri;
    private boolean isAUthrorizationHeaderAdded;

    public RestClient(Properties prop, String baseURI) {
        specBuilder = new RequestSpecBuilder();
        this.prop=prop;
        this.baseUri=baseURI;
    }

    private void addAuthorizationHeader() {
        if (!isAUthrorizationHeaderAdded) {
            specBuilder.addHeader("Authorization", prop.getProperty("tokenId"));
            isAUthrorizationHeaderAdded = true;
        }
    }

    private void setRequestConentType(String contentType) {
        switch (contentType.toLowerCase()) {
            case "json":
                specBuilder.setContentType(ContentType.JSON);
                break;
            case "xml":
                specBuilder.setContentType(ContentType.XML);
                break;
            case "html":
                specBuilder.setContentType(ContentType.HTML);
                break;
            case "multipart":
                specBuilder.setContentType(ContentType.MULTIPART);
                break;
            default:
                System.out.println("Content type not supported, Please use correct content type");
        }
    }


    private RequestSpecification createRequestSpec(boolean includeAuth) {

        specBuilder.setBaseUri(baseUri);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        return specBuilder.build();
    }

    /**
     *
     * @param headersMap
     * @param includeAuth
     * @return
     */
    private RequestSpecification createRequestSpec(Map<String, String> headersMap, boolean includeAuth) {
        specBuilder.setBaseUri(baseUri);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        if (headersMap != null) {
            specBuilder.addHeaders(headersMap);
        }
        return specBuilder.build();
    }

    /**
     *
     * @param headersMap
     * @param queryParams
     * @param includeAuth
     * @return
     */
    private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, String> queryParams, boolean includeAuth) {
        specBuilder.setBaseUri(baseUri);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        if (headersMap != null) {
            specBuilder.addHeaders(headersMap);
        }
        if (queryParams != null) {
            specBuilder.addQueryParams(queryParams);
        }
        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Object requestBody, String contentType, boolean includeAuth) {
        specBuilder.setBaseUri(baseUri);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        if (requestBody != null) {
            specBuilder.setBody(requestBody);
        }
        return specBuilder.build();
    }


    private RequestSpecification createRequestSpec(Map<String, String> headersMap, Object requestBody, String contentType, boolean includeAuth) {
        specBuilder.setBaseUri(baseUri);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        if (headersMap != null) {
            specBuilder.addHeaders(headersMap);
        }
        if (requestBody != null) {
            specBuilder.setBody(requestBody);
        }
        setRequestConentType(contentType);
        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, String> queryParams, Object requestBody, String contentType, boolean includeAuth) {
        specBuilder.setBaseUri(baseUri);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        if (headersMap != null) {
            specBuilder.addHeaders(headersMap);
        }
        if (queryParams != null) {
            specBuilder.addQueryParams(queryParams);
        }
        if (requestBody != null) {
            specBuilder.setBody(requestBody);
        }
        setRequestConentType(contentType);
        return specBuilder.build();
    }


    /** GET Call */

    public Response get(String serviceUrl, boolean includeAuth, boolean log){
        if(log) {
            return RestAssured.given(createRequestSpec(includeAuth)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(includeAuth))
                .when()
                .get(serviceUrl);

    }


    /**
     *
     * @param serviceUrl
     * @param headersMap
     * @param log
     * @return
     */
    public Response get(String serviceUrl, Map<String, String> headersMap, boolean includeAuth, boolean log) {

        if(log) {
            return RestAssured.given(createRequestSpec(headersMap, includeAuth)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(headersMap, includeAuth))
                .when()
                .get(serviceUrl);
    }

    /**
     *
     * @param serviceUrl
     * @param headersMap
     * @param queryParams
     * @param log
     * @return
     */
    public Response get(String serviceUrl,
                        Map<String, String> headersMap,
                        Map<String, String> queryParams,
                        boolean includeAuth,
                        boolean log) {

        if(log) {
            return RestAssured.given(createRequestSpec(headersMap, queryParams, includeAuth)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(headersMap, queryParams, includeAuth))
                .when()
                .get(serviceUrl);
    }

    /**POST Call Methods*/

    /**
     *
     * @param serviceUrl
     * @param contentType
     * @param requestBody
     * @param log
     * @return
     */
    public Response post(String serviceUrl,
                         String contentType,
                         Object requestBody,
                         boolean includeAuth,
                         boolean log) {

        if(log) {
            return RestAssured.given(createRequestSpec(requestBody,contentType, includeAuth)).log().all()
                    .when()
                    .post(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(requestBody,contentType, includeAuth)).log().all()
                .when()
                .post(serviceUrl);
    }

    /**
     *
     * @param serviceUrl
     * @param contentType
     * @param headersMap
     * @param requestBody
     * @param log
     * @return
     */
    public Response post(String serviceUrl,
                         String contentType,
                         boolean includeAuth,
                         Map<String, String> headersMap,
                         Object requestBody,
                         boolean log) {

        if(log) {
            return RestAssured.given(createRequestSpec(headersMap, requestBody, contentType, includeAuth)).log().all()
                    .when()
                    .post(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(headersMap, requestBody,contentType, includeAuth)).log().all()
                .when()
                .post(serviceUrl);
    }

    /**PUT Call Methods*/

    /**
     *
     * @param serviceUrl
     * @param contentType
     * @param requestBody
     * @param log
     * @return
     */
    public Response put(String serviceUrl,
                        String contentType,
                        boolean includeAuth,
                        Object requestBody,
                        boolean log) {

        if(log) {
            return RestAssured.given(createRequestSpec(requestBody,contentType, includeAuth)).log().all()
                    .when()
                    .put(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(requestBody,contentType, includeAuth)).log().all()
                .when()
                .put(serviceUrl);
    }
    /**
     *
     * @param serviceUrl
     * @param contentType
     * @param headersMap
     * @param requestBody
     * @param log
     * @return
     */
    public Response put(String serviceUrl,
                        String contentType,
                        boolean includeAuth,
                        Map<String, String> headersMap,
                        Object requestBody,
                        boolean log) {

        if(log) {
            return RestAssured.given(createRequestSpec(headersMap, requestBody,contentType, includeAuth)).log().all()
                    .when()
                    .put(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(headersMap,requestBody,contentType, includeAuth)).log().all()
                .when()
                .put(serviceUrl);
    }

    /**PATCH Methods*/
    /**
     *
     * @param serviceUrl
     * @param contentType
     * @param requestBody
     * @param log
     * @return
     */
    public Response patch(String serviceUrl,
                          String contentType,
                          boolean includeAuth,
                          Object requestBody,
                          boolean log) {

        if(log) {
            return RestAssured.given(createRequestSpec(requestBody,contentType, includeAuth)).log().all()
                    .when()
                    .patch(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(requestBody,contentType, includeAuth)).log().all()
                .when()
                .patch(serviceUrl);
    }

    /**
     *
     * @param serviceUrl
     * @param contentType
     * @param headersMap
     * @param requestBody
     * @param log
     * @return
     */
    public Response patch(String serviceUrl,
                          String contentType,
                          boolean includeAuth,
                          Map<String, String> headersMap,
                          Object requestBody,
                          boolean log) {

        if(log) {
            return RestAssured.given(createRequestSpec(headersMap, requestBody,contentType, includeAuth)).log().all()
                    .when()
                    .patch(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(headersMap, requestBody,contentType, includeAuth)).log().all()
                .when()
                .patch(serviceUrl);
    }

    /**PUT Call Methods*/

    /**
     *
     * @param serviceUrl
     * @param log
     * @return
     */
    public Response delete(String serviceUrl, boolean includeAuth, boolean log) {

        if(log) {
            return RestAssured.given(createRequestSpec(includeAuth)).log().all()
                    .when()
                    .delete(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(includeAuth))
                .when()
                .delete(serviceUrl);
    }


}
