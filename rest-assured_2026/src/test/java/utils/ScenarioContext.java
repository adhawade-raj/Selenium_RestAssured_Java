package utils;

import io.restassured.response.Response;

import java.util.Map;

public class ScenarioContext {

    public static String testcase;
    public static String dataFile;
    public static Map<String, Object> request;
    public static Response response;
}
