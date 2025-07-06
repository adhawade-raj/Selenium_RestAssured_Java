package org.mock.api;

import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ApiMocks {

	public static void getDummyUser() {

		WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/users"))
				.willReturn(WireMock.aResponse()
				.withStatus(200)
				.withHeader("Content-Type", "application/json")
				.withBody("{\r\n" + "\"name\" : \"Raj\"\r\n" + "}")));
	}

	/**
	 * Not working at moment
	 */
	public static void getDummyUserWithQueryParam() {

		WireMock.stubFor(
				WireMock.get(WireMock.urlPathEqualTo("/api/users"))
				.withQueryParam("param", WireMock.equalTo("value"))
						.willReturn(WireMock.aResponse().withStatus(200)
								.withHeader("Content-Type", "application/json")
								.withBody("{\r\n" + "\"name\" : \"Raj\"\r\n" + "}")));
	}

	public static void createDummyUser() {

		WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/users"))
				.withHeader("Content-Type", WireMock.equalTo("application/json"))
				.withRequestBody(WireMock.equalTo("{\r\n" + "\"message\" : \"User is created\"\r\n" + "}"))
				.willReturn(WireMock.aResponse().withStatus(201).withHeader("Content-Type", "application/json")
				.withBody("{\r\n" + "\"message\" : \"User is created\"\r\n" + "}")));
	}

	public static void deleteDummyUser() {

		WireMock.stubFor(
				WireMock.delete(WireMock.urlEqualTo("/api/users"))
				.willReturn(WireMock.aResponse().withStatus(204)));
	}
	
	public static void createDummyUserWithJson() {

        stubFor(post(urlEqualTo("/api/users"))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withStatusMessage("user is created")
                        .withBodyFile("user.json")));
    }

}
