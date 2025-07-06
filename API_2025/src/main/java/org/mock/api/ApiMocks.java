package org.mock.api;

import com.github.tomakehurst.wiremock.client.WireMock;

public class ApiMocks {

	public static void getDummyUser() {

		WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/users")).willReturn(WireMock.aResponse().withStatus(200)
				.withHeader("Content-Type", "application/json").withBody("{\r\n" + "\"name\" : \"Raj\"\r\n" + "}")));

	}

}
