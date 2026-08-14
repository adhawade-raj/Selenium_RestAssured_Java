package utils;

import io.restassured.path.json.JsonPath;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonPathUtil {

    private static final Map<String, JsonPath> cache =
            new ConcurrentHashMap<>();

    private static JsonPath load(String filePath) {
        return cache.computeIfAbsent(
                filePath,
                path -> new JsonPath(new File(path))
        );
    }

    public static Map<String, Object> getTestCase(
            String filePath,
            String testcase) {

        JsonPath json = load(filePath);

        return json.getMap(
                "tests.find { it.testcase == '" + testcase + "' }"
        );
    }

    public static Map<String, Object> getRequest(
            String filePath,
            String testcase) {

        return (Map<String, Object>)
                getTestCase(filePath, testcase).get("request");
    }

    public static String getEndpoint(
            String filePath,
            String testcase) {

        return (String)
                getTestCase(filePath, testcase).get("endpoint");
    }

    public static String getMethod(
            String filePath,
            String testcase) {

        return (String)
                getTestCase(filePath, testcase).get("method");
    }

    public static int getExpectedStatus(
            String filePath,
            String testcase) {

        Map<String, Object> expected =
                (Map<String, Object>)
                        getTestCase(filePath, testcase).get("expected");

        return (int) expected.get("statusCode");
    }
}
