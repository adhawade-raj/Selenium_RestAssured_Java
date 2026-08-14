package org.mock.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class WiremockSetup {

	private static WireMockServer server;

	public static void createWireMockServer() {
//		 Initialize the WireMock server
		server = new WireMockServer(8085);
		WireMock.configureFor("localhost", 8085);
		server.start();
	}

	public static void stopWireMockServer() {
		if (server != null) {
			server.stop();
		}
	}

}
