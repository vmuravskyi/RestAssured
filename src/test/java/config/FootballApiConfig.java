package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class FootballApiConfig {

    private static final String X_AUTH_TOKEN_HEADER = "X-Auth-Token";
    private static final String X_AUTH_TOKEN_VALUE = "886c3f0ec4bc49488a4fccef509e0180";
    private static final String X_RESPONSE_CONTROL_HEADER = "X-Response-Control";
    private static final String X_RESPONSE_CONTROL_VALUE = "minified";

    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;

    @BeforeClass
    public static void setup() {
        requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://api.football-data.org")
            .setBasePath("/v4")
            .addHeader(X_AUTH_TOKEN_HEADER, X_AUTH_TOKEN_VALUE)
            .addHeader(X_RESPONSE_CONTROL_HEADER, X_RESPONSE_CONTROL_VALUE)
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .build();

        responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }

}
