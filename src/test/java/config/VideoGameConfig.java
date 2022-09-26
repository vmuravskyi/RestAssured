package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;

public class VideoGameConfig {

    public static RequestSpecification videoGameRequestSpec;
    public static ResponseSpecification videoGameResponseSpec;


    @BeforeClass
    public static void setup() {
        videoGameRequestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setBasePath("/app")
            .setPort(8080) // 8888 for using Fiddler
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .build();

        videoGameResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_OK)
            .expectResponseTime(Matchers.lessThan(3000L))
            .build();

        RestAssured.requestSpecification = videoGameRequestSpec;
        RestAssured.responseSpecification = videoGameResponseSpec;
    }

}
