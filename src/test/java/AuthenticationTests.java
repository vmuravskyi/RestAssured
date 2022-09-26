import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class AuthenticationTests {

    @BeforeClass
    public static void setup() {
        RestAssured.proxy("localhost", 8888);
    }

    // Basic Auth https://github.com/rest-assured/rest-assured/wiki/Usage#basic-authentication
    @Test
    public void basicPreemptiveAuthTest() {
        given()
            .auth().preemptive().basic("username", "password")
            .when()
            .get("http://localhost:8080/someEndpoint");
    }

    // Basic Auth https://github.com/rest-assured/rest-assured/wiki/Usage#basic-authentication
    @Test
    public void basicChallengedAuthTest() {
        given()
            .auth().basic("username", "password")
            .when()
            .get("http://localhost:8080/someEndpoint");
    }

    // OAuth1 https://github.com/rest-assured/rest-assured/wiki/Usage#oauth
    @Test
    public void oauth1Test() {
        given()
            .auth().oauth("consumerKey", "consumerSecret", "consumerAccessToken", "secretToken")
            .when()
            .get("http://localhost:8080/someEndpoint");
    }

    // OAuth2 https://github.com/rest-assured/rest-assured/wiki/Usage#oauth
    @Test
    public void oauth2Test() {
        given()
            .auth().oauth2("accessToken")
            .when()
            .get("http://localhost:8080/someEndpoint");
    }

    // SSL https://github.com/rest-assured/rest-assured/wiki/Usage#ssl
    @Test
    public void relaxedHttpsTest() {
        given()
            .relaxedHTTPSValidation()
            .when()
            .get("http://localhost:8080/someEndpoint");
    }

    // SSL https://github.com/rest-assured/rest-assured/wiki/Usage#basic-authentication
    @Test
    public void keystoreTest() {
        given()
            .keyStore("/pathToJKS", "password")
            .when()
            .get("http://localhost:8080/someEndpoint");
    }

}
